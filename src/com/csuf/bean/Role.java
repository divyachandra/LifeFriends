package com.csuf.bean;

import com.csuf.base.BaseBean;
import com.csuf.base.BaseDAO;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Divya on 26-09-2015.
 */
public class Role extends BaseBean {
    public static int ADMIN = 1;
    public static int USER = 2;

    private String name;
    private boolean active;

    public int getRoleId() {
        return (Integer) getPk();
    }

    public void setRoleId(int roleId) {
        setPk(new Integer(roleId));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    private static DAO dao = new DAO();

    public static DAO getDao() { return dao; }

    public static class DAO extends BaseDAO<Role> {
        private static final String TABLE = "Role";
        private static final String PK_COLUMN = "RoleID";

        protected DAO() {
            super(true);
        }


        @Override
        public Role createInstance() {
            return new Role();
        }

        @Override
        public Role findByPK(Object pk) throws SQLException {
            return findByPK(pk, TABLE, PK_COLUMN);
        }

        @Override
        public int insert(Role bl) throws SQLException {
            throw new SQLException("Unsupported operation");
        }

        @Override
        public int update(Role bl) throws SQLException {
            throw new SQLException("Unsupported operation");
        }

        @Override
        public int remove(Role bl) throws SQLException {
            throw new SQLException("Unsupported operation");
        }

        @Override
        protected Map<String, Object> buildColumns(Role bl) {
            Map<String, Object> map = new HashMap<String, Object>();
            putString(map, "Name", bl.getName(), false);
            putBoolean(map, "Active", bl.isActive());
            return map;
        }

        @Override
        protected void loadColumns(Role bl, Map<String, Object> columns) {
            bl.setPk(getObject(columns, PK_COLUMN));
            bl.setName(getString(columns, "Name"));
            bl.setActive(getBool(columns, "Active"));
        }

        public Collection<Role> searchAll() throws SQLException {
            return super.search(TABLE, null, null, null, PK_COLUMN);
        }
    }
}

