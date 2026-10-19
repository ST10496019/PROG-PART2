package chatapp;

import java.util.Scanner;

/** Console application for PROG Parts 1 and 2: registration, login and QuickChat. */
public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Login login = new Login();
            System.out.println("=== Chat App - Registration and Login ===");
            System.out.print("Enter your first name: "); login.setFirstName(scanner.nextLine());
            System.out.print("Enter your last name: "); login.setLastName(scanner.nextLine());
            System.out.print("Enter a username: "); login.setUsername(scanner.nextLine());
            if (!login.checkUserName()) { System.out.println("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length."); return; }
            System.out.println("Username successfully captured.");
            System.out.print("Enter a password: "); login.setPassword(scanner.nextLine());
            if (!login.checkPasswordComplexity()) { System.out.println("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character."); return; }
            System.out.println("Password successfully captured.");
            System.out.print("Enter your South African cell phone number in international format (+27...): "); login.setCellPhoneNumber(scanner.nextLine());
            if (!login.checkCellPhoneNumber()) { System.out.println("Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again."); return; }
            System.out.println("Cell number successfully captured.");
            System.out.println(login.registerUser());
            System.out.println("\n=== Login ===");
            System.out.print("Enter your username: "); String username = scanner.nextLine();
            System.out.print("Enter your password: "); String password = scanner.nextLine();
            boolean loggedIn = login.loginUser(username, password);
            System.out.println(login.returnLoginStatus(loggedIn));
            if (loggedIn) runQuickChat(scanner);
        }
    }

    private static void runQuickChat(Scanner scanner) {
        System.out.println("\nWelcome to QuickChat.");
        boolean running = true;
        while (running) {
            System.out.println("\n1) Send Messages\n2) Show recently sent messages\n3) Quit");
            System.out.print("Choose an option: ");
            String option = scanner.nextLine().trim();
            switch (option) {
                case "1":
                    System.out.print("How many messages would you like to enter? ");
                    int count;
                    try { count = Integer.parseInt(scanner.nextLine().trim()); }
                    catch (NumberFormatException ex) { System.out.println("Please enter a valid number."); break; }
                    if (count < 0) { System.out.println("Please enter zero or a positive number."); break; }
                    for (int i = 0; i < count; i++) {
                        System.out.print("Enter recipient number in international format (e.g. +27838968976): ");
                        String recipient = scanner.nextLine();
                        Message message = new Message(recipient, "");
                        if (!message.checkRecipientCell()) {
                            System.out.println("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.");
                            i--; continue;
                        }
                        System.out.println("Cell phone number successfully captured.");
                        System.out.print("Enter message (maximum 250 characters): ");
                        String body = scanner.nextLine();
                        message = new Message(recipient, body);
                        if (body.length() > 250) {
                            System.out.println("Message exceeds 250 characters by " + (body.length() - 250) + "; please reduce the size.");
                            i--; continue;
                        }
                        System.out.println("Message ready to send.");
                        System.out.println("Message ID generated: " + message.getMessageID());
                        System.out.println("Message Hash: " + message.getHash());
                        System.out.print("Choose: 1) Send Message  2) Disregard Message  3) Store Message: ");
                        System.out.println(message.SentMessage(scanner.nextLine()));
                    }
                    break;
                case "2": System.out.println("Coming Soon."); break;
                case "3": running = false; System.out.println("Thank you for using QuickChat."); break;
                default: System.out.println("Invalid option. Please choose 1, 2 or 3.");
            }
        }
    }
}
