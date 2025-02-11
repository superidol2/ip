package awebo.task;

/**
 * The {@code Task} class represents a task with a description and completion status.
 * It provides methods to mark the task as done or undone, get its status,
 * retrieve its description, and obtain a string representation of the task.
 */
public class Task {

    /** The description of the task. */
    private String description;

    /** The completion status of the task. */
    private boolean isDone;

    /**
     * Constructs a new {@code Task} with a given description.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the completion status of the task.
     *
     * @return {@code true} if the task is done, {@code false} otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Returns the status of the task, including whether it is done or not, followed by its description.
     *
     * @return The status of the task, formatted as "[X] description" if done, or "[ ] description" if not.
     */
    public String getStatus() {
        return (isDone ? "[X] " : "[ ] ") + description;
    }

    /**
     * Returns the type of the task. This method can be overridden in subclasses to specify the task type.
     *
     * @return An empty string by default, to be overridden by subclasses.
     */
    public String getType() {
        return "";
    }

    /**
     * Returns a string representation of the task, including its type and status.
     *
     * @return A formatted string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getType() + "]" + getStatus();
    }
}

