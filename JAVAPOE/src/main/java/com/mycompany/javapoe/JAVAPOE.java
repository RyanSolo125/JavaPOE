package com.mycompany.javapoe;

import javax.swing.JOptionPane;

///////
/// ChatGPT was used for JSON and to help fix errors that I couldn't understand
//////

///////
/// changed from scanner to Joption
//////

public class JAVAPOE {
    public static void main(String[] args) {
        Message.loadFromFile("messages.json");

        // ask for register or login
        String[] startOptions = {"Register", "Login"};
        int startChoice = JOptionPane.showOptionDialog(null, "Welcome to QuickChat! Please choose an option:",
                "QuickChat Start", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, startOptions, startOptions[0]);

        Login user = null;

        if (startChoice == 0) {
            JOptionPane.showMessageDialog(null, "Welcome to QuickChat - Registration");

            String firstName = JOptionPane.showInputDialog("Enter first name:");
            String lastName = JOptionPane.showInputDialog("Enter last name:");
            String userName = JOptionPane.showInputDialog("Enter username (must contain _ and be 5 or fewer characters):");
            String password = JOptionPane.showInputDialog("Enter password (min 8 characters, 1 capital, 1 number, 1 special char):");
            String cellNumber = JOptionPane.showInputDialog("Enter cell number (must start with +27 and be 12 chars):");

            user = new Login(firstName, lastName, userName, password, cellNumber);
            JOptionPane.showMessageDialog(null, user.registerUser());

        } else {
            JOptionPane.showMessageDialog(null, "Please log in:");
            String inputUsername = JOptionPane.showInputDialog("Enter username:");
            String inputPassword = JOptionPane.showInputDialog("Enter password:");
            user = new Login("", "", inputUsername, inputPassword, "");
        }

        String inputUsername = user.getUsername();
        String inputPassword = user.getPassword();
        boolean loggedIn = user.loginUser(inputUsername, inputPassword);
        JOptionPane.showMessageDialog(null, user.returnLoginStatus(inputUsername, inputPassword));

        if (loggedIn) {
            JOptionPane.showMessageDialog(null, "Welcome to QuickChat.");

            while (true) {
                String[] options = {"Send Messages", "Show Sent Messages", "Quit"};
                int choice = JOptionPane.showOptionDialog(null, "Choose an option", "QuickChat Menu",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

                if (choice == 0) {
                    String countStr = JOptionPane.showInputDialog("How many messages do you want to send?");
                    int count = 0;
                    try {
                        count = Integer.parseInt(countStr);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Invalid number entered.");
                        continue;
                    }

                    for (int i = 0; i < count; i++) {
                        String recipient = JOptionPane.showInputDialog("Enter recipient cell number:");
                        String msg = JOptionPane.showInputDialog("Enter your message (max 250 characters):");

                        Message message = new Message(recipient, msg);
                        if (!message.checkRecipientCell()) {
                            JOptionPane.showMessageDialog(null, "Invalid recipient number. Must start with +27 and be 12 chars.");
                            continue;
                        }

                        String result = message.sentMessage();
                        if (result.equals("Message sent")) {
                            Message.saveToFile("messages.json");
                        }
                    }

                    JOptionPane.showMessageDialog(null, "Total messages sent: " + Message.returnTotalMessages());

                } else if (choice == 1) {
                    Message.displayReport(); 
                } else if (choice == 2) {
                    JOptionPane.showMessageDialog(null, "Goodbye!");
                    break;
                }
            }
        }
    }
}
