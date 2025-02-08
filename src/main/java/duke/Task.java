package duke;

abstract class Task {
    protected String description;
    public boolean isDone;

    public Task(String description, boolean status) {
        this.description = description;
        this.isDone = status;
    }

    public abstract String toFileFormat();

    /**
     * Checks the status of the Task object
     * @return a String indicating whether it is done or not
     */
    public String getStatus() {
        return (isDone ?"X" :" ");
    }

    /**
     * Marks a task as done
     */
    public void markAsDone() {
        setDone(true);
    }

    /**
     * Unmarks a task
     */
    public void markAsUndone() {
        setDone(false);
    }

    /**
     * Gets the description of the task
     * @return a String of the description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets status to specified state
     * @param isDone a boolean indicating the status
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Converts task object into standard string format
     * @return a String representation of the task
     */
    @Override
    public String toString() {
        return "[" + getStatus() + "] " + description;
    }
}
