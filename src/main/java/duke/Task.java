package duke;

abstract class Task {
    protected String description;
    public boolean isDone;

    public Task(String description, boolean status) {
        this.description = description;
        this.isDone = status;
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
