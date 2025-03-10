package awebo.gui;

import java.util.List;

import awebo.Awebo;
import awebo.parser.Parser;
import awebo.storage.Storage;
import awebo.task.Task;
import awebo.ui.Ui;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * The MainWindow class acts as the main controller for the JavaFX GUI.
 * It initializes the UI components and handles user interactions.
 */
public class MainWindow extends AnchorPane {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Awebo awebo;
    private Ui ui;
    private Parser parser;
    private Storage storage;
    private List<Task> taskList;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/seal.jpeg"));
    private Image aweboImage = new Image(this.getClass().getResourceAsStream("/images/awebo.jpeg"));

    /**
     * Initializes the MainWindow, setting up UI components and task handling.
     * Binds the scroll pane to update dynamically as new dialog messages are added.
     */
    @FXML
    public void initialize() {
        String filePath = "saved_tasks.txt";
        this.storage = new Storage(filePath);
        taskList = storage.loadTasks();
        ui = new Ui();
        parser = new Parser(taskList);
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String welcomeMessage = "Hello! I'm Awebo, your task management chatbot. "
                + "How can I assist you today? Type ? for help";
        dialogContainer.getChildren().add(DialogBox.getAweboDialog(welcomeMessage, aweboImage));
    }


    /**
     * Handle the user input, add tasks, and provide feedback to the user.
     */
    public void setAwebo(Awebo a) {
        awebo = a;
    }

    /**
     * Handles user input by processing commands, updating the UI, and displaying responses.
     * Retrieves user input, processes it using the parser, updates the dialog container,
     * and clears the input field afterward.
     */
    @FXML
    public void handleUserInput() {
        String input = userInput.getText(); // Get the text from the input field

        // Process the command
        parser.processCommand(input, ui);

        storage.saveTasks(this.taskList);

        // Get the last message that ui showed
        String response = ui.getLastMessage();

        // Add both user's input and awebo's response to the dialog container
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getAweboDialog(response, aweboImage)
        );

        // Clear the input field after processing
        userInput.clear();
    }
}
