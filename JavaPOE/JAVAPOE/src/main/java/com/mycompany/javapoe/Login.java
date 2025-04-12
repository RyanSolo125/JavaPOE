/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javapoe;


public class Login {
    
    // variables
    
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String cellNumber;
   
    
    //CONSTRUCTOR to store variables
    
    public Login(String firstName, String lastName, String userName, String password, String cellNumber){   
        
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.cellNumber = cellNumber;
    }
    
    
    
    
    //FIRST NAME
    
    //getter
    
    public String getfirstName(){
        return firstName;
    }
    
    //setter
    
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    
    
    //checker
    
    public boolean checkFirstName(){
        return firstName.matches(".*[A-Z].*") &&
                firstName.matches(".*[a-z].*");
    }
    
    
    
    
    
    //LAST NAME
    
    //getter
    
    public String getLastName(){
        return lastName;
    }
    
    //setter
    
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    
    
    //checker
    
    public boolean checkLastName(){
        return lastName.matches(".*[A-Z].*") &&
                lastName.matches(".*[a-z].*");
    }
    
    
    
    
    
    // USERNAME
    
    //getter
    public String getUsername(){
        return userName;
}
    
    //setter
    public void setUsername(String userName){
        this.userName = userName;
    }
    
    
    //checker
    public boolean checkUsername(){
        return userName.contains("_")&& userName.length() <= 5;
    }
    
    
    
    
    
    // PASSWORD
    
    //getter
    public String getPassword(){
        return password;
    }
    //setter
    public void setPassword(String password){
        this.password = password;
    }
    //checker
    public boolean checkPasswordComplexity(){
        return password.length() >= 8 &&
                password.matches(".*[A-Z].*")&&
                password.matches(".*[a-z].*")&&
                password.matches(".*[0-9].*")&&
                password.matches(".*[!@#$%^&*()_].*");
    }

    
    
    
    
    /// CELL NUMBER
    
    //getter
    public String getCellNumber(){
        return cellNumber;
    }
    //setter
    public void setCellNumber(String cellNumber){
        this.cellNumber = cellNumber;
    }
    //checker
    public boolean checkCellPhoneNumber(){
        return cellNumber.length() == 12 &&
                cellNumber.matches(".*[0-9].*")&&
                cellNumber.startsWith("+27");
    }
    
    
    
    
    //REGISTRATION checks if password and username are correct
    
    public String registerUser(){
        
        if (!checkUsername() || !checkPasswordComplexity() || !checkCellPhoneNumber())
            return "Username, Password or Cellnumber are incorrectly formatted. Username must include and underscore(_) and be 5 or less characters. Password must be at least 8 characters long, contain at least 1 capital, at least 1 number and at least 1 of the following characters; !@#$%^&*()_ . Cellnumber must start with +27 and be 12 characters long.";
            
        else

        return "Successfully registered!";
    }
    
    
    // LOGIN checks if details entered matches login details
    
    
    public boolean loginUser(String inputUsername, String inputPassword) {
        return this.userName.equals(inputUsername) && this.password.equals(inputPassword);
}
    
    
    //Login status says if successful or failed login
    
    public String returnloginStatus(String inputUsername, String inputPassword){
    if (!loginUser(inputUsername, inputPassword)) {
        return "Login failed. Username or password does not match.";
    }
    return "Welcome " + firstName + " " + lastName + ", good to see you again.";
}
    
    
}

    
    


