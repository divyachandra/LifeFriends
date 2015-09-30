package com.csuf.bean;

import com.csuf.base.BaseBean;
import com.csuf.base.BaseDAO;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Divya on 26-09-2015.
 */
public class CertifiedUser extends BaseBean {
    private int userId;
    private int hospitalId;
    private Date createDate;
    private Date updateDate;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(int hospitalId) {
        this.hospitalId = hospitalId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    private static DAO dao = new DAO();

    public static DAO getDao() {
        return dao;
    }

    public static class DAO extends BaseDAO<CertifiedUser> {
        private static final String TABLE = "CertifiedUser";
        private static final String PK_COLUMN = "CertifiedUserID";

        public DAO() {
            super(false);
        }

        @Override
        public CertifiedUser createInstance() {
            return new CertifiedUser();
        }

        @Override
        public CertifiedUser findByPK(Object pk) throws SQLException {
            return findByPK(pk, TABLE, PK_COLUMN);
        }

        @Override
        public int insert(CertifiedUser bl) throws SQLException {
            return insert(bl, TABLE, PK_COLUMN);
        }

        @Override
        public int update(CertifiedUser bl) throws SQLException {
            return update(bl, TABLE, PK_COLUMN);
        }

        @Override
        public int remove(CertifiedUser bl) throws SQLException {
            return remove(bl, TABLE, PK_COLUMN);
        }

        @Override
        protected Map<String, Object> buildColumns(CertifiedUser bl) {
            Map<String, Object> map = new HashMap<String, Object>();
            putInt(map, "UserID", bl.getUserId());
            putInt(map, "HospitalID", bl.getHospitalId());
            putDate(map, "UpdatedDate", bl.getUpdateDate());
            return map;
        }

        @Override
        protected void loadColumns(CertifiedUser bl, Map<String, Object> columns) {
            bl.setPk(getObject(columns, PK_COLUMN));
            bl.setUserId(getInt(columns, "UserID"));
            bl.setHospitalId(getInt(columns, "HospitalID"));
            bl.setCreateDate(getDate(columns, "CreatedDate"));
            bl.setUpdateDate(getDate(columns, "UpdatedDate"));
        }


        public Collection<CertifiedUser> searchAll() throws SQLException {
            return super.search(TABLE, null, null, null, PK_COLUMN);
        }
    }
}
