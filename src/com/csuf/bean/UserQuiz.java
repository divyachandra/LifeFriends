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
public class UserQuiz extends BaseBean {
    private int userId;
    private boolean bloodTransfusion;
    private boolean smoking;
    private boolean drugs;
    private boolean alcohol;
    private boolean tattoo;
    private int weight;
    private int height;
    private boolean active;

    public int getUserQuizId() {
        return (Integer) getPk();
    }

    public void setUserQuizId(int userQuizId) {
        setPk(new Integer(userQuizId));
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isBloodTransfusion() {
        return bloodTransfusion;
    }

    public void setBloodTransfusion(boolean bloodTransfusion) {
        this.bloodTransfusion = bloodTransfusion;
    }

    public boolean isSmoking() {
        return smoking;
    }

    public void setSmoking(boolean smoking) {
        this.smoking = smoking;
    }

    public boolean isDrugs() {
        return drugs;
    }

    public void setDrugs(boolean drugs) {
        this.drugs = drugs;
    }

    public boolean isAlcohol() {
        return alcohol;
    }

    public void setAlcohol(boolean alcohol) {
        this.alcohol = alcohol;
    }

    public boolean isTattoo() {
        return tattoo;
    }

    public void setTattoo(boolean tattoo) {
        this.tattoo = tattoo;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    private static DAO dao = new DAO();

    public static DAO  getDao() { return dao; }

    public static class DAO extends BaseDAO<UserQuiz> {
        private static final String TABLE = "UserQuiz";
        private static final String PK_COLUMN = "UserQuizID";

        protected DAO() {
            super(true);
        }

        @Override
        public UserQuiz createInstance() {
            return new UserQuiz();
        }

        @Override
        public UserQuiz findByPK(Object pk) throws SQLException {
            return findByPK(pk, TABLE, PK_COLUMN );
        }

        @Override
        public int insert(UserQuiz bl) throws SQLException {
            return insert(bl, TABLE, PK_COLUMN);
        }

        @Override
        public int update(UserQuiz bl) throws SQLException {
            return update(bl, TABLE, PK_COLUMN);
        }

        @Override
        public int remove(UserQuiz bl) throws SQLException {
            return remove(bl, TABLE, PK_COLUMN);
        }

        @Override
        protected Map<String, Object> buildColumns(UserQuiz bl) {
            Map<String, Object> map = new HashMap<String, Object>();
            putInt(map, "UserId", bl.getUserId());
            putBoolean(map, "BloodTransfusion", bl.isBloodTransfusion());
            putBoolean(map, "Smoking", bl.isSmoking());
            putBoolean(map, "Drugs", bl.isDrugs());
            putBoolean(map, "Alcohol", bl.isAlcohol());
            putBoolean(map, "Tattoo", bl.isTattoo());
            putInt(map, "Weight", bl.getWeight());
            putInt(map, "Height", bl.getHeight());
            putBoolean(map, "Active", bl.isActive());
            return map;
        }

        @Override
        protected void loadColumns(UserQuiz bl, Map<String, Object> columns) {
            bl.setPk(getObject(columns, PK_COLUMN));
            bl.setUserId(getInt(columns, "UserId"));
            bl.setBloodTransfusion(getBool(columns, "BloodTransfusion"));
            bl.setSmoking(getBool(columns, "Smoking"));
            bl.setDrugs(getBool(columns, "Drugs"));
            bl.setAlcohol(getBool(columns, "Alcohol"));
            bl.setTattoo(getBool(columns, "Tattoo"));
            bl.setWeight(getInt(columns, "Weight"));
            bl.setHeight(getInt(columns, "Height"));
            bl.setActive(getBool(columns, "Active"));
        }

        public Collection<UserQuiz> searchAll() throws SQLException {
          return super.search(TABLE, null, null, null, PK_COLUMN);
        }
    }
}
