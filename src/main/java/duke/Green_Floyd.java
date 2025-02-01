package duke;

public class Green_Floyd {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Goofy ahh Green_Floyd object
     * @param filePath a String of the data file
     */
    public Green_Floyd(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (BrainrotException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Gets the private task list
     * @return the inner task list
     */
    public TaskList getTasks() {
        return tasks;
    }

    /**
     * Runs the main logic
     */
    public void run() {
        ui.greeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.printSeparateBar();
                isExit = handleCommand(fullCommand);
            } catch (BrainrotException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.printSeparateBar();
            }
        }
    }

    /**
     * Handles the user command and performs the corresponding action.
     *
     * @param input The full command entered by the user.
     * @return True if the command is "bye", false otherwise.
     * @throws BrainrotException If the command is invalid or an error occurs.
     */
    boolean handleCommand(String input) throws BrainrotException {
        String[] parsedInput = Parser.parseInput(input);
        String action = parsedInput[0];
        String details = parsedInput[1];

        switch (action) {
        case "bye":
            ui.bye();
            return true;
        case "list":
            listTasks();
            break;
        case "mark":
            markTaskAsDone(details);
            break;
        case "unmark":
            markTaskAsUndone(details);
            break;
        case "delete":
            deleteTask(details);
            break;
        case "todo":
            addTodo(details);
            break;
        case "deadline":
            addDeadline(details);
            break;
        case "event":
            addEvent(details);
            break;
        case "find":
            findTask(details);
            break;
        default:
            throw new BrainrotException("Unknown command: " + action);
        }
        return false;
    }

    /**
     * Lists all tasks in the task list.
     */
    private void listTasks() throws BrainrotException {
        if (tasks.size() == 0) {
            System.out.println("No tasks added yet! Try typing tasks to me, I will add them to the list.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.getTask(i));
            }
        }
    }

    /**
     * Marks a task as done.
     *
     * @param details The index of the task to mark as done.
     * @throws BrainrotException If the index is invalid.
     */
    private void markTaskAsDone(String details) throws BrainrotException {
        int index = parseIndex(details);
        Task task = tasks.getTask(index);
        task.markAsDone();
        storage.saveTasks(tasks.getTasks());
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
    }

    /**
     * Marks a task as undone.
     *
     * @param details The index of the task to mark as undone.
     * @throws BrainrotException If the index is invalid.
     */
    private void markTaskAsUndone(String details) throws BrainrotException {
        int index = parseIndex(details);
        Task task = tasks.getTask(index);
        task.markAsUndone();
        storage.saveTasks(tasks.getTasks());
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task);
    }

    /**
     * Deletes a task from the task list.
     *
     * @param details The index of the task to delete.
     * @throws BrainrotException If the index is invalid.
     */
    private void deleteTask(String details) throws BrainrotException {
        int index = parseIndex(details);
        Task task = tasks.getTask(index);
        tasks.deleteTask(index);
        storage.saveTasks(tasks.getTasks());
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Adds a ToDos task to the task list.
     *
     * @param details The description of the todo task.
     * @throws BrainrotException If the description is empty.
     */
    private void addTodo(String details) throws BrainrotException {
        if (details.isEmpty()) {
            throw new BrainrotException("The description of a todo cannot be empty.");
        }
        Task task = new ToDos(details, false);
        tasks.addTask(task);
        ui.printAddedTask(task, tasks.size());
        storage.saveTasks(tasks.getTasks());
    }

    /**
     * Adds a deadline task to the task list.
     *
     * @param details The description and deadline of the task.
     * @throws BrainrotException If the description or deadline is invalid.
     */
    private void addDeadline(String details) throws BrainrotException {
        String[] parts = details.split("/by", 2);
        if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            throw new BrainrotException("Invalid deadline format. Use: deadline <description> /by <date>");
        }
        Task task = new Deadlines(parts[0].trim(), parts[1].trim(), false);
        tasks.addTask(task);
        ui.printAddedTask(task, tasks.size());
        storage.saveTasks(tasks.getTasks());
    }

    /**
     * Adds an event task to the task list.
     *
     * @param details The description, start time, and end time of the event.
     * @throws BrainrotException If the description or times are invalid.
     */
    private void addEvent(String details) throws BrainrotException {
        String[] parts = details.split("/from", 2);
        if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            throw new BrainrotException("Invalid event format. Use: event <description> /from <start> /to <end>");
        }
        String[] timeParts = parts[1].split("/to", 2);
        if (timeParts.length < 2 || timeParts[0].trim().isEmpty() || timeParts[1].trim().isEmpty()) {
            throw new BrainrotException("Invalid event format. Use: event <description> /from <start> /to <end>");
        }
        Task task = new Events(parts[0].trim(), timeParts[0].trim(), timeParts[1].trim(), false);
        tasks.addTask(task);
        ui.printAddedTask(task, tasks.size());
        storage.saveTasks(tasks.getTasks());
    }

    /**
     * Prints tasks that match the keyword provided
     * @param details keyword from user input
     * @throws BrainrotException
     */
    public void findTask(String details) throws BrainrotException {
        System.out.println("Here are the matching tasks in your list:");
        int matchCount = 0;
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.getTask(i);
            if (task.getDescription().contains(details)) {
                System.out.println((matchCount + 1) + "." + task);
                matchCount++;
            }
        }
        if (matchCount == 0) {
            System.out.println("No tasks found with the keyword: " + details);
        }
    }

    /**
     * Parses the task index from the user input.
     *
     * @param details The string containing the task index.
     * @return The parsed task index (zero-based).
     * @throws BrainrotException If the index is invalid.
     */
    private int parseIndex(String details) throws BrainrotException {
        try {
            int index = Integer.parseInt(details) - 1;
            if (index < 0 || index >= tasks.size()) {
                throw new BrainrotException("Invalid task index.");
            }
            return index;
        } catch (NumberFormatException e) {
            throw new BrainrotException("Please provide a valid task number.");
        }
    }

    /**
     * Runs, Idk why we need to write JavaDoc for this method but it is what it is
     * @param args args for main
     */
    public static void main(String[] args) {
        new Green_Floyd("data/task_history.txt").run();
    }
}