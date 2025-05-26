package com.mycompany.javapoe;

import org.json.JSONObject;
import org.junit.jupiter.api.*;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;




///////
/// Chatgpt was used for JSON and to help fix errors that I couldn't understand
//////


public class MessageTest {

    private static Message shortMessage;
    private static Message longMessage;
    private static Message invalidRecipient;
    private static Message validMessage;

    @BeforeAll
    public static void setUpClass() {
        shortMessage = new Message("+27123456789", "Hello");
        longMessage = new Message("+27123456789", "a".repeat(251));
        invalidRecipient = new Message("0821234567", "Hi");
        validMessage = new Message("+27123456789", "This is a test message.");
    }

    @AfterEach
    public void cleanUp() {
        new File("test_messages.json").delete();
    }

    @Test
    public void testCheckMessageID() {
        assertTrue(validMessage.checkMessageID(), "Message ID should be 10 digits or fewer.");
    }

    @Test
    public void testCheckRecipientCell_Valid() {
        assertTrue(validMessage.checkRecipientCell(), "Valid South African cell number should pass.");
    }

    @Test
    public void testCheckRecipientCell_Invalid() {
        assertFalse(invalidRecipient.checkRecipientCell(), "Invalid cell number should fail.");
    }

    @Test
    public void testSendMessage_Valid() {
        assertEquals("Message sent", validMessage.sendMessage(), "Valid message should be sent.");
    }

    @Test
    public void testSendMessage_TooLong() {
        assertEquals("Message is too long. Must be 250 characters or less.",
                     longMessage.sendMessage(), "Too long message should be rejected.");
    }

    @Test
    public void testSendMessage_TooShort() {
        Message emptyMessage = new Message("+27123456789", "");
        assertEquals("Message too short.", emptyMessage.sendMessage(), "Empty message should be rejected.");
    }

    @Test
    public void testToJSONandFromJSON() {
        JSONObject json = validMessage.toJSON();
        Message recreated = Message.fromJSON(json);
        assertEquals(validMessage.toString(), recreated.toString(), "Message should be equal after JSON serialization and deserialization.");
    }

    @Test
    public void testSaveToFileAndLoadFromFile() {
        validMessage.sendMessage();  // Ensure message is added to the sent list
        Message.saveToFile("test_messages.json");
        Message.loadFromFile("test_messages.json");
        assertTrue(Message.returnTotalMessages() > 0, "Should load at least one message.");
    }

    @Test
    public void testReturnTotalMessages() {
        int countBefore = Message.returnTotalMessages();
        validMessage.sendMessage();
        assertEquals(countBefore + 1, Message.returnTotalMessages(), "Total message count should increase.");
    }

}
