import java.util.Scanner;
public class Awebo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); //get user input
        System.out.println("Hello! I'm Awebo\nWhat can I do for you?\n");
        while (true) {
            String userinput = scanner.nextLine();
            if (userinput.equalsIgnoreCase("bye")) { //case-insensitive check
                System.out.println("Goodbye! aWebO");
                        System.exit(0);
            } else {
                System.out.println(userinput); //echo
            }
        }
    }
}
