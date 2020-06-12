package com.revature.test.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import com.revature.daos.UserDao;
import com.revature.exceptions.HttpStatusException;
import com.revature.models.User;
import com.revature.services.UserService;

public class UserServiceTest {

	UserDao userDao;

	UserService userService = new UserService(userDao);

	// Runs once, prior to any tests in this class
	@BeforeClass
	public static void testSetup() {
		
	}
	
	// Runs once, after all tests have finished
	@AfterClass
	public static void testTeardown() {
		
	}
	
	
	/* Runs before each @Test */
	@Before
	public void setup() {
		// pre-test setup
		userDao = Mockito.mock(UserDao.class);
	}
	
	/* Runs after each test */
	@After
	public void postTestCleanup() {

	}
	
	@Test
	public void myTest() {
		User user = new User(1, "email@email.email", LocalDate.of(2000, 1, 1), "Test User");

		// stub method on userDao
		// When userDao.insertUser is called with user then return user
		when(userDao.insertUser(user)).thenReturn(user);

		User result = userService.insertUser(user);

		assertEquals("Method should cleanly finish and return user from the userDaos return", user, result);
	}

	@Test(expected=HttpStatusException.class)
	public void testMissingEmail() {
		User user = new User(1, null, LocalDate.of(2000, 1, 1), "Test User");
		when(userDao.insertUser(user)).thenReturn(user);

		User result = userService.insertUser(user);

		// The code above should throw an exception, we should not reach this point
		fail();
	}
	
	@Test
	public void testEmptyStringEmail() {
		User user = new User(1, "", LocalDate.of(2000, 1, 1), "Test User");
		when(userDao.insertUser(user)).thenReturn(user);

		try {
			User result = userService.insertUser(user);			
			// The code above should throw an exception, we should not reach this point
			fail("insertUser should throw an exception for an empty string");
		} catch(HttpStatusException e) {
			assertEquals("Status code should be 422", e.getStatusCode(), 422);
		}
	}
	
}
