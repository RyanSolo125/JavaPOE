package com.mycompany.javapoe;

import java.util.Scanner;

public class JAVAPOE {
    public static void main(String[] args) {
        
        
        Scanner scanner = new Scanner(System.in);

        
        
        // enter the details
        
        System.out.println("Registration");

        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.println("First name succsessfully captured.");
        

        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        System.out.println("Last name succsessfully captured.");

        System.out.print("Enter username (must contain _ and be 5 or fewer characters): ");
        String userName = scanner.nextLine();
        System.out.println("Username successfully captured.");

        System.out.print("Enter password (min 8 characters, 1 capital, 1 number, 1 special character): ");
        String password = scanner.nextLine();
        System.out.println("Password succsessfully captured.");

        System.out.print("Enter cell number (must start with +27 and be 12 characters long): ");
        String cellNumber = scanner.nextLine();
        System.out.println("Cell number successfully captured.");
        
        

        // login object
        
        Login user = new Login(firstName, lastName, userName, password, cellNumber);

        
        
        // registration
        
        System.out.println(user.registerUser());

        
        
        // login
        
        System.out.println("Login");
        
        
        

        System.out.print("Enter username: ");
        String inputUsername = scanner.nextLine();
        

        System.out.print("Enter password: ");
        String inputPassword = scanner.nextLine();
        
        scanner.close();
        
        

        // returns login status
        
        System.out.println(user.returnloginStatus(inputUsername, inputPassword));

        
    }
}
