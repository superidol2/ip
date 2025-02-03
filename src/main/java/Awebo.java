import java.util.Scanner;
import java.util.ArrayList;
abstract class Task {
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
    public abstract String getType();
    @Override
    public String toString() {
        return "[" + getType() + "]" + getStatus();
    }
}

class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }
    @Override
    public String getType() {
        return "T"; //"T" for todo
    }
}

class Deadline extends Task {
    String by;
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    @Override
    public String getType() {
        return "D"; //"D" for deadline
    }
    @Override
    public String toString() {
        return "[" + getType() + "]" + getStatus() + " (by: " + by + ")";
    }
}

class Event extends Task {
    String from, to;
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }
    @Override
    public String getType() {
        return "E"; // "E" for Event
    }
    @Override
    public String toString() {
        return "[" + getType() + "]" + getStatus() + " (from: " + from + " to: " + to + ")";
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
            } else if (userinput.equalsIgnoreCase("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println((i + 1) + ". " + list.get(i).toString());
                }
            } else if (userinput.startsWith("mark ")) {
                int index = Integer.parseInt(userinput.substring(5)) - 1;
                if (index >= 0 && index < list.size()) {
                    list.get(index).markDone();
                    System.out.println("AWEsome! Marked as done: " + list.get(index).getStatus());
                } else {
                    System.out.println("Invalid task number.");
                }
            } else if (userinput.startsWith("unmark ")) {
                int index = Integer.parseInt(userinput.substring(7)) - 1;
                if (index >= 0 && index < list.size()) {
                    list.get(index).markUndone();
                    System.out.println("Marked as undone: " + list.get(index).getStatus());
                } else {
                    System.out.println("Invalid task number.");
                }
            } else if (userinput.startsWith("todo ")) {
                String taskDesc = userinput.substring(5).trim();
                if (taskDesc.isEmpty()) {
                    System.out.println("Empty task, please fill in your task.");
                } else {
                    Task newTask = new ToDo(taskDesc);
                    list.add(newTask);
                    System.out.println("Got it. I've added this task:\n  " + newTask);
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                }
            } else if (userinput.startsWith("deadline ")) {
                int splitIndex = userinput.indexOf(" /by ");
                if (splitIndex == -1) {
                    System.out.println("Invalid format. Use: deadline <task> /by <date>");
                } else {
                    if (splitIndex <= 9) {//ensure there's something before "/by"
                        System.out.println("Invalid deadline. Please provide a task description before /by.");
                        continue;
                    }
                    String taskDesc = userinput.substring(9, splitIndex).trim();
                    String by = "";
                    if (splitIndex + 5 < userinput.length()) {
                        by = userinput.substring(splitIndex + 5).trim(); //extract date after "/by"
                    }
                    //validate input
                    if (taskDesc.isEmpty()) {
                        System.out.println("Invalid deadline. Please provide a task description before /by.");
                    } else if (by.isEmpty()) {
                        System.out.println("Invalid deadline. Please provide a valid deadline after /by.");
                    } else {
                        Task newTask = new Deadline(taskDesc, by);
                        list.add(newTask);
                        System.out.println("Got it. I've added this task:\n  " + newTask);
                        System.out.println("Now you have " + list.size() + " tasks in the list.");
                    }
                }
            }
            else if (userinput.startsWith("event ")) {
                int fromIndex = userinput.indexOf(" /from ");
                int toIndex = userinput.indexOf(" /to ");
                if (fromIndex == -1 || toIndex == -1 || fromIndex > toIndex) {
                    System.out.println("Invalid format. Use: event <task> /from <start> /to <end>");
                    continue;
                }
                String taskDesc = userinput.substring(6, fromIndex).trim();
                String from = userinput.substring(fromIndex + 7, toIndex).trim();
                String to = userinput.substring(toIndex + 4).trim();
                if (taskDesc.isEmpty() || from.isEmpty() || to.isEmpty()) { //ensure all parts not empty
                    System.out.println("Invalid event. Please provide task, start, and end times.");
                    continue;
                }
                Task newTask = new Event(taskDesc, from, to);
                list.add(newTask);
                System.out.println("Got it. I've added this task:\n  " + newTask);
                System.out.println("Now you have " + list.size() + " tasks in the list.");
            }
            else{
                System.out.println("Unknown command. Try 'todo'/'deadline'/'event'.");
            }
        }
    }}
