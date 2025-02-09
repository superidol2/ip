package awebo.todo;

import awebo.task.Task;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }
    @Override
    public String getType() {
        return "T"; //"T" for todo
    }
}