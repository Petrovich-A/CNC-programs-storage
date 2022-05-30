///**
// * 
// */
//package by.petrovich.storage.service.impl;
//
//import static org.junit.Assert.fail;
//
//import java.sql.Timestamp;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import org.junit.Assert;
//import org.junit.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//
//import by.petrovich.storage.entity.EmployeePosition;
//import by.petrovich.storage.entity.RegistrationUserInfo;
//import by.petrovich.storage.entity.User;
//import by.petrovich.storage.entity.UserRole;
//import by.petrovich.storage.service.ServiceException;
//import by.petrovich.storage.service.ServiceProvider;
//import by.petrovich.storage.service.UserService;
//
///**
// * @author Petrovich A.V.
// *
// */
//public class UserServiceImplTest {
//	private final UserService userService = new UserServiceImpl();
//	private String passwordValid = "wdf123wef*";
//	private String passwordData = "wdf123wef*";
//	private String passwordConfirm = "wdf123wef*";
//	private String passwordNotMatch = "wdf458wef*";
//	private String passwordInvalid = "wdfqdwdwef";
//	private String passwordEmpty = "";
//	private int loginValid = 15000;
//	private int loginData = 15000;
//	private int loginInvalid = 842475;
////	private User userHome = new User(12001, "C8XW84YAP6m1sUXOg9Q7+UEa9cR7lQiIh+DJY8gnfJ4=", "Вася", "Пупкин",
////			"Владимирович", EmployeePosition.CNC_PROGRAMMER, "wef@ef.ef", Timestamp.valueOf("2022-03-23 18:47:41"),
////			UserRole.USER);
////	private User userHomeWithRoleUser = new User(12001, "C8XW84YAP6m1sUXOg9Q7+UEa9cR7lQiIh+DJY8gnfJ4=", "Вася",
////			"Пупкин", "Владимирович", EmployeePosition.CNC_PROGRAMMER, "wef@ef.ef",
////			Timestamp.valueOf("2022-03-23 18:47:41"), UserRole.USER);
////	private User userWork = new User(11000, "ghbdtn00!", "John", "Smith", "Ivanovich",
////			EmployeePosition.ENGINEERING_TECHNICIAN, "john123@mail.com", Timestamp.valueOf("2022-02-25 08:38:30"),
////			UserRole.USER);
////	private User userWork2 = new User(11000, "U/m9XgBquMvtjkFj3Nv3kJCp/EQML2cEp7+psQeMyR8=", "John", "Smith",
////			"Ivanovich", EmployeePosition.ENGINEERING_TECHNICIAN, "john123@mail.com",
////			Timestamp.valueOf("2022-02-25 08:38:30"), UserRole.USER);
////	private User userWorkInBD = new User(11000, "U/m9XgBquMvtjkFj3Nv3kJCp/EQML2cEp7+psQeMyR8=", "John", "Smith",
////			"Ivanovich", EmployeePosition.ENGINEERING_TECHNICIAN, "john123@mail.com",
////			Timestamp.valueOf("2022-02-25 08:38:30"), UserRole.USER);
////	private User userNotExist = new User(18000, "U/m9XgBquMvtjkFj3Nv3kJCp/EQML2cEp7+psQeMyR8=", "John", "Smith",
////			"Ivanovich", EmployeePosition.ENGINEERING_TECHNICIAN, "john123@mail.com",
////			Timestamp.valueOf("2022-02-25 08:38:30"), UserRole.GUEST);
////	private User userWorkInBD2 = new User(15000, "TERTHgbk0sd6KMvchPI94wvN7A+KgtPy6bQmVfU5ODc=", "Василий", "Васильев",
////			"Васильевич", EmployeePosition.ENGINEERING_TECHNICIAN, "vasiliy@tut.by",
////			Timestamp.valueOf("2022-03-29 10:42:22"), UserRole.GUEST);
//	private List<User> allusers = new ArrayList<>();
//	private RegistrationUserInfo registrationUserInfo = new RegistrationUserInfo.RegistrationUserInfoBuilder()
//			.withPersonnelNumber(42001).build();
//	private RegistrationUserInfo registrationUserInfoValid = new RegistrationUserInfo.RegistrationUserInfoBuilder()
//			.withPersonnelNumber(12000).withEmployeeName("John").withEmployeeSurname("Smith").withPassword("wwefew@@34")
//			.withConfirmPassword("wwefew@@34").withEmployeePatronymic("Smitovich")
//			.withEmployeePosition(EmployeePosition.ENGINEERING_TECHNICIAN).withEmail("america@usa.com")
//			.withСreationDate(Timestamp.valueOf("2022-05-16 15:38:30")).withUserRole(UserRole.USER).build();
//	private RegistrationUserInfo registrationUserInfoInvalidPassword = new RegistrationUserInfo.RegistrationUserInfoBuilder()
//			.withPersonnelNumber(12000).withEmployeeName("John").withEmployeeSurname("Smith")
//			.withEmployeePatronymic("Smitovich").withEmployeePosition(EmployeePosition.ENGINEERING_TECHNICIAN)
//			.withEmail("america@usa.com").withPassword("nmverg24").withConfirmPassword("nmverg24").build();
//
//	/**
//	 * 
//	 */
//	@Mock
//	ServiceProvider serviceProviderMock;
//	UserService userServiceMock;
//
//	/**
//	 * 
//	 */
//	@InjectMocks
//	ServiceProvider serviceProviderMock1 = ServiceProvider.getInstance();
//	UserService userServiceMock1 = serviceProviderMock1.getUserService();
//
//	/**
//	 * Test method for
//	 * {@link by.petrovich.storage.service.impl.UserServiceImpl#readAllUsers()}.
//	 */
//	@Test
//	public void readAllUsers() throws ServiceException {
//		fail("Not yet implemented");
//	}
//
//	/**
//	 * Test method for
//	 * {@link by.petrovich.storage.service.impl.UserServiceImpl#isPasswordsMatch(java.lang.String, java.lang.String)}.
//	 * 
//	 * @throws ServiceException
//	 */
//	@Test
//	public void isPasswordsMatch() throws ServiceException {
//		boolean positive = userService.isPasswordsMatch(passwordData, passwordConfirm);
//		boolean negative = userService.isPasswordsMatch(passwordData, passwordNotMatch);
//		Assert.assertTrue(positive);
//		Assert.assertFalse(negative);
//	}
//
//	/**
//	 * Test method for
//	 * {@link by.petrovich.storage.service.impl.UserServiceImpl#isLoginAndPasswordMatchWithDateBaseData(int, java.lang.String)}.
//	 * 
//	 * @throws ServiceException
//	 */
//	@Test
//	public void isLoginAndPasswordMatchWithDateBaseData() throws ServiceException {
//		boolean positive = userService.isLoginAndPasswordMatchWithDateBaseData(userWork.getPersonnelNumber(),
//				userWork.getPassword());
//		boolean negative = userService.isLoginAndPasswordMatchWithDateBaseData(userNotExist.getPersonnelNumber(),
//				userNotExist.getPassword());
//		Assert.assertTrue(positive);
//		Assert.assertFalse(negative);
//	}
//
//	/**
//	 * Test method for
//	 * {@link by.petrovich.storage.service.impl.UserServiceImpl#isLoginAndPasswordValid(int, java.lang.String)}.
//	 * 
//	 * @throws ServiceException
//	 */
//	@Test
//	public void isLoginAndPasswordValid() throws ServiceException {
//		boolean positive = userService.isLoginAndPasswordValid(loginValid, passwordValid);
//		boolean negativeLoginInvalid = userService.isLoginAndPasswordValid(loginInvalid, passwordValid);
//		boolean negativePasswordInvalid = userService.isLoginAndPasswordValid(loginValid, passwordInvalid);
//		boolean negiveBothInvalid = userService.isLoginAndPasswordValid(loginInvalid, passwordInvalid);
//		boolean negivePassEmpty = userService.isLoginAndPasswordValid(loginInvalid, passwordEmpty);
//		Assert.assertTrue(positive);
//		Assert.assertFalse(negativeLoginInvalid);
//		Assert.assertFalse(negativePasswordInvalid);
//		Assert.assertFalse(negiveBothInvalid);
//		Assert.assertFalse(negivePassEmpty);
//	}
//
//	/**
//	 * Test method for
//	 * {@link by.petrovich.storage.service.impl.UserServiceImpl#readUserByPersonnelNumber(int)}.
//	 */
//	@Test
//	public void readUserByPersonnelNumber() throws ServiceException {
//		User expected = userWorkInBD;
//		Optional<User> userOptionalExisted = userService.readUserByPersonnelNumber(userWorkInBD.getPersonnelNumber());
//		Optional<User> userOptionalNotExisted = userService
//				.readUserByPersonnelNumber(userNotExist.getPersonnelNumber());
//		User positive = userOptionalExisted.isPresent() ? userOptionalExisted.get() : new User();
//		User negative = userOptionalNotExisted.isPresent() ? userOptionalNotExisted.get() : new User();
//		Assert.assertEquals(expected, positive);
//		Assert.assertNotEquals(expected, negative);
//	}
//
//	/**
//	 * Test method for
//	 * {@link by.petrovich.storage.service.impl.UserServiceImpl#authorizateUser(int, java.lang.String)}.
//	 */
//	@Test
//	public void authorizateUser() throws ServiceException {
//		User expected = userWorkInBD;
//		Optional<User> userOptionalPositive = userService.authorizateUser(userWorkInBD.getPersonnelNumber(),
//				userWorkInBD.getPassword());
//		Optional<User> userOptionalNegative = userService.authorizateUser(userWorkInBD2.getPersonnelNumber(),
//				userWorkInBD2.getPassword());
//		User positive = userOptionalPositive.isPresent() ? userOptionalPositive.get() : new User();
//		User negative = userOptionalNegative.isPresent() ? userOptionalNegative.get() : new User();
//		Assert.assertEquals(expected, positive);
//		Assert.assertNotEquals(expected, negative);
//	}
//
//	/**
//	 * Test method for
//	 * {@link by.petrovich.storage.service.impl.UserServiceImpl#registrateUser(by.petrovich.storage.entity.RegistrationUserInfo)}.
//	 * 
//	 * @throws ServiceException
//	 */
//	@Test
//	public void testRegistrateUser() throws ServiceException {
////		doNothing().when(userServiceMock1).registrateUser(registrationUserInfoValid);
////		verify(userServiceMock1, times(1)).registrateUser(registrationUserInfoValid);
//	}
//
//	/**
//	 * Test method for
//	 * {@link by.petrovich.storage.service.impl.UserServiceImpl#logOut(by.petrovich.storage.entity.User)}.
//	 * 
//	 * @throws ServiceException
//	 */
//	@Test
//	public void testLogOut() throws ServiceException {
////		verify(userService, times(1)).logOut(user);
//	}
//
//	/**
//	 * Test method for
//	 * {@link by.petrovich.storage.service.impl.UserServiceImpl#update(by.petrovich.storage.entity.User, int)}.
//	 * 
//	 * @throws ServiceException
//	 */
//	@Test
//	public void testUpdate() throws ServiceException {
////		verify(userService, times(1)).update(user, personnelNumber);
//	}
//
//	/**
//	 * Test method for
//	 * {@link by.petrovich.storage.service.impl.UserServiceImpl#isValid(by.petrovich.storage.entity.RegistrationUserInfo)}.
//	 */
//	@Test
//	public void isValid() throws ServiceException {
//		boolean positive = userService.isValid(registrationUserInfoValid);
//		boolean negativePassword = userService.isValid(registrationUserInfoInvalidPassword);
//		Assert.assertTrue(positive);
//		Assert.assertFalse(negativePassword);
//	}
//
//	/**
//	 * Test method for
//	 * {@link by.petrovich.storage.service.impl.UserServiceImpl#isUserExist(int)}.
//	 */
//	@Test
//	public void isUserExist() throws ServiceException {
//		boolean positive = userService.isUserExist(userWorkInBD.getPersonnelNumber());
//		boolean negative = userService.isUserExist(userNotExist.getPersonnelNumber());
//		Assert.assertTrue(positive);
//		Assert.assertFalse(negative);
//	}
//
//}
