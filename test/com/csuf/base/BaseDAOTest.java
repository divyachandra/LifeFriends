package com.csuf.base;

import com.csuf.BaseTestCase;
import com.csuf.bean.CertifiedUser;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collection;

/**
 * Created by Divya on 27-09-2015.
 */
public class BaseDAOTest extends BaseTestCase {

    @Test
    public void insert() throws SQLException {
        CertifiedUser cu = getCertifiedUser();
        int success = CertifiedUser.getDao().insert(cu);
        assertEquals(1, success);
    }

    @Test
    public void update() throws SQLException {
        CertifiedUser cu = getCertifiedUser();
        int response1 = CertifiedUser.getDao().insert(cu);
        assertEquals(1, response1);

        cu.setUserId(300);
        cu.setHospitalId(50);
        int response2 = CertifiedUser.getDao().update(cu);
        assertEquals(1, response2);
    }

    @Test
    public void remove() throws SQLException {
        CertifiedUser cu = getCertifiedUser();
        int response1 = CertifiedUser.getDao().insert(cu);
        assertEquals(1, response1);

        int response2 = CertifiedUser.getDao().remove(cu);
        assertEquals(1, response2);
    }

    @Test
    public void findPk() throws SQLException {
        CertifiedUser cu = getCertifiedUser();
        int response1 = CertifiedUser.getDao().insert(cu);
        assertEquals(1, response1);

        CertifiedUser cu2 = CertifiedUser.getDao().findByPK(cu.getPk());
        assertEquals(cu.getPk(), cu2.getPk());
    }

    @Test
    public void searchAll() throws SQLException {
        Collection<CertifiedUser> list = CertifiedUser.getDao().searchAll();
        assertFalse(list.isEmpty());
    }

    private CertifiedUser getCertifiedUser() {
        CertifiedUser cu = CertifiedUser.getDao().createInstance();
        cu.setUserId(99);
        cu.setHospitalId(2);
        return cu;
    }
}
