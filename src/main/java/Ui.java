import java.util.Scanner;

class Ui {
    public void showWelcomeMessage() {
        System.out.println("Hello! I'm Awebo\nWhat can I do for you?");
    }

    public String getUserInput(Scanner scanner) {
        return scanner.nextLine();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
