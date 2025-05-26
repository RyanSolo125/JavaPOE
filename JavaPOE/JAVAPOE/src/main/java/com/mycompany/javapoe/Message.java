package com.mycompany.javapoe;

import java.util.*;
import java.io.*;
import java.nio.file.*;
import javax.swing.JOptionPane;
import org.json.JSONArray;
import org.json.JSONObject;

///////
/// Chatgpt was used for JSON and to help fix errors that I couldn't understand
//////

public class Message {
    private String recipient;
    private String messageText;
    private String messageID;
    private String messageHash;
    private static int totalMessages = 0;
    private static List<Message> sentMessages = new ArrayList<>();

    // Constructor
    public Message(String recipient, String messageText) {
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageID = generateID();
        this.messageHash = createMessageHash();
    }

    private String generateID() {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(rand.nextInt(10));
        }
        return sb.toString();
    }

    public boolean checkMessageID() {
        return messageID.length() <= 10;
    }

    public boolean checkRecipientCell() {
        return recipient.matches("\\+27\\d{9}");
    }

    public String sendMessage() {
        if (messageText.length() > 250) {
            return "Message is too long. Must be 250 characters or less.";
        } else if (messageText.length() < 1) {
            return "Message too short.";
        } else {
            totalMessages++;
            sentMessages.add(this);
            return "Message sent";
        }
    }

    private String createMessageHash() {
        String[] words = messageText.trim().split("\\s+");
        String firstWord = words.length > 0 ? words[0] : "";
        String lastWord = words.length > 1 ? words[words.length - 1] : firstWord;
        return (messageID.substring(0, 2) + ":" + totalMessages + ":" + firstWord + lastWord).toUpperCase();
    }

    public String sentMessage() {
        String[] options = {"Send Message", "Disregard Message", "Store Message to send later"};
        int choice = JOptionPane.showOptionDialog(null, "Choose an option", "Message Options",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        if (choice == 0) {
            String result = sendMessage();
            JOptionPane.showMessageDialog(null, result.equals("Message sent") ? this.toString() : result);
            return result;
        } else if (choice == 1) {
            return "Message disregarded";
        } else if (choice == 2) {
            return "Message stored";
        }
        return "No action taken";
    }

    public static int returnTotalMessages() {
        return totalMessages;
    }


    // JSON conversion for saving
    public JSONObject toJSON() {
        JSONObject obj = new JSONObject();
        obj.put("recipient", recipient);
        obj.put("messageText", messageText);
        obj.put("messageID", messageID);
        obj.put("messageHash", messageHash);
        return obj;
    }

    public static Message fromJSON(JSONObject obj) {
        Message msg = new Message(obj.getString("recipient"), obj.getString("messageText"));
        msg.messageID = obj.getString("messageID");
        msg.messageHash = obj.getString("messageHash");
        return msg;
    }

    public static void saveToFile(String filePath) {
        JSONArray jsonArray = new JSONArray();
        for (Message msg : sentMessages) {
            jsonArray.put(msg.toJSON());
        }

        try (FileWriter file = new FileWriter(filePath)) {
            file.write(jsonArray.toString(4));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving messages: " + e.getMessage());
        }
    }

    public static void loadFromFile(String filePath) {
        sentMessages.clear();
        totalMessages = 0;

        File file = new File(filePath);
        if (!file.exists()) return;

        try {
            String content = new String(Files.readAllBytes(file.toPath()));
            JSONArray jsonArray = new JSONArray(content);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                Message msg = Message.fromJSON(obj);
                sentMessages.add(msg);
                totalMessages++;
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error loading messages: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "Message ID: " + messageID +
               "\nMessage Hash: " + messageHash +
               "\nRecipient: " + recipient +
               "\nMessage: " + messageText;
    }
}
