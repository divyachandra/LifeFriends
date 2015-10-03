package com.csuf.bean;

import com.csuf.BaseTestCase;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;

/**
 * Created by Divya on 27-09-2015.
 */
public class DatabaseTest extends BaseTestCase {

    @Test
    public void event() throws SQLException {
        String name  = Event.class.getSimpleName();

        Event event = Event.getDao().createInstance();
        event.setType("Test");
        event.setTitle("Blood Drive");
        event.setDescription("This is a blood Drive");
        event.setStartDate(new Date());
        event.setAddress("3301 S Bear St");
        event.setCity("Irvine");
        event.setState("California");
        event.setZip("92704");
        event.setCountry("USA");

        // insert
        int insert = Event.getDao().insert(event);
        assertEquals(name + " insert failed", 1, insert);
        log.info("insert success");

        // update
        event.setType("updated");
        int update = Event.getDao().update(event);
        assertEquals(name + " update failed", 1, update);
        log.info("update success");

        // find
        Event findEvent = Event.getDao().findByPK(event.getPk());
        assertEquals(name + " find failed", event.getEventId(), findEvent.getEventId());
        log.info("find success");

        // search
        Collection<Event> search = Event.getDao().searchAll();
        assertFalse(name + " search failed", search.isEmpty());
        log.info("search success");

        // remove
        int remove = Event.getDao().remove(event);
        assertEquals(name + " remove failed", 1, remove);
        log.info("remove success");
    }

    @Test
    public void user() throws SQLException {
        String name  = User.class.getSimpleName();

        User user = User.getDao().createInstance();
        user.setEmail("aa@aa.com");
        user.setPassword("testing");
        user.setFirstName("Divya");
        user.setLastName("Chan");
        user.setDOB(new Date());
        user.setPhone("562 555 5555");
        user.setAddress("335 Belflowar");
        user.setCity("Irvine");
        user.setState("California");
        user.setZip("92704");
        user.setCountry("USA");
        user.setBloodGroup("AB+");
        user.setGender("Female");
        user.setDonor(true);
        user.setRoleId(Role.USER);

        // insert
        int insert = User.getDao().insert(user);
        assertEquals(name + " insert failed", 1, insert);
        log.info("insert success");

        // update
        user.setLastName("Ama");
        int update = User.getDao().update(user);
        assertEquals(name + " update failed", 1, update);
        log.info("update success");

        // find
        User findUser = User.getDao().findByPK(user.getUserId());
        assertEquals(name + " find failed", user.getUserId(), findUser.getUserId());
        log.info("find success");

        // search
        Collection<User> search = User.getDao().searchAll();
        assertFalse(name + " search failed", search.isEmpty());
        log.info("search success");

        // remove
        int remove = User.getDao().remove(user);
        assertEquals(name + " remove failed", 1, remove);
        log.info("remove success");
    }

    @Test
    public void userQuiz() throws SQLException {
        String name  = UserQuiz.class.getSimpleName();

        User user = getNewUser();
        int insertUser = User.getDao().insert(user);
        assertEquals(name + " insert failed", 1, insertUser);
        log.info("--- user created");

        UserQuiz userQuiz = UserQuiz.getDao().createInstance();
        userQuiz.setUserId(user.getUserId());
        userQuiz.setBloodTransfusion(false);
        userQuiz.setSmoking(false);
        userQuiz.setDrugs(false);
        userQuiz.setAlcohol(false);
        userQuiz.setTattoo(false);
        userQuiz.setWeight(120);
        userQuiz.setHeight(200);

        // insert
        int insert = UserQuiz.getDao().insert(userQuiz);
        assertEquals(name + " insert failed", 1, insert);
        log.info("insert success");

        // update
        userQuiz.setSmoking(true);
        int update = UserQuiz.getDao().update(userQuiz);
        assertTrue(name + " update failed", userQuiz.isSmoking());
        log.info("update success");

        // find
        UserQuiz findUserQuiz = UserQuiz.getDao().findByPK(userQuiz.getUserQuizId());
        assertEquals(name + " find failed", userQuiz.getUserQuizId(), findUserQuiz.getUserQuizId());
        log.info("find success");

        // search
        Collection<UserQuiz> search = UserQuiz.getDao().searchAll();
        assertFalse(name + " search failed", search.isEmpty());
        log.info("search success");

        // remove
        int remove = UserQuiz.getDao().remove(userQuiz);
        assertEquals(name + " remove failed", 1, remove);
        log.info("remove success");

        int removeUser = User.getDao().remove(user);
        assertEquals(name + " remove failed", 1, removeUser);
        log.info("--- removed user");
    }

    @Test
    public void userStory() throws SQLException {
        String name  = UserStory.class.getSimpleName();

        User user = getNewUser();
        int insertUser = User.getDao().insert(user);
        assertEquals(name + " insert failed", 1, insertUser);
        log.info("--- user created");

        UserStory userStory = UserStory.getDao().createInstance();
        userStory.setUserId(user.getUserId());
        userStory.setTitle("Blood Drive");
        userStory.setDescription("This is a blood Drive");

        // insert
        int insert = UserStory.getDao().insert(userStory);
        assertEquals(name + " insert failed", 1, insert);
        log.info("insert success");

        // update
        userStory.setTitle("Tame");
        int update = UserStory.getDao().update(userStory);
        assertEquals(name + " update failed", 1, update);
        log.info("update success");

        // find
        UserStory findUserStory = UserStory.getDao().findByPK(userStory.getUserStoryId());
        assertEquals(name + " find failed", userStory.getUserStoryId(), findUserStory.getUserStoryId());
        log.info("find success");

        // search
        Collection<UserStory> search = UserStory.getDao().searchAll();
        assertFalse(name + " search failed", search.isEmpty());
        log.info("search success");

        // remove
        int remove = UserStory.getDao().remove(userStory);
        assertEquals(name + " remove failed", 1, remove);
        log.info("remove success");

        int removeUser = User.getDao().remove(user);
        assertEquals(name + " remove failed", 1, removeUser);
        log.info("--- removed user");
    }

    private User getNewUser() {
        User user = User.getDao().createInstance();
        user.setEmail("aa@aa.com");
        user.setPassword("testing");
        user.setFirstName("Divya");
        user.setLastName("Chan");
        user.setDOB(new Date());
        user.setPhone("562 555 5555");
        user.setAddress("335 Belflowar");
        user.setCity("Irvine");
        user.setState("California");
        user.setZip("92704");
        user.setCountry("USA");
        user.setBloodGroup("AB+");
        user.setGender("Female");
        user.setDonor(true);
        user.setRoleId(Role.USER);

        return user;
    }
}
