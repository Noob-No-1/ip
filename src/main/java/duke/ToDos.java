package duke;

import duke.Task;

/**
 * The Todo class represents a task of type "todo".
 * It extends the Task class to provide a specific representation for todo tasks.
 */
public class ToDos extends Task {
    private final String type = "T";
    public String description;
    public ToDos(String description, boolean status) {
        super(description, status);
        this.description = description;
    }

    /**
     * Converts the ToDos object to string to be written into the data file
     * @return a String of the file format of the Deadline object
     */
    public String toFileFormat() { //T | 1 | read book
        return "T" + " | " + (super.isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Converts the ToDos object to String format to be printed
     * @return  a String format to be printed
     */
    @Override
    public String toString() {
        return "[" + type + "]" + super.toString();
    }
}
