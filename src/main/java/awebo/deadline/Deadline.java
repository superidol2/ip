package awebo.deadline;

import awebo.task.Task;

public class Deadline extends Task {
    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getBy() {
        return by;
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
