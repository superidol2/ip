package awebo.parser;

import java.util.ArrayList;
import java.util.Random;

import awebo.dateformat.DateFormatter;
import awebo.deadline.Deadline;
import awebo.event.Event;
import awebo.storage.Storage;
import awebo.task.Task;
import awebo.todo.ToDo;
import awebo.ui.Ui;

/**
 * The {@code Parser} class is responsible for processing user commands
 * and executing the corresponding actions on a task list.
 */
public class Parser {
    /** The list of tasks managed by the parser. */
    private ArrayList<Task> list;

    /** The file path where task data is stored. */
    private String filePath;

    /**
     * Constructs a {@code Parser} with a task list and a file path.
     *
     * @param list The list of tasks.
     * @param filePath The file path for storing task data.
     */
    public Parser(ArrayList<Task> list, String filePath) {
        this.list = list;
        this.filePath = filePath;
    }

    /**
     * Processes a user command and performs the corresponding action.
     *
     * @param command The user input command.
     * @param ui The user interface to display messages.
     */
    public void processCommand(String command, Ui ui) {
        if (command.equalsIgnoreCase("bye")) {
            ui.showMessage("Goodbye! aWebO");
            System.exit(0);
        } else if (command.equalsIgnoreCase("list")) {
            showTaskList(ui);
        } else if (command.equalsIgnoreCase("?")) {
            showHelp(ui);
        }
        else if (command.startsWith("mark ")) {
            markTask(command, ui);
        } else if (command.startsWith("unmark ")) {
            unmarkTask(command, ui);
        } else if (command.startsWith("todo ")) {
            addTodo(command, ui);
        } else if (command.startsWith("deadline ")) {
            addDeadline(command, ui);
        } else if (command.startsWith("event ")) {
            addEvent(command, ui);
        } else if (command.startsWith("delete ") || command.startsWith("remove ")) {
            deleteTask(command, ui);
        } else if (command.startsWith("find ")) {
            findTask(command, ui);
        } else if (command.startsWith("cheer")) {
            cheerTask(command, ui);
        } else {
            ui.showMessage("Unknown command. Try 'todo', 'deadline', or 'event'.");
        }
    }

    private void showHelp(Ui ui) {
        String helpMessage = "Here are the available commands:\n"
                + "ToDos: tasks without any date/time attached to it.\n"
                + "    - Input: todo <task>\n\n"
                + "Deadlines: tasks that need to be done before a specific date/time.\n"
                + "    - Input: deadline <task> /by <d/M/yyyy>\n\n"
                + "Events: tasks that start and end at a specific date/time.\n"
                + "    - Input: event <task> /from <d/M/yyyy> <HHmm> /to <d/M/yyyy> <HHmm>\n\n"
                + "Mark: mark tasks as done.\n"
                + "    - Input: mark <task number>\n\n"
                + "Unmark: unmark tasks.\n"
                + "    - Input: unmark <task number>\n\n"
                + "Remove: remove tasks.\n"
                + "    - Input: remove <task number>\n\n"
                + "Exit: exit application\n"
                + "    - Input: bye";

        ui.showMessage(helpMessage);
    }

