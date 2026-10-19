package chatapp;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/** Represents one QuickChat message and provides message validation/storage. */
public class Message {
    private static final List<Message> SENT_MESSAGES = new ArrayList<>();
    private static final Path STORE_FILE = Paths.get("messages.json");
    private final String messageID;
    private final String recipient;
    private final String text;
    private final String hash;
    private String status;

    public Message(String recipient, String text) {
        this.messageID = String.valueOf(ThreadLocalRandom.current().nextLong(1_000_000_000L, 9_999_999_999L));
        this.recipient = recipient == null ? "" : recipient.trim();
        this.text = text == null ? "" : text.trim();
        this.hash = createMessageHash();
        this.status = "Pending";
    }

    public boolean checkMessageID() { return messageID.length() <= 10; }

    /** Accepts an international-format number, including the South African +27 format. */
    public boolean checkRecipientCell() {
        return recipient.matches("^\\+[1-9][0-9]{7,12}$");
    }

    public String createMessageHash() {
        String[] words = text.trim().isEmpty() ? new String[0] : text.trim().split("\\s+");
        String first = words.length == 0 ? "" : words[0];
        String last = words.length == 0 ? "" : words[words.length - 1];
        String firstTwo = messageID.substring(0, Math.min(2, messageID.length()));
        return (firstTwo + ":" + SENT_MESSAGES.size() + ":" + first + last).toUpperCase();
    }

    /** Records the selected action and returns the assignment's required response. */
    public String SentMessage(String choice) {
        if (choice == null) return "Invalid message option.";
        switch (choice.trim().toLowerCase()) {
            case "send":
            case "1":
                status = "Sent";
                SENT_MESSAGES.add(this);
                return "Message successfully sent.";
            case "disregard":
            case "2":
                status = "Disregarded";
                return "Press 0 to delete the message.";
            case "store":
            case "3":
                status = "Stored";
                try { storeMessage(); return "Message successfully stored."; }
                catch (IOException ex) { return "Unable to store message: " + ex.getMessage(); }
            default: return "Invalid message option.";
        }
    }

    public String printMessages() {
        StringBuilder out = new StringBuilder();
        for (Message m : SENT_MESSAGES) out.append(m).append(System.lineSeparator());
        return out.toString().trim();
    }

    public int returnTotalMessagess() { return SENT_MESSAGES.size(); }

    /** Appends this message as a JSON object to messages.json. */
    public void storeMessage() throws IOException {
        List<String> records = new ArrayList<>();
        if (Files.exists(STORE_FILE)) {
            String existing = Files.readString(STORE_FILE, StandardCharsets.UTF_8).trim();
            if (!existing.isEmpty() && !existing.equals("[]")) {
                String inner = existing;
                if (inner.startsWith("[") && inner.endsWith("]")) inner = inner.substring(1, inner.length() - 1).trim();
                if (!inner.isEmpty()) records.add(inner);
            }
        }
        records.add("{\"messageID\":\"" + escape(messageID) + "\",\"recipient\":\"" + escape(recipient)
                + "\",\"message\":\"" + escape(text) + "\",\"hash\":\"" + escape(hash)
                + "\",\"status\":\"" + escape(status) + "\"}");
        Files.writeString(STORE_FILE, "[" + String.join(",", records) + "]" + System.lineSeparator(), StandardCharsets.UTF_8);
    }

    private static String escape(String s) {
        return s.replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", "\\n").replace("\r", "\\r");
    }
    public String getMessageID() { return messageID; }
    public String getRecipient() { return recipient; }
    public String getText() { return text; }
    public String getHash() { return hash; }
    public String getStatus() { return status; }
    public static void clearSentMessagesForTests() { SENT_MESSAGES.clear(); }

    @Override public String toString() {
        return "Message ID: " + messageID + " | Hash: " + hash + " | Recipient: " + recipient + " | Message: " + text + " | Status: " + status;
    }
}
