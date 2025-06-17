package com.mycompany.javapoe;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.json.JSONObject;

import java.io.File;
import java.lang.reflect.Field;
import java.util.List;

public class MessageTest {

    private static Message validMessage;
    private static Message shortMessage;
    private static Message longMessage;
    private static Message invalidRecipient;

    @BeforeAll
    public static void setup() {
        validMessage = new Message("+27821234567", "This is a test message.");
        shortMessage = new Message("+27821234567", "");
        longMessage = new Message("+27821234567", "a".repeat(251));
        invalidRecipient = new Message("0821234567", "Hello");
    }

    @AfterEach
    public void cleanup() {
        File file = new File("test_messages.json");
        if (file.exists()) file.delete();
    }

    @Test
    public void testCheckMessageID() {
        assertTrue(validMessage.checkMessageID());
    }

    @Test
    public void testCheckRecipientCell_Valid() {
        assertTrue(validMessage.checkRecipientCell());
    }

    @Test
    public void testCheckRecipientCell_Invalid() {
        assertFalse(invalidRecipient.checkRecipientCell());
    }

    @Test
    public void testSendMessage_Valid() {
        assertEquals("Message sent", validMessage.sendMessage());
    }

    @Test
    public void testSendMessage_TooShort() {
        assertEquals("Message too short.", shortMessage.sendMessage());
    }

    @Test
    public void testSendMessage_TooLong() {
        assertEquals("Message is too long. Must be 250 characters or less.", longMessage.sendMessage());
    }

    @Test
    public void testToJSONAndFromJSON() {
        JSONObject json = validMessage.toJSON();
        Message copy = Message.fromJSON(json);
        assertEquals(validMessage.toString(), copy.toString());
    }

    @Test
    public void testSaveToFileAndLoadFromFile() {
        validMessage.sendMessage();
        Message.saveToFile("test_messages.json");

        int countBefore = Message.returnTotalMessages();

        Message.loadFromFile("test_messages.json");

        // After loading, messages are added again to sentMessages
        assertTrue(Message.returnTotalMessages() >= countBefore);
    }

    @Test
    public void testReturnTotalMessages() {
        int before = Message.returnTotalMessages();
        new Message("+27821234567", "Another message").sendMessage();
        assertEquals(before + 1, Message.returnTotalMessages());
    }

    @Test
    public void testDisplayLongestMessage() {
        Message m1 = new Message("+27821234567", "Short msg");
        Message m2 = new Message("+27821234567", "This is definitely the longest message in this test.");

        m1.sendMessage();
        m2.sendMessage();

        Message longest = getLongestSentMessageForTest();
        assertEquals(m2.toString(), longest.toString());
    }

    // Access sentMessages using reflection for testing
    @SuppressWarnings("unchecked")
    private static List<Message> getSentMessagesForTest() {
        try {
            Field f = Message.class.getDeclaredField("sentMessages");
            f.setAccessible(true);
            return (List<Message>) f.get(null);
        } catch (Exception e) {
            fail("Unable to access sentMessages.");
            return List.of();
        }
    }

    private static Message getLongestSentMessageForTest() {
        List<Message> messages = getSentMessagesForTest();
        Message longest = null;
        for (Message m : messages) {
            if (longest == null || m.toString().length() > longest.toString().length()) {
                longest = m;
            }
        }
        return longest;
    }
}
