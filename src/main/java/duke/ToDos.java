package duke;

import duke.Task;

public class ToDos extends Task {
    private final String type = "T";
    public String description;
    public ToDos(String description, boolean status) {
        super(description, status);
        this.description = description;
    }

    public String toFileFormat() { //T | 1 | read book
        return "T" + " | " + (super.isDone ? "1" : "0") + " | " + description;
    }
    @Override
    public String toString() {
        return "[" + type + "]" + super.toString();
    }
}
