package com.mycompany.javapoe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

////////////

/////  I watched tutorials on how to make a test class in NetBeans and used ChatGPT to explain some things that confused me.

///////////

public class LoginTest {

    private Login login;

    /// using @BeforeEach so I don’t have to make multiple login objects
    @BeforeEach
    public void setUp() {
        login = new Login("Ryan", "Solomon", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        login.registerUser(); // Register the user before testing login
    }


    /// checks first name
    @Test
    public void testGetFirstName() {
        assertEquals("Ryan", login.getFirstName());
    }

    @Test
    public void testSetFirstName() {
        login.setFirstName("Ryan");
        assertEquals("Ryan", login.getFirstName());
    }

    @Test
    public void testCheckFirstName() {
        assertTrue(login.checkFirstName());
    }

    /// checks last name
    @Test
    public void testGetLastName() {
        assertEquals("Solomon", login.getLastName());
    }

    @Test
    public void testSetLastName() {  // fixed method name here
        login.setLastName("Solomon");
        assertEquals("Solomon", login.getLastName());
    }

    @Test
    public void testCheckLastName() {
        assertTrue(login.checkLastName());
    }

    /// checks username
    @Test
    public void testGetUsername() {
        assertEquals("kyl_1", login.getUsername());
    }

    @Test
    public void testSetUsername() {
        login.setUsername("kyl_1");
        assertEquals("kyl_1", login.getUsername());
    }

    @Test
    public void testCheckUsername() {
        assertTrue(login.checkUsername());
    }

    /// checks password
    @Test
    public void testGetPassword() {
        assertEquals("Ch&&sec@ke99!", login.getPassword());
    }

    @Test
    public void testSetPassword() {
        login.setPassword("Ch&&sec@ke99!");
        assertEquals("Ch&&sec@ke99!", login.getPassword());
    }

    @Test
    public void testCheckPasswordComplexity() {
        assertTrue(login.checkPasswordComplexity());
    }

    /// checks cell number
    @Test
    public void testGetCellNumber() {
        assertEquals("+27838968976", login.getCellNumber());
    }

    @Test
    public void testSetCellNumber() {
        login.setCellNumber("+27838968976");
        assertEquals("+27838968976", login.getCellNumber());
    }

    @Test
    public void testCheckCellPhoneNumber() {
        assertTrue(login.checkCellPhoneNumber());
    }

    /// checks if username, password, phone number are valid
    @Test
    public void testRegisterUser() {
        String expected = "Username successfully captured.\nPassword successfully captured.\nCell phone number successfully added.";
        assertEquals(expected, login.registerUser());
    }

    /// checks if login details match
    @Test
    public void testLoginUser() {
        assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
        assertFalse(login.loginUser("wrong", "wrong"));
    }

    /// says if logged in or not
    @Test
    public void testReturnLoginStatus() { 
        assertEquals("Welcome Ryan Solomon, it is great to see you again.", 
                     login.returnLoginStatus("kyl_1", "Ch&&sec@ke99!"));
        assertEquals("Username or password incorrect, please try again.", 
                     login.returnLoginStatus("wrong", "wrong"));
    }
}
