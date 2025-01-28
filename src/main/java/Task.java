abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description, boolean status) {
        this.description = description;
        this.isDone = status;
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public abstract String toFileFormat();

    public String getStatus() {
        return (isDone ?"Done!" :" ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatus() + "] " + description;
    }
}
