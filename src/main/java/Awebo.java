import java.util.Scanner;
import java.util.ArrayList;
class Task {
    String description;
    boolean isDone;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public void markDone() {
        this.isDone = true;
    }
    public void markUndone() {
        this.isDone = false;
    }
    public String getStatus() {
        return (isDone ? "[X] " : "[ ] ") + description;
    }
}

public class Awebo {
    public static void main(String[] args) {
        ArrayList<Task> list = new ArrayList<>(); // resizable
        Scanner scanner = new Scanner(System.in); //get user input
        System.out.println("Hello! I'm Awebo\nWhat can I do for you?\n");
        while (true) {
            String userinput = scanner.nextLine();
            if (userinput.equalsIgnoreCase("bye")) { //case-insensitive check
                System.out.println("Goodbye! aWebO");
                System.exit(0);
            }
            else if (userinput.equalsIgnoreCase("list")) {
                System.out.println("Here are the tasks in your list: ");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println((i + 1) + ". " + list.get(i).getStatus());
                }
            }
            else if (userinput.startsWith("mark ")) {
                int index = Integer.parseInt(userinput.substring(5)) - 1;
                if (index >= 0 && index < list.size()) {
                    list.get(index).markDone();
                    System.out.println("AWEsome! Marked as done: " + list.get(index).getStatus());
                } else {
                    System.out.println("Invalid task number.");
                }
            }
            else if (userinput.startsWith("unmark ")) {
                int index = Integer.parseInt(userinput.substring(7)) - 1;
                if (index >= 0 && index < list.size()) {//check if keyed index within range
                    list.get(index).markUndone();
                    System.out.println("Marked as undone: " + list.get(index).getStatus());
                } else {
                    System.out.println("Invalid task number.");
                }
            }
            else{
                list.add(new Task(userinput));
                System.out.println("added: " + userinput);
            }
        }
    }
}
