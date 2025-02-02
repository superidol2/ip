import java.util.Scanner;
import java.util.ArrayList;
public class Awebo {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>(); // resizable
        Scanner scanner = new Scanner(System.in); //get user input
        System.out.println("Hello! I'm Awebo\nWhat can I do for you?\n");
        while (true) {
            String userinput = scanner.nextLine();
            if (userinput.equalsIgnoreCase("bye")) { //case-insensitive check
                System.out.println("Goodbye! aWebO");
                System.exit(0);
            }
            if (userinput.equalsIgnoreCase("list")) {
                for (int i = 0; i < list.size(); i++) {
                    System.out.println((i+1) + ". " + list.get(i));
                }
            }
            else{
                list.add(userinput);
                System.out.println("added: " + userinput);}
        }
    }
}

