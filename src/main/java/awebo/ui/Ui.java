package awebo.ui;

import java.util.Scanner;

/**
 * The {@code Ui} class provides methods for interacting with the user.
 * It displays messages to the user, retrieves user input, and shows a welcome message.
 */
public class Ui {

    /**
     * Displays a welcome message to the user.
     * This method is typically called when the application starts.
     */
    public void showWelcomeMessage() {
        System.out.println("Hello! I'm main.Awebo\nWhat can I do for you?");
    }

    /**
     * Retrieves input from the user through the provided {@code Scanner} object.
     *
     * @param scanner The {@code Scanner} object used to read user input.
     * @return The input entered by the user as a {@code String}.
     */
    public String getUserInput(Scanner scanner) {
        return scanner.nextLine();
    }

    /**
     * Displays a message to the user.
     *
     * @param message The message to be displayed to the user.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }
}

