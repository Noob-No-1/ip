public class Deadlines extends Task {
    public String by;
    public String description;
    public Deadlines(String description, String by) {
        super(description);
        this.description = description;
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
