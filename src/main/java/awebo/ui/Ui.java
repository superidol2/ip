package awebo.ui;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import awebo.task.Task;


/**
 * The {@code Ui} class provides methods for interacting with the user.
 * It displays messages to the user, retrieves user input, and shows a welcome message.
 */
public class Ui {

    // Add a field to store the last message shown to the user
    private String lastMessage;

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
        // Store the last message
        lastMessage = message;

        // Display the message
        System.out.println(message);
    }

    /**
     * Gets the last message displayed to the user.
     *
     * @return The last message shown to the user.
     */
    public String getLastMessage() {

        // Return the last message displayed
        return lastMessage;
    }

    /**
     * Displays a help document of commands available for usage in the app.
     */
    public void showHelp() {
        String helpMessage = "Here are the available commands:\n"
                + "ToDos: tasks without any date/time attached to it.\n"
                + "    - Input: todo <task>\n\n"
                + "Deadlines: tasks that need to be done before a specific date/time.\n"
                + "    - Input: deadline <task> /by <d/M/yyyy>\n\n"
                + "Events: tasks that start and end at a specific date/time.\n"
                + "    - Input: event <task> /from <d/M/yyyy> <HHmm> /to <d/M/yyyy> <HHmm>\n\n"
                + "Mark: mark tasks as done.\n"
                + "    - Input: mark <task number>\n\n"
                + "Unmark: unmark tasks.\n"
                + "    - Input: unmark <task number>\n\n"
                + "Remove: remove tasks.\n"
                + "    - Input: remove <task number>\n\n"
                + "Exit: exit application\n"
                + "    - Input: bye";

        this.showMessage(helpMessage);
    }

    /**
     * Displays current task list.
     */
    public void showTaskList(ArrayList<Task> list) {
        if (list.isEmpty()) {
            this.showMessage("Your task list is empty.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        for (int i = 0; i < list.size(); i++) {
            sb.append(i + 1).append(". ").append(list.get(i)).append("\n");
        }

        this.showMessage(sb.toString());
    }

    /**
     * Displays a randomly selected cheer.
     */
    public void cheerTask() {
        ArrayList<String> cheer = new ArrayList<>();
        cheer.add("Hip Hip Hooray engineers!");
        cheer.add("Let's go, you can do it fellow engineers!");
        cheer.add("You got this, hang on engineers!");
        Random rand = new Random();
        this.showMessage(cheer.get(rand.nextInt(cheer.size())));
    }
}
