package chatapp;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MessageTest {
    @BeforeEach void reset() { Message.clearSentMessagesForTests(); }

    @Test void validInternationalRecipientIsAccepted() {
        assertTrue(new Message("+27838968976", "Hi tonight").checkRecipientCell());
    }
    @Test void recipientWithoutInternationalCodeIsRejected() {
        assertFalse(new Message("0838968976", "Hi").checkRecipientCell());
    }
    @Test void messageIdIsAtMostTenCharacters() {
        assertTrue(new Message("+27838968976", "Hi").checkMessageID());
    }
    @Test void messageHashContainsExpectedStructure() {
        Message m = new Message("+27838968976", "Hi tonight");
        assertTrue(m.getHash().matches("[0-9]{2}:0:HITONIGHT"));
    }
    @Test void sendingAddsMessageToSentList() {
        Message m = new Message("+27838968976", "Hello there");
        assertEquals("Message successfully sent.", m.SentMessage("1"));
        assertEquals(1, m.returnTotalMessagess());
        assertTrue(m.printMessages().contains("Hello there"));
    }
    @Test void disregardingDoesNotAddMessageToSentList() {
        Message m = new Message("+27838968976", "Hello there");
        assertEquals("Press 0 to delete the message.", m.SentMessage("2"));
        assertEquals(0, m.returnTotalMessagess());
    }
    @Test void storingReturnsExpectedMessage() {
        Message m = new Message("+27838968976", "Hello there");
        assertEquals("Message successfully stored.", m.SentMessage("3"));
    }
}
