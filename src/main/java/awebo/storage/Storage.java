package awebo.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import awebo.deadline.Deadline;
import awebo.event.Event;
import awebo.task.Task;
import awebo.todo.ToDo;


/**
 * The {@code Storage} class provides functionality for writing a list of objects to a file.
 * This class is abstract and cannot be instantiated.
 */
public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    private String formatTaskString(List<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        for (Task task : tasks) {
            if (task instanceof ToDo) {
                sb.append("T|" + task.getDescription() + "|" + (task.isDone() ? "1" : "0") + '\n');
            }
            if (task instanceof Deadline) {
                sb.append("D|" + task.getDescription() + "|"
                        + ((Deadline) task).getBy() + "|" + (task.isDone() ? "1" : "0") + '\n');
            }
            if (task instanceof Event) {
                sb.append("E|" + task.getDescription()
                        + "|" + ((Event) task).getFrom() + "|" + ((Event) task).getTo()
                        + "|" + (task.isDone() ? "1" : "0") + '\n');
            }
        }
        String ans = sb.toString();
        return ans;
    }

    private void parseTaskString(String taskString, List<Task> tasks) {
        String[] split = taskString.split("\\|");
        try {
            if (taskString.startsWith("T")) {
                Task taskToAdd = new ToDo(split[1]);
                if (split[2].equals("1")) {
                    taskToAdd.markDone();
                }
                tasks.add(taskToAdd);
            } else if (taskString.startsWith("D")) {
                Task taskToAdd = new Deadline(split[1], split[2]);
                if (split[3].equals("1")) {
                    taskToAdd.markDone();
                }
                tasks.add(taskToAdd);
            } else if (taskString.startsWith("E")) {
                Task taskToAdd = new Event(split[1], split[2], split[3]);
                if (split[4].equals("1")) {
                    taskToAdd.markDone();
                }
                tasks.add(taskToAdd);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Saves tasks to file according filePath
     * @param tasks
     */
    public void saveTasks(List<Task> tasks) {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            String taskString = formatTaskString(tasks);
            fw.write(taskString);
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Loads tasks according to the saved task in the filePath
     * @return Task list
     */
    public List<Task> loadTasks() {
        this.createFile();
        List<Task> tasks = new ArrayList<>();
        try {
            File f = new File(this.filePath);
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                parseTaskString(s.nextLine(), tasks);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Creates file according to filePath if the file does not exist
     */
    public void createFile() {
        File file = new File(this.filePath);
        if (file.exists()) {
            return;
        }
        try {
            if (!file.createNewFile()) {
                throw new IOException("File creation failed.");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
