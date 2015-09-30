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
public class Event extends BaseBean {
    private int eventId;
    private String type;
    private String title;
    private String description;
    private Date startDate;
    private Date endDate;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String country;
    private Date createdDate;
    private boolean active;

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    private static DAO dao = new DAO();

    public static DAO getDao() { return dao; }

    public static class DAO extends BaseDAO<Event> {

        private static final String TABLE = "Event";
        private static final String PK_COLUMN = "EventID";

        protected DAO() { super(true); }

        @Override
        public Event createInstance() {
            return new Event();
        }

        @Override
        public Event findByPK(Object pk) throws SQLException {
            return findByPK(pk, TABLE, PK_COLUMN);
        }

        @Override
        public int insert(Event bl) throws SQLException {
            return insert(bl, TABLE, PK_COLUMN);
        }

        @Override
        public int update(Event bl) throws SQLException {
            return update(bl, TABLE, PK_COLUMN);
        }

        @Override
        public int remove(Event bl) throws SQLException {
            return remove(bl, TABLE, PK_COLUMN);
        }

        @Override
        protected Map<String, Object> buildColumns(Event bl) {
            Map<String, Object> map = new HashMap<String, Object>();
            putInt(map, "EventId", bl.getEventId());
            putString(map, "Type", bl.getType(), false);
            putString(map, "Title", bl.getTitle(), false);
            putString(map, "Description", bl.getDescription(), false);
            putDate(map, "StartDate", bl.getStartDate());
            putDate(map, "EndDate", bl.getEndDate());
            putString(map, "Address", bl.getAddress(), false);
            putString(map, "City", bl.getCity(), false);
            putString(map, "State", bl.getState(), false);
            putString(map, "Zip", bl.getZip(), false);
            putString(map, "Country", bl.getCountry(), false);
            putDate(map, "CreatedDate", bl.getCreatedDate());
            putBoolean(map, "Active", bl.isActive());
            return map;
        }

        @Override
        protected void loadColumns(Event bl, Map<String, Object> columns) {
            bl.setPk(getObject(columns, PK_COLUMN));
            bl.setEventId(getInt(columns, "EventID"));
            bl.setType(getString(columns, "Type"));
            bl.setTitle(getString(columns, "Title"));
            bl.setType(getString(columns, "Description"));
            bl.setStartDate(getDate(columns, "StartDate"));
            bl.setEndDate(getDate(columns, "EndDate"));
            bl.setAddress(getString(columns, "Address"));
            bl.setCity(getString(columns, "City"));
            bl.setState(getString(columns, "State"));
            bl.setZip(getString(columns, "Zip"));
            bl.setCountry(getString(columns, "Country"));
            bl.setCreatedDate(getDate(columns, "CreatedDate"));
            bl.setActive(getBool(columns, "Active"));
        }

        public Collection<Event> searchAll() throws SQLException{
            return super.search(TABLE, null, null, null, PK_COLUMN);
        }
    }
}
