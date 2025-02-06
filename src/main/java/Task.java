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
