/**
 * 
 */
package by.petrovich.storage.dao.impl;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import by.petrovich.storage.dao.DaoException;

/**
 * @author Petrovich A.V.
 *
 */
public class UserDaoImplTest {

	@Mock
	private UserDaoImpl userDaoImplMock;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
//		MockitoAnnotations.initMocks(daoProvider);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link by.petrovich.storage.dao.impl.UserDaoImpl#readAllUsers()}.
	 */
	@Test
	public void testReadAllUsers() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link by.petrovich.storage.dao.impl.UserDaoImpl#createUser(by.petrovich.storage.entity.RegistrationUserInfo)}.
	 */
	@Test
	public void testCreateUser() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link by.petrovich.storage.dao.impl.UserDaoImpl#readUserByPersonnelNumber(int)}.
	 */
	@Test
	public void testReadUserByPersonnelNumber() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link by.petrovich.storage.dao.impl.UserDaoImpl#update(by.petrovich.storage.entity.User, int)}.
	 */
	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link by.petrovich.storage.dao.impl.UserDaoImpl#isUserExist(int)}.
	 */
	@Test
	public void isUserExist_Should_Return_True() throws DaoException {
//		Mockito.when(userDaoImplMock.isUserExist(11000)).thenReturn(true);
//		boolean isExist = userDaoImplMock.isUserExist(11000);
//		Assert.assertEquals(isExist, true);
//		Assert.assertEquals(userDaoImplMock.isUserExist(11000), true);
	}

	/**
	 * Test method for
	 * {@link by.petrovich.storage.dao.impl.UserDaoImpl#updateUserRole(by.petrovich.storage.entity.User)}.
	 */
	@Test
	public void testUpdateUserRole() {
		fail("Not yet implemented");
	}

}
