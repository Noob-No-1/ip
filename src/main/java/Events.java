public class Events extends Task{
    private final String type = "E";
    public String from;
    public String to;
    public String details;
    public Events(String description, String from, String to) {
        super(description);
        this.details = description;
        this.from = from;
        this.to = to;

    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
