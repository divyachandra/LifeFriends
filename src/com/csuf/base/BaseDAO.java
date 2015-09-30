package com.csuf.base;

import com.csuf.exceptions.BadCountException;

import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * Created by Divya on 26-09-2015.
 */
public abstract class BaseDAO<P extends BaseBean> extends AbstractDAO {
    private boolean identity;

    protected BaseDAO(boolean identity) {
        this.identity = identity;
    }

    protected final Connection getConnection() {
        return DbConnectionManager.getConnection(DbConfig.DEFAULT);
    }

    protected boolean isNullPK(Object pk) {
        if (identity && pk == null)
            return true;
        return false;
    }

    protected int insert(P bl, String table, String pkColumn) throws SQLException {
        if (identity && !isNullPK(bl.getPk()))
            throw new BadCountException("Attempt to insert identity object with non-null PK.");
        else if (!identity && isNullPK(bl.getPk()))
            throw new BadCountException("Attempt to insert non-identity object with null PK.");
        Map<String, Object> columns = buildColumns(bl);
        Connection conn = null;
        try {
            conn = getConnection();
            int count = 0;
            final StringBuffer sqlbuff = new StringBuffer();
            final Object[] parms = buildInsert(table, columns, sqlbuff);
            if (pkColumn != null)
                sqlbuff.append("");
            final String sql = sqlbuff.toString();
            ResultSet data = null;
            new Integer(executeSQLUpdate(conn, sql, parms));
            data = executeSQL(conn, "SELECT LAST_INSERT_ID();");
            if (data.next())
                count = new Integer(data.getInt(1));

            if (identity && count != 0) {
                bl.setPk(bl.createPK(new Integer(count)));
                count = 1;
            }
            if (count != 1)
                throw new BadCountException("inserted", 1, count);
            return count;
        } finally {
            conn.close();
        }
    }

    protected int update(P bl, String table, String pkColumn) throws SQLException {
        if (isNullPK(bl.getPk()))
            return 0;
        if (isNullPK(bl.getPk()))
            throw new BadCountException("Attempt to update object with null PK.");

        String filter = pkColumn + "=?";
        Object[] parms = {bl.getPk()};
        Map<String, Object> columns = buildColumns(bl);
        Connection conn = null;
        try {
            conn = getConnection();
            StringBuffer sql = new StringBuffer();
            Object[] fullparms = buildUpdate(table, filter, parms, columns, sql);
            int count = executeSQLUpdate(conn, sql.toString(), fullparms);
            if (count != 1)
                throw new BadCountException("updated", 1, count);
            return count;
        } finally {
            conn.close();
        }
    }

    protected int remove(P bl, String table, String pkColumn) throws SQLException {
        String filter = pkColumn + "=?";
        Object[] parms = {bl.getPk()};
        Connection conn = null;
        try {
            conn = getConnection();
            String sql = buildDelete(table, filter, parms);
            int count = executeSQLUpdate(conn, sql, parms);
            if (count != 1)
                throw new BadCountException("deleted", 1, count);
            return count;
        } finally {
            conn.close();
        }
    }

    protected P findByPK(Object pk, String table, String pkColumn) throws SQLException {
        if (pk == null)
            return null;
        String filter = pkColumn + "=?";
        Object[] parms = {pk};
        return findByFilter(table, null, filter, parms);
    }

    protected P findByFilter(String table, String columns, String filter, Object[] parms) throws SQLException {
        Connection conn = null;
        ResultSet data = null;
        try {
            conn = getConnection();
            String sql = buildQuery(table, columns, filter, parms, null);
            data = executeSQL(conn, sql, parms);
            if (!data.next())
                return null;
            P bl = createInstance();
            load(bl, data);
            if (data.next()) {
                throw new SQLException(null, "More than one record returned from find() with filter '" + filter + "'.");
            }
            return bl;
        } finally {
            conn.close();
        }
    }

    protected Collection<P> search(String table, String columns, String filter, Object[] parms, String orderBy)
            throws SQLException {
        Connection conn = null;
        ResultSet data = null;
        try {
            conn = getConnection();
            String sql = buildQuery(table, columns, filter, parms, orderBy);
            data = executeSQL(conn, sql, parms);

            Collection<P> values = new ArrayList<P>();
            while (data.next()) {
                P bl = createInstance();
                load(bl, data);
                values.add(bl);
            }
            return values;
        } finally {
            conn.close();
        }
    }

