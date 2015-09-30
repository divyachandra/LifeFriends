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
public class UserStory extends BaseBean{
    private int userStoryId;
    private String title;
    private String description;
    private int userId;
    private boolean active;

    public int getUserStoryId() {
        return userStoryId;
    }

    public void setUserStoryId(int userStoryId) {
        this.userStoryId = userStoryId;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    private static DAO doa = new DAO();

    public static DAO getDoa() { return new DAO(); }

    public static class DAO extends BaseDAO<UserStory>  {
        private static final String TABLE = "UserStory";
        private static final String PK_COLUMN = "UserStoryId";

        protected DAO() {
            super(true);
        }


        @Override
        public UserStory createInstance() {
            return new UserStory();
        }

        @Override
        public UserStory findByPK(Object pk) throws SQLException {
            return findByPK(pk, TABLE, PK_COLUMN);
        }

        @Override
        public int insert(UserStory bl) throws SQLException {
            return insert(bl, TABLE, PK_COLUMN);
        }

        @Override
        public int update(UserStory bl) throws SQLException {
            return update(bl, TABLE, PK_COLUMN);
        }

        @Override
        public int remove(UserStory bl) throws SQLException {
            return remove(bl, TABLE, PK_COLUMN);
        }

        @Override
        protected Map<String, Object> buildColumns(UserStory bl) {
            Map<String, Object> map = new HashMap<String, Object>();
            putInt(map, "UserStoryId", bl.getUserStoryId());
            putString(map, "Title", bl.getTitle(), false);
            putString(map, "Description", bl.getDescription(), false);
            putInt(map, "UserId", bl.getUserId());
            putBoolean(map, "Active", bl.isActive());
            return map;
        }

        @Override
        protected void loadColumns(UserStory bl, Map<String, Object> columns) {
            bl.setPk(getObject(columns, PK_COLUMN));
            bl.setUserStoryId(getInt(columns, "UserStoryId"));
            bl.setTitle(getString(columns, "Title"));
            bl.setDescription(getString(columns, "Description"));
            bl.setUserId(getInt(columns, "UserId"));
            bl.setActive(getBool(columns, "Active"));
        }

        public Collection<UserStory> searchAll() throws SQLException {
            return super.search(TABLE, null, null, null, PK_COLUMN);
        }
    }
}
