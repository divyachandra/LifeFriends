package com.csuf.bean;

import com.csuf.base.BaseBean;
import com.csuf.base.BaseDAO;

import java.sql.SQLException;
import java.time.DateTimeException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Divya on 26-09-2015.
 */
public class Hospital extends BaseBean {
    private int hospitalId;
    private String name;
    private String email;
    private String password;
    private String phone;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String country;
    private Date createdDate;
    private Date updatedDate;
    private boolean active;

    public int getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(int hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private static DAO dao = new DAO();

    public static DAO getDao() { return dao; }

    public static class DAO extends BaseDAO<Hospital>{
        private static final String TABLE = "Hospital";
        private static final String PK_COLUMN = "HospitalId";

        protected DAO() { super(true); }


        @Override
        public Hospital createInstance() {
            return new Hospital();
        }

        @Override
        public Hospital findByPK(Object pk) throws SQLException {
            return findByPK(pk, TABLE, PK_COLUMN);
        }

        @Override
        public int insert(Hospital bl) throws SQLException {
            return insert(bl, TABLE, PK_COLUMN);
        }

        @Override
        public int update(Hospital bl) throws SQLException {
            return update(bl, TABLE, PK_COLUMN);
        }

        @Override
        public int remove(Hospital bl) throws SQLException {
            return remove(bl, TABLE, PK_COLUMN);
        }

        @Override
        protected Map<String, Object> buildColumns(Hospital bl) {
            Map<String, Object> map = new HashMap<String, Object>();
            putInt(map, "HospitalId", bl.getHospitalId());
            putString(map, "Name", bl.getName(), false);
            putString(map, "Email", bl.getEmail(), false);
            putString(map, "Password", bl.getPassword(), false);
            putString(map, "Phone", bl.getPhone(), false);
            putString(map, "Address", bl.getAddress(), false);
            putString(map, "City", bl.getCity(), false);
            putString(map, "State", bl.getState(), false);
            putString(map, "Zip", bl.getZip(), false);
            putString(map, "Country", bl.getCountry(), false);
            putDate(map, "CreatedDate", bl.getCreatedDate());
            putDate(map, "UpdatedDate", bl.getUpdatedDate());
            putBoolean(map, "Active", bl.isActive());
            return map;
        }

        @Override
        protected void loadColumns(Hospital bl, Map<String, Object> columns) {
            bl.setPk(getObject(columns, PK_COLUMN));
            bl.setHospitalId(getInt(columns, "HospitalId"));
            bl.setName(getString(columns, "Name"));
            bl.setEmail(getString(columns, "Email"));
            bl.setPassword(getString(columns, "Password"));
            bl.setPhone(getString(columns, "Phone"));
            bl.setAddress(getString(columns, "Address"));
            bl.setCity(getString(columns, "City"));
            bl.setState(getString(columns, "State"));
            bl.setZip(getString(columns, "Zip"));
            bl.setCountry(getString(columns, "Country"));
            bl.setCreatedDate(getDate(columns, "CreatedDate"));
            bl.setUpdatedDate(getDate(columns, "UpdatedDate"));
            bl.setActive(getBool(columns, "Active"));
        }

        public Collection<Hospital> searchAll() throws SQLException {
           return super.search(TABLE, null, null, null, PK_COLUMN);
        }
    }
}