    private static Object[] buildInsert(String table, Map<String, Object> columns, StringBuffer sql) {
        sql.append("INSERT INTO ");
        sql.append(table).append(" (");
        List<Object> parms = new ArrayList<Object>();
        StringBuffer values = new StringBuffer();
        Iterator<String> iter = columns.keySet().iterator();
        while (iter.hasNext()) {
            Object key = iter.next();
            Object value = columns.get(key);
            sql.append(key);
            if (value == null)
                values.append("NULL");
            else {
                values.append('?');
                parms.add(value);
            }
            if (iter.hasNext()) {
                sql.append(", ");
                values.append(", ");
            }
        }
        sql.append(") VALUES (").append(values.toString());
        sql.append(")");
        return parms.toArray();
    }

    private static Object[] buildUpdate(String table, String filter, Object[] parms, Map<String, Object> columns,
                                        StringBuffer sql) {
        sql.append("UPDATE ");
        sql.append(table).append(" SET ");
        List<Object> fullparms = new ArrayList<Object>(columns.size() + (parms != null ? parms.length : 0));
        Iterator<String> iter = columns.keySet().iterator();
        while (iter.hasNext()) {
            Object key = iter.next();
            Object value = columns.get(key);
            sql.append(key);
            if (value == null)
                sql.append("=NULL");
            else {
                sql.append("=?");
                fullparms.add(value);
            }
            if (iter.hasNext())
                sql.append(", ");
        }
        if (filter != null && filter.length() != 0)
            sql.append(" WHERE ").append(filter);
        if (parms != null && parms.length != 0)
            fullparms.addAll(Arrays.asList(parms));
        return fullparms.toArray();
    }

    private static String buildDelete(String table, String filter, Object[] parms) {
        return "DELETE FROM " + table + " WHERE " + filter;
    }

    private static String buildQuery(String table, String columns, String filter, Object[] parms, String orderBy) {
        if (columns == null)
            columns = "*";
        StringBuffer sql = new StringBuffer("SELECT ");
        sql.append(columns).append(" FROM ").append(table);
        if (filter != null && filter.length() > 0)
            sql.append(" WHERE ").append(filter);
        if (orderBy != null && orderBy.length() > 0)
            sql.append(" ORDER BY ").append(orderBy);
        return sql.toString();
    }

    protected ResultSet executeSQL(Connection conn, final String sql, final Object[] parms) throws SQLException {
        PreparedStatement st = null;
        st = conn.prepareStatement(sql);
        processParms(st, parms);
        return st.executeQuery();
    }

    protected ResultSet executeSQL(Connection conn, final String sql) throws SQLException {
        PreparedStatement st = null;
        st = conn.prepareStatement(sql);
        return st.executeQuery();
    }

    private Integer executeSQLUpdate(Connection conn, final String sql, final Object[] parms) throws SQLException {
        PreparedStatement st = null;
        st = conn.prepareStatement(sql);
        processParms(st, parms);
        return new Integer(st.executeUpdate());
    }

    private void processParms(PreparedStatement st, Object[] parms) throws SQLException {
        if (parms == null)
            return;

        int count = 0;
        for (Object parm : parms) {
            if (parm != null) {
                count++;
                if (parm instanceof Date)
                    st.setTimestamp(count, new Timestamp(((Date) parm).getTime()));
                else if (parm instanceof byte[])
                    st.setBytes(count, (byte[]) parm);
                else if (parm instanceof Enum)
                    st.setString(count, ((Enum<?>) parm).name());
                else if (parm instanceof Character)
                    st.setString(count, String.valueOf(parm));
                else
                    st.setObject(count, parm);
            }
        }
    }

    private void load(P bl, ResultSet data) throws SQLException {
        // Build a Map of column names/values
        Map<String, Object> columns = new HashMap<String, Object>();
        columns.put(TABLE_KEY, bl.getClass().getSimpleName());
        ResultSetMetaData meta = data.getMetaData();
        int count = meta.getColumnCount();
        for (int i = 1; i <= count; i++)
            columns.put(meta.getColumnName(i).toUpperCase(), data.getObject(i));
        loadColumns(bl, columns);
    }

    public abstract P createInstance();

    public abstract P findByPK(Object pk) throws SQLException;

    public abstract int insert(P bl) throws SQLException;

    public abstract int update(P bl) throws SQLException;

    public abstract int remove(P bl) throws SQLException;

    protected abstract Map<String, Object> buildColumns(P bl);

    protected abstract void loadColumns(P bl, Map<String, Object> columns);
}
