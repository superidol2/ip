package awebo;

import java.util.ArrayList;
import java.util.Scanner;

import awebo.parser.Parser;
import awebo.task.Task;
import awebo.ui.Ui;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The {@code AweboCLI} class handles the command-line version of the application.
 */
public class Awebo extends Application {

    @Override
    public void start(Stage primaryStage) {
        // This will be your JavaFX initialization code
        String filePath = "saved_tasks.txt";
        ArrayList<Task> list = new ArrayList<>();
        Ui ui = new Ui();
        Parser parser = new Parser(list);

        ui.showWelcomeMessage();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userInput = ui.getUserInput(scanner);
            parser.processCommand(userInput, ui);
        }
    }

    public static void main(String[] args) {

        // Launch the JavaFX application
        launch(args);
    }
}
