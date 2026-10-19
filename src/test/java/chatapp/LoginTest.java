package chatapp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests required by the Part 1 specification.
 */
public class LoginTest {

    private Login login;

    @BeforeEach
    void setUp() {
        login = new Login(
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976",
                "John",
                "Doe"
        );
    }

    @Test
    void usernameCorrectlyFormatted() {
        assertEquals(true, login.checkUserName());
    }

    @Test
    void usernameIncorrectlyFormatted() {
        login.setUsername("kyle!!");
        assertEquals(false, login.checkUserName());
    }

    @Test
    void passwordMeetsComplexityRequirements() {
        assertEquals(true, login.checkPasswordComplexity());
    }

    @Test
    void passwordDoesNotMeetComplexityRequirements() {
        login.setPassword("password");
        assertEquals(false, login.checkPasswordComplexity());
    }

    @Test
    void cellPhoneNumberCorrectlyFormatted() {
        assertEquals(true, login.checkCellPhoneNumber());
    }

    @Test
    void cellPhoneNumberIncorrectlyFormatted() {
        login.setCellPhoneNumber("08966553");
        assertEquals(false, login.checkCellPhoneNumber());
    }

    @Test
    void loginSuccessful() {
        assertEquals(true, login.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    void loginFailed() {
        assertEquals(false, login.loginUser("kyl_1", "wrongPassword"));
    }

    @Test
    void usernameRegistrationMessage() {
        login.setUsername("kyle!!");
        assertEquals(
                "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.",
                login.registerUser()
        );
    }

    @Test
    void passwordRegistrationMessage() {
        login.setPassword("password");
        assertEquals(
                "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.",
                login.registerUser()
        );
    }

    @Test
    void successfulRegistrationMessage() {
        assertEquals("User successfully registered.", login.registerUser());
    }

    @Test
    void successfulLoginMessage() {
        assertEquals(
                "Welcome John Doe, it is great to see you again.",
                login.returnLoginStatus(true)
        );
    }

    @Test
    void failedLoginMessage() {
        assertEquals(
                "Username or password incorrect, please try again.",
                login.returnLoginStatus(false)
        );
    }
}
