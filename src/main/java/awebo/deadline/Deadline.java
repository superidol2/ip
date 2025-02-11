package awebo.deadline;

import awebo.task.Task;
/**
 * The {@code Deadline} class represents a task with a specific deadline.
 * It extends the {@code Task} class and includes a deadline date/time.
 */
public class Deadline extends Task {
    /** The deadline date/time of the task. */
    private String by;
    /**
     * Constructs a {@code Deadline} task with a description and a deadline.
     *
     * @param description The description of the deadline task.
     * @param by The deadline date/time in string format.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    /**
     * Returns the type of the task.
     *
     * @return The letter "D" representing a deadline task.
     */
    @Override
    public String getType() {
        return "D"; //"D" for deadline
    }

    /**
     * Returns a string representation of the deadline task, including its type, status, and deadline.
     *
     * @return A formatted string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[" + getType() + "]" + getStatus() + " (by: " + by + ")";
    }
}

