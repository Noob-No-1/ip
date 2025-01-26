public class ToDos extends Task{
    private final String type = "T";
    public ToDos(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[" + type + "]" + super.toString();
    }
}
