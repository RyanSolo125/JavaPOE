package com.mycompany.javapoe;

/////////
///       I used chatGPT for help with regex.
/////////

import java.util.HashMap;
import java.util.Map;

public class Login {

    // variables
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String cellNumber;

    ////////
    ///     I added first and last name because there's a greeting message in the question paper.
    ////////

    // static storage for registered users
    private static Map<String, String> registeredUsers = new HashMap<>();

    // CONSTRUCTOR to store variables
    public Login(String firstName, String lastName, String userName, String password, String cellNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.cellNumber = cellNumber;
    }

    // FIRST NAME

    // getter
    public String getFirstName() {
        return firstName;
    }

    // setter
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // checker
    public boolean checkFirstName() {
        return firstName.matches(".*[A-Z].*") &&
               firstName.matches(".*[a-z].*");
    }

    // LAST NAME

    // getter
    public String getLastName() {
        return lastName;
    }

    // setter
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // checker
    public boolean checkLastName() {
        return lastName.matches(".*[A-Z].*") &&
               lastName.matches(".*[a-z].*");
    }

    // USERNAME

    // getter
    public String getUsername() {
        return userName;
    }

    // setter
    public void setUsername(String userName) {
        this.userName = userName;
    }

    // checker
    public boolean checkUsername() {
        return userName.contains("_") && userName.length() <= 5;
    }

    // PASSWORD

    // getter
    public String getPassword() {
        return password;
    }

    // setter
    public void setPassword(String password) {
        this.password = password;
    }

    // checker
    public boolean checkPasswordComplexity() {
        return password.length() >= 8 &&
               password.matches(".*[A-Z].*") &&
               password.matches(".*[a-z].*") &&
               password.matches(".*[0-9].*") &&
               password.matches(".*[!@#$%^&*()_].*");
    }

    // CELL NUMBER

    // getter
    public String getCellNumber() {
        return cellNumber;
    }

    // setter
    public void setCellNumber(String cellNumber) {
        this.cellNumber = cellNumber;
    }

    // checker
    public boolean checkCellPhoneNumber() {
        return cellNumber.matches("\\+27\\d{9,10}");
    }

    // REGISTRATION checks if username, password, cell number are correct
    public String registerUser() {
        if (!checkUsername()) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore ( _ ), and is no more than five characters in length.";
        }

        if (!checkPasswordComplexity()) {
            return "Password is not correctly formatted; password must contain at least eight characters, a capital letter, a number, and a special character.";
        }

        if (!checkCellPhoneNumber()) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }

        //  Store valid registration
        registeredUsers.put(userName, password);
        return "Username successfully captured.\nPassword successfully captured.\nCell phone number successfully added.";
    }

    // LoginUser checks if details entered match registered users
    public boolean loginUser(String inputUsername, String inputPassword) {
        return registeredUsers.containsKey(inputUsername) && registeredUsers.get(inputUsername).equals(inputPassword);
    }

    // LoginStatus says if successful or failed login
    public String returnLoginStatus(String inputUsername, String inputPassword) {
        if (!loginUser(inputUsername, inputPassword)) {
            return "Username or password incorrect, please try again.";
        }
        return "Welcome " + firstName + " " + lastName + ", it is great to see you again.";
    }
}
