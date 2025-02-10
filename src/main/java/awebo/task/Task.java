package awebo.task;

public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
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

    public String getType() {
        return "";
    }

    @Override
    public String toString() {
        return "[" + getType() + "]" + getStatus();
    }
}
