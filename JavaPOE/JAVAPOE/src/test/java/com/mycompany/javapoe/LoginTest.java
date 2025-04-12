package com.mycompany.javapoe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    private Login login;

    @BeforeEach
    public void setUp() {
        login = new Login("Ryan", "Solomon", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
    }

    @Test
    public void testGetfirstName() {
        assertEquals("Ryan", login.getfirstName());
    }

    @Test
    public void testSetFirstName() {
        login.setFirstName("Ryan");
        assertEquals("Ryan", login.getfirstName());
    }

    @Test
    public void testCheckFirstName() {
        assertTrue(login.checkFirstName());
    }

    @Test
    public void testGetLastName() {
        assertEquals("Solomon", login.getLastName()); 
    }

    @Test
    public void testSetLasttName() {
        login.setLastName("Solomon");
        assertEquals("Solomon", login.getLastName());
    }

    @Test
    public void testCheckLastName() {
        assertTrue(login.checkLastName());
    }

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

    @Test
    public void testRegisterUser() {
        assertEquals("Successfully registered!", login.registerUser());
    }

    @Test
    public void testLoginUser() {
        assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
        assertFalse(login.loginUser("wrong", "wrong"));
    }

    @Test
    public void testReturnloginStatus() {
        assertEquals("Welcome " + login.getfirstName() + " " + login.getLastName() + ", good to see you again.", login.returnloginStatus("kyl_1", "Ch&&sec@ke99!"));
        assertEquals("Login failed. Username or password does not match.", login.returnloginStatus("wrong", "wrong"));
    }
} 
