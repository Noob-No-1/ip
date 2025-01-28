public class Events extends Task{
    private final String type = "E";
    public String from;
    public String to;
    public String details;
    public Events(String description, String from, String to, boolean status) {
        super(description, status);
        this.details = description;
        this.from = from;
        this.to = to;
    }

    public String toFileFormat() { //E | 0 | project meeting | Mon 2pm | 4pm
        return "E" + " | " + (super.isDone ? "1" : "0") + " | " + description + " | " + from + " | " + to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
