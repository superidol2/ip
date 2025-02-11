package awebo.todo;

import awebo.task.Task;

/**
 * The {@code ToDo} class represents a task of type "ToDo".
 * It inherits from the {@code Task} class and specifies the task type as "T".
 */
public class ToDo extends Task {

    /**
     * Constructs a new {@code ToDo} task with the given description.
     *
     * @param description The description of the ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the type of the task, which is "T" for ToDo tasks.
     *
     * @return The type of the task, which is "T".
     */
    @Override
    public String getType() {
        return "T"; // "T" for ToDo task
    }
}
