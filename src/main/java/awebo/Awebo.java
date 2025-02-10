package awebo;

import java.util.ArrayList;
import java.util.Scanner;

import awebo.parser.Parser;
import awebo.task.Task;
import awebo.ui.Ui;

public class Awebo {
    public static void main(String[] args) {
        String filePath = "saved_tasks/output.txt";
        ArrayList<Task> list = new ArrayList<>();
        Ui ui = new Ui();
        Parser parser = new Parser(list, filePath);

        ui.showWelcomeMessage();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String userInput = ui.getUserInput(scanner);
            parser.processCommand(userInput, ui);
        }
    }
}
