import java.util.ArrayList;

/**
 * Manages the list of tasks and provides operations to add, delete, and retrieve tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the specified tasks.
     *
     * @param tasks The list of tasks to initialize the TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the list by its index.
     *
     * @param index The index of the task to be deleted.
     * @throws BrainrotException If the index is invalid.
     */
    public void deleteTask(int index) throws BrainrotException {
        if (index < 0 || index >= tasks.size()) {
            throw new BrainrotException("Invalid task index.");
        }
        tasks.remove(index);
    }

    /**
     * Retrieves a task by its index.
     *
     * @param index The index of the task to be retrieved.
     * @return The task at the specified index.
     * @throws BrainrotException If the index is invalid.
     */
    public Task getTask(int index) throws BrainrotException {
        if (index < 0 || index >= tasks.size()) {
            throw new BrainrotException("Invalid task index.");
        }
        return tasks.get(index);
    }

    /**
     * Returns the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks.
     */
    public int size() {
        return tasks.size();
    }
}