package duke;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {
    public LocalDateTime by;

    public String by_str;

    public String description;
    public Deadlines(String description, String by, boolean status) {
        super(description, status);
        this.description = description;
        this.by_str = by;
        this.by = parseDateTime(by);
    }

    private LocalDateTime parseDateTime(String by) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return LocalDateTime.parse(by, formatter);
    }

    public String toFileFormat() { //D | 0 | return book | June 6thk
        return "D" + " | " + (super.isDone ? "1" : "0") + " | " + description + " | " + by_str;
    }
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a");
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }
}
