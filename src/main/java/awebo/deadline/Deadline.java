package awebo.deadline;

import awebo.task.Task;

public class Deadline extends Task {
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
