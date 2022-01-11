package by.petrovich.storage.dao.impl;

import by.petrovich.storage.dao.DaoException;
import by.petrovich.storage.entity.User;
import junit.framework.TestCase;

public class UserDaoImplTest extends TestCase {
    private UserDaoImpl userDao;
    User user = new User();

    public void testCreate() throws DaoException {
        userDao.create(user);
//        assertThat();
    }
}