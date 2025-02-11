package awebo;

import java.util.ArrayList;
import java.util.Scanner;

import awebo.parser.Parser;
import awebo.task.Task;
import awebo.ui.Ui;

/**
 * The {@code Awebo} class represents the main entry point for the program.
 * It initializes the necessary components like the user interface (UI), parser,
 * and task list, and runs the program in a continuous loop to process user input
 * and interact with the system.
 */
public class Awebo {

    /**
     * The main method initializes the application by setting up the necessary
     * components, including the task list, user interface, and parser.
     * It then enters a loop to continuously accept and process user commands.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Path to the file where tasks will be saved.
        String filePath = "saved_tasks/output.txt";

        // List that holds the tasks.
        ArrayList<Task> list = new ArrayList<>();

        // Instance of the user interface to interact with the user.
        Ui ui = new Ui();

        // Parser responsible for processing commands and updating the task list.
        Parser parser = new Parser(list, filePath);

        // Show the welcome message to the user.
        ui.showWelcomeMessage();

        // Create a scanner to read user input.
        Scanner scanner = new Scanner(System.in);

        // Continuously process commands entered by the user.
        while (true) {
            String userInput = ui.getUserInput(scanner);
            parser.processCommand(userInput, ui);
        }
    }
}

