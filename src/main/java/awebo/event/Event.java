package awebo.event;

import awebo.task.Task;

/**
 * The {@code Event} class represents a task of type "Event".
 * It contains details about the event's description, start time, and end time.
 * The class provides methods to retrieve the event type and a string representation of the event.
 */
public class Event extends Task {
    /** The start time of the event. */
    private String from;

    /** The end time of the event. */
    private String to;

    /**
     * Constructs an {@code Event} task with a description, start time, and end time.
     *
     * @param description The description of the event task.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the start time of the event.
     *
     * @return The start time of the event.
     */
    public String getFrom() {
        return from;
    }

    /**
     * Returns the end time of the event.
     *
     * @return The end time of the event.
     */
    public String getTo() {
        return to;
    }
    @Override
    public String getType() {
        return "E"; // "E" for Event
    }

    /**
     * Returns a string representation of the event task, including its type, status, start, and end time.
     *
     * @return A formatted string representation of the event task.
     */
    @Override
    public String toString() {
        return "[" + getType() + "]" + getStatus() + " (from: " + from + " to: " + to + ")";
    }
}
