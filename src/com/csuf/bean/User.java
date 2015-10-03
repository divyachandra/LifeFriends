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
public class User extends BaseBean {
    private static DAO dao = new DAO();
    private String email;
    private String password;
    private String firstName;
    private String middleName;
    private String lastName;
    private Date createdDate;
    private Date updatedDate;
    private Date DOB;
    private String phone;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String country;
    private String bloodGroup;
    private String gender;
    private int roleId;
    private boolean active;
    private boolean donor;
    private boolean lifeCertified;
    private boolean officialCertified;

    public static DAO getDao() {
        return new DAO();
    }

    public int getUserId() {
        return (Integer) getPk();
    }

    public void setUserId(int userId) {
        setPk(new Integer(userId));
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
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

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isDonor() {
        return donor;
    }

    public void setDonor(boolean donor) {
        this.donor = donor;
    }

    public boolean isLifeCertified() {
        return lifeCertified;
    }

    public void setLifeCertified(boolean lifeCertified) {
        this.lifeCertified = lifeCertified;
    }

    public boolean isOfficialCertified() {
        return officialCertified;
    }

    public void setOfficialCertified(boolean officialCertified) {
        this.officialCertified = officialCertified;
    }

    public static class DAO extends BaseDAO<User> {
        private static final String TABLE = "User";
        private static final String PK_COLUMN = "UserID";

        protected DAO() {
            super(true);
        }


        @Override
        public User createInstance() {
            return new User();
        }

        @Override
        public User findByPK(Object pk) throws SQLException {
            return findByPK(pk, TABLE, PK_COLUMN);
        }

        @Override
        public int insert(User bl) throws SQLException {
            return insert(bl, TABLE, PK_COLUMN);
        }

        @Override
        public int update(User bl) throws SQLException {
            return update(bl, TABLE, PK_COLUMN);
        }

        @Override
        public int remove(User bl) throws SQLException {
            return remove(bl, TABLE, PK_COLUMN);
        }

        @Override
        protected Map<String, Object> buildColumns(User bl) {
            Map<String, Object> map = new HashMap<String, Object>();
            putString(map, "Email", bl.getEmail(), false);
            putString(map, "Password", bl.getPassword(), false);
            putString(map, "FirstName", bl.getFirstName(), false);
            putString(map, "MiddleName", bl.getMiddleName(), false);
            putString(map, "LastName", bl.getLastName(), false);
            putDate(map, "UpdatedDate", bl.getUpdatedDate());
            putDate(map, "DOB", bl.getDOB());
            putString(map, "Phone", bl.getPhone(), false);
            putString(map, "Address", bl.getAddress(), false);
            putString(map, "City", bl.getCity(), false);
            putString(map, "State", bl.getState(), false);
            putString(map, "Zip", bl.getZip(), false);
            putString(map, "Country", bl.getCountry(), false);
            putString(map, "BloodGroup", bl.getBloodGroup(), false);
            putString(map, "Gender", bl.getGender(), false);
            putInt(map, "RoleId", bl.getRoleId());
            putBoolean(map, "Active", bl.isActive());
            putBoolean(map, "IsDonor", bl.isDonor());
            putBoolean(map, "LifeCertified", bl.isLifeCertified());
            putBoolean(map, "OfficialCertified", bl.isOfficialCertified());
            return map;
        }

        @Override
        protected void loadColumns(User bl, Map<String, Object> columns) {
            bl.setPk(getObject(columns, PK_COLUMN));
            bl.setEmail(getString(columns, "Email"));
            bl.setPassword(getString(columns, "Password"));
            bl.setFirstName(getString(columns, "FirstName"));
            bl.setMiddleName(getString(columns, "MiddleName"));
            bl.setLastName(getString(columns, "LastName"));
            bl.setCreatedDate(getDate(columns, "CreatedDate"));
            bl.setUpdatedDate(getDate(columns, "UpdatedDate"));
            bl.setDOB(getDate(columns, "DOB"));
            bl.setPhone(getString(columns, "Phone"));
            bl.setAddress(getString(columns, "Address"));
            bl.setCity(getString(columns, "City"));
            bl.setState(getString(columns, "State"));
            bl.setZip(getString(columns, "Zip"));
            bl.setCountry(getString(columns, "Country"));
            bl.setBloodGroup(getString(columns, "BloodGroup"));
            bl.setGender(getString(columns, "Gender"));
            bl.setRoleId(getInt(columns, "RoleId"));
            bl.setActive(getBool(columns, "Active"));
            bl.setDonor(getBool(columns, "IsDonor"));
            bl.setLifeCertified(getBool(columns, "LifeCertified"));
            bl.setOfficialCertified(getBool(columns, "OfficialCertified"));
        }

        public Collection<User> searchAll() throws SQLException {
            return search(TABLE, null, null, null, PK_COLUMN);
        }

        public Collection<User> searchAllActive() throws SQLException {
            String filter = " Active = ? ";
            Object[] parms = {true};

            return search(TABLE, null, filter, parms, PK_COLUMN);
        }

        public User findByEmailAndPassword(String email, String password) throws SQLException {
            String filter = " Email = ? AND Password = ? ";
            Object[] parms = {email, password};

            return findByFilter(TABLE, null, filter, parms);
        }
    }
}

