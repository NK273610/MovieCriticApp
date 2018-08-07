package com.dalhousie.moviecritic.service;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;

import com.dalhousie.moviecritic.Data.User;
import com.dalhousie.moviecritic.dao.IUserDAO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dalhousie.moviecritic.Data.PasswordSalt;
import com.dalhousie.moviecritic.dao.UserDAOImpl;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceImplTest {

	@Mock
	UserDAOImpl userDao = new UserDAOImpl();
	
	@InjectMocks
	UserServiceImpl impl = new UserServiceImpl();
	
	@Mock
	PasswordSalt salt;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void registerUserTest(){
		User user = new User();
		Mockito.when(userDao.registerUser(user)).thenReturn(true);
		assertTrue(impl.registerUser(user));
	}

	@Test
	public void existingUserTest(){
		User user = new User();
		user.setUseremail("rahul@gmail.com");
		Mockito.when(userDao.getRegisteredUserByEmail(user.getUseremail())).thenReturn(true);
		assertTrue(impl.isExistingUser("rahul@gmail.com"));
	}




	@Test
	public void isUserUnavailableTest(){
		User user = new User();
		user.setUsername("rahul");
		Mockito.when(userDao.getRegisteredUserByUsername(user.getUsername())).thenReturn(true);
		assertTrue(impl.isUserUnavailable("rahul"));
	}

	@Test
	public void validateUserFalseTest() throws NoSuchAlgorithmException, IllegalArgumentException, SQLException  {
		PasswordHashingService service = new PasswordHashingService();
		PasswordSalt hashPassword = service.encryptPassword("5678");
		Mockito.doReturn(hashPassword).when(userDao).validateUser(Mockito.anyString());
		assertFalse(impl.validateUser("1234", "1234"));
	}
	
	
	@Test
	public void validateUserTrueTest() throws NoSuchAlgorithmException, IllegalArgumentException, SQLException  {
		PasswordHashingService service = new PasswordHashingService();
		PasswordSalt hashPassword = service.encryptPassword("1234");
		Mockito.doReturn(hashPassword).when(userDao).validateUser(Mockito.anyString());
		assertEquals(true, impl.validateUser("1234", "1234"));
	}
	
	@Test
	public void changePasswordTest() throws NoSuchAlgorithmException, IllegalArgumentException, SQLException  {
		PasswordHashingService service = new PasswordHashingService();
		PasswordSalt hashPassword = service.encryptPassword("1234");
		User user = Mockito.mock(User.class);
		Mockito.doReturn(1).when(userDao).changePassword(Mockito.any());
		assertEquals(1, impl.changePassword(user, "1234"));
	}
	
	@Test(expected=NoSuchAlgorithmException.class)
	public void changePasswordExceptionTest() throws NoSuchAlgorithmException, IllegalArgumentException, SQLException  {
		PasswordHashingService service = new PasswordHashingService();
		PasswordSalt hashPassword = service.encryptPassword("1234");
		User user = Mockito.mock(User.class);
		Mockito.doReturn(1).when(userDao).changePassword(Mockito.any());
		assertEquals(1, impl.changePassword(user, "1234"));
	}
	
	
}
