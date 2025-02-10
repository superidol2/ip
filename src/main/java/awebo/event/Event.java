package awebo.event;

import awebo.task.Task;

public class Event extends Task {
    private String from;
    private String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }
    public String getFrom() {
        return from;
    }
    public String getTo() {
        return to;
    }
    @Override
    public String getType() {
        return "E"; // "E" for Awebo.Event.Awebo.Event
    }

    @Override
    public String toString() {
        return "[" + getType() + "]" + getStatus() + " (from: " + from + " to: " + to + ")";
    }
}
