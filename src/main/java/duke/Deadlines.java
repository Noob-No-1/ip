package duke;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class represents a task with a specific deadline.
 * It extends the Task class to provide a representation for tasks that have a deadline.
 */
public class Deadlines extends Task {
    public LocalDateTime by;

    public String by_str;

    public String description;

    /**
     * Constructs a Deadline object
     * @param description a string describing what the task is about
     * @param by a string representing the deadline of the current task
     * @param status a boolean indicating the current task done
     */
    public Deadlines(String description, String by, boolean status) {
        super(description, status);
        this.description = description;
        this.by_str = by;
        this.by = parseDateTime(by);
    }

    /**
     * Parses the string of by to LocalDateTime type
     * @param by a String of the deadline
     * @return return the deadline in LocalDateTime type
     */
    private LocalDateTime parseDateTime(String by) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return LocalDateTime.parse(by, formatter);
    }

    /**
     * Converts the Deadline object to string to be written into the data file
     * @return a String of the file format of the Deadline object
     */
    public String toFileFormat() { //D | 0 | return book | June 6thk
        return "D" + " | " + (super.isDone ? "1" : "0") + " | " + description + " | " + by_str;
    }

    public String getDueDate() {
        return by_str;
    }

    /**
     * Converts the Deadline object to String format to be printed
     * @return  a String format to be printed
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a");
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }
}
