package duke;

import duke.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    private LocalDateTime parseDateTime(String s) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return LocalDateTime.parse(s, formatter);

    }
    public String toFileFormat() { //E | 0 | project meeting | Mon 2pm | 4pm

        return "E" + " | " + (super.isDone ? "1" : "0") + " | " + description + " | " + from_str + " | " + to_str;
    }

    @Override
    public String toString() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a");
        return "[E]" + super.toString() + " (from: " + from.format(formatter) + " to: " + to.format(formatter) + ")";

    }
}
