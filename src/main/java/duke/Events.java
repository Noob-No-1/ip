package duke;

import duke.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Event class represents a task with a specific time period.
 * It extends the Task class to provide a representation for tasks that have a start and end time.
 */
public class Events extends Task {
    private final String type = "E";
    public LocalDateTime from;
    public LocalDateTime to;

    public String from_str;
    public String to_str;
    public String details;
    public Events(String description, String from, String to, boolean status) {

        super(description, status);
        this.details = description;
        from_str = from;
        to_str = to;
        this.from = parseDateTime(from);
        this.to = parseDateTime(to);

    }

    public String getFrom_str() {
        return from_str;
    }

    public String getTo_str() {
        return to_str;
    }

    /**
     * Parses the string of by to LocalDateTime type
     * @param s a String of the deadline
     * @return return the deadline in LocalDateTime type
     */private LocalDateTime parseDateTime(String s) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return LocalDateTime.parse(s, formatter);

    }

    /**
     * Converts the Event object to string to be written into the data file
     * @return a String of the file format of the Deadline object
     */
    public String toFileFormat() { //E | 0 | project meeting | Mon 2pm | 4pm
        return "E" + " | " + (super.isDone ? "1" : "0") + " | " + description + " | " + from_str + " | " + to_str;
    }


    /**
     * Converts the Event object to String format to be printed
     * @return  a String format to be printed
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a");
        return "[E]" + super.toString() + " (from: " + from.format(formatter) + " to: " + to.format(formatter) + ")";

    }
}
