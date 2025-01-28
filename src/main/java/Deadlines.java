public class Deadlines extends Task {
    public String by;
    public String description;
    public Deadlines(String description, String by, boolean status) {
        super(description, status);
        this.description = description;
        this.by = by;
    }

    public String toFileFormat() { //D | 0 | return book | June 6thk
        return "D" + " | " + (super.isDone ? "1" : "0") + " | " + description + " | " + by;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
