package awebo.ui;

import java.util.Scanner;

public class Ui {
    public void showWelcomeMessage() {
        System.out.println("Hello! I'm main.Awebo\nWhat can I do for you?");
    }

    public String getUserInput(Scanner scanner) {
        return scanner.nextLine();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