    private void showTaskList(Ui ui) {
        if (list.isEmpty()) {
            ui.showMessage("Your task list is empty.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        for (int i = 0; i < list.size(); i++) {
            sb.append(i + 1).append(". ").append(list.get(i)).append("\n");
        }

        ui.showMessage(sb.toString());
    }

    private void markTask(String command, Ui ui) {
        try {
            int index = Integer.parseInt(command.substring(5)) - 1;
            list.get(index).markDone();
            ui.showMessage("AWEsome! Marked as done: " + list.get(index).getStatus());
            Storage.writeListToFile(list, filePath);
        } catch (Exception e) {
            ui.showMessage("Invalid task number.");
        }
    }

    private void unmarkTask(String command, Ui ui) {
        try {
            int index = Integer.parseInt(command.substring(7)) - 1;
            list.get(index).markUndone();
            ui.showMessage("Marked as undone: " + list.get(index).getStatus());
            Storage.writeListToFile(list, filePath);
        } catch (Exception e) {
            ui.showMessage("Invalid task number.");
        }
    }

    private void addTodo(String command, Ui ui) {
        String taskDesc = command.substring(5).trim();
        if (taskDesc.isEmpty()) {
            ui.showMessage("Empty task, please fill in your task.");
            return;
        }
        Task newTask = new ToDo(taskDesc);
        list.add(newTask);
        ui.showMessage("Got it. I've added this task:\n  " + newTask);
        ui.showMessage("Now you have " + list.size() + " tasks in the list.");
        Storage.writeListToFile(list, filePath);
    }

    private void addDeadline(String command, Ui ui) {
        int splitIndex = command.indexOf(" /by ");
        if (splitIndex == -1) {
            ui.showMessage("Invalid format. Use: deadline <task> /by <date>");
            return;
        }
        String taskDesc = command.substring(9, splitIndex).trim();
        String by = command.substring(splitIndex + 5).trim();
        if (taskDesc.isEmpty() || by.isEmpty()) {
            ui.showMessage("Invalid deadline. Provide a task and deadline.");
            return;
        }
        String formattedDate = DateFormatter.formatDate(by);
        if (formattedDate.startsWith("Error:")) {
            ui.showMessage(formattedDate);
            return;
        }
        Task newTask = new Deadline(taskDesc, formattedDate);
        list.add(newTask);
        ui.showMessage("Got it. I've added this task:\n  " + newTask);
        ui.showMessage("Now you have " + list.size() + " tasks in the list.");
        Storage.writeListToFile(list, filePath);
    }

    private void addEvent(String command, Ui ui) {
        int fromIndex = command.indexOf(" /from ");
        int toIndex = command.indexOf(" /to ");
        if (fromIndex == -1 || toIndex == -1 || fromIndex > toIndex) {
            ui.showMessage("Invalid format. Input: 'event <task> /from <d/M/yyyy> <HHmm> /to <d/M/yyyy> <HHmm>'");
            return;
        }
        String taskDesc = command.substring(6, fromIndex).trim();
        String from = command.substring(fromIndex + 7, toIndex).trim();
        String to = command.substring(toIndex + 4).trim();
        if (taskDesc.isEmpty()) {
            ui.showMessage("Invalid event. Task description cannot be empty.\nFormat: event <task> /from <d/M/yyyy> <HHmm> /to <d/M/yyyy> <HHmm>");
            return;
        }

        if (from.isEmpty()) {
            ui.showMessage("Invalid event. Start date and time must be provided.\nFormat: event <task> /from <d/M/yyyy> <HHmm> /to <d/M/yyyy> <HHmm>");
            return;
        }

        if (to.isEmpty()) {
            ui.showMessage("Invalid event. End date and time must be provided.\nFormat: event <task> /from <d/M/yyyy> <HHmm> /to <d/M/yyyy> <HHmm>");
            return;
        }

        String fromFormatted = DateFormatter.formatDate(from);
        String toFormatted = DateFormatter.formatDate(to);
        if (fromFormatted.startsWith("Error:") || toFormatted.startsWith("Error:")) {
            ui.showMessage("Invalid Event. Format: event <task> /from <d/M/yyyy> <HHmm> /to <d/M/yyyy> <HHmm>");
            return;
        }
        Task newTask = new Event(taskDesc, fromFormatted, toFormatted);
        list.add(newTask);
        ui.showMessage("Got it. I've added this task:\n  " + newTask);
        ui.showMessage("Now you have " + list.size() + " tasks in the list.");
        Storage.writeListToFile(list, filePath);
    }

    private void deleteTask(String command, Ui ui) {
        try {
            int index = Integer.parseInt(command.substring(7)) - 1;
            ui.showMessage("Noted. I've removed this task: " + list.get(index).getStatus());
            list.remove(index);
            ui.showMessage("Now you have " + list.size() + " tasks in the list.");
            Storage.writeListToFile(list, filePath);
        } catch (Exception e) {
            ui.showMessage("Invalid task number, unable to remove task!");
        }
    }
    private void findTask(String command, Ui ui) {
        try {
            if (command.length() < 4) {
                throw new IllegalArgumentException("Search term too short.");
            }

            String find = command.substring(4);
            int num = 0;
            ui.showMessage("Here are the tasks in your list from your search: " + find);

            for (int i = 0; i < list.size(); i++) {
                String task = list.get(i).toString();
                if (task != null && task.contains(find)) { // Check for null values
                    ui.showMessage((i + 1) + ". " + task);
                    num++;
                }
            }

            if (num == 0) {
                ui.showMessage("No task found.");
            }
        } catch (StringIndexOutOfBoundsException e) {
            ui.showMessage("Invalid search: search term too short.");
        } catch (NullPointerException e) {
            ui.showMessage("Error: Task list contains null entries.");
        }
    }

    private void cheerTask(String command, Ui ui) {
        ArrayList<String> cheer = new ArrayList<>();
        cheer.add("Hip Hip Hooray engineers!");
        cheer.add("Let's go, you can do it fellow engineers!");
        cheer.add("You got this, hang on engineers!");
        Random rand = new Random();
        ui.showMessage(cheer.get(rand.nextInt(cheer.size())));
    }
}
