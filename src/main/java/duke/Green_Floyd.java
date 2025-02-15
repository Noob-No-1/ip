package duke;

public class Green_Floyd {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private boolean isExit;

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
                handleCommand(fullCommand);
                isExit = this.isExit;
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
    public String handleCommand(String input) throws BrainrotException {
        String[] parsedInput = Parser.parseInput(input);
        String action = parsedInput[0];
        String details = parsedInput[1];
        assert action != null : "Empty action field";
        switch (action) {
        case "bye":
            ui.bye();
            isExit = true;
        case "list":
            return tasks.listTasks();
        case "mark":
            return markTaskAsDone(details);
        case "unmark":
            return markTaskAsUndone(details);
        case "delete":
            return deleteTask(details);
        case "todo":
            return addTodo(details);
        case "deadline":
            return addDeadline(details);
        case "event":
            return addEvent(details);
        case "find":
            return findTask(details);
        default:
            return "Bruh what you yapping about";
        }
    }

    /**
     * Marks a task as done.
     *
     * @param details The index of the task to mark as done.
     * @throws BrainrotException If the index is invalid.
     * @return String message of the response from the bot
     */
    private String markTaskAsDone(String details) throws BrainrotException {
        int index = parseIndex(details);
        Task task = tasks.getTask(index);
        task.markAsDone();
        storage.saveTasks(tasks.getTasks());
        return "Nice! I've marked this task as done:\n\n\t" + task.toString();
    }

    /**
     * Marks a task as undone.
     *
     * @param details The index of the task to mark as undone.
     * @throws BrainrotException If the index is invalid.
     */
    private String markTaskAsUndone(String details) throws BrainrotException {
        int index = parseIndex(details);
        Task task = tasks.getTask(index);
        task.markAsUndone();
        storage.saveTasks(tasks.getTasks());
        return "OK, I've marked this task as not done yet:\n\n\t" + task.toString();
    }

    /**
     * Deletes a task from the task list.
     *
     * @param details The index of the task to delete.
     * @throws BrainrotException If the index is invalid.
     */
    private String deleteTask(String details) throws BrainrotException {
        int index = parseIndex(details);
        Task task = tasks.getTask(index);
        tasks.deleteTask(index);
        storage.saveTasks(tasks.getTasks());
        return ui.printDeletedTaskStr(task, tasks.size());
    }

    /**
     * Adds a ToDos task to the task list.
     *
     * @param details The description of the todo task.
     * @throws BrainrotException If the description is empty.
     */
    private String addTodo(String details) throws BrainrotException {
        if (details.isEmpty()) {
            throw new BrainrotException("The description of a todo cannot be empty.");
        }
        Task task = new ToDos(details, false);
        tasks.addTask(task);
        //ui.printAddedTask(task, tasks.size());
        storage.saveTasks(tasks.getTasks());
        return ui.printAddedTaskStr(task, tasks.size());
    }

    /**
     * Adds a deadline task to the task list.
     *
     * @param details The description and deadline of the task.
     * @throws BrainrotException If the description or deadline is invalid.
     */
    private String addDeadline(String details) throws BrainrotException {
        String[] parts = details.split("/by", 2);
        if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            throw new BrainrotException("Invalid deadline format. Use: deadline <description> /by <date>");
        }
        Task task = new Deadlines(parts[0].trim(), parts[1].trim(), false);
        tasks.addTask(task);
        //ui.printAddedTask(task, tasks.size());
        storage.saveTasks(tasks.getTasks());
        return ui.printAddedTaskStr(task, tasks.size());
    }

    /**
     * Adds an event task to the task list.
     *
     * @param details The description, start time, and end time of the event.
     * @throws BrainrotException If the description or times are invalid.
     */
    private String addEvent(String details) throws BrainrotException {
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
        //ui.printAddedTask(task, tasks.size());
        storage.saveTasks(tasks.getTasks());
        return ui.printAddedTaskStr(task, tasks.size());
    }

    /**
     * Prints tasks that match the keyword provided
     * @param details keyword from user input
     * @throws BrainrotException
     */
    public String findTask(String details) throws BrainrotException {
        String message = "Here are the matching tasks in your list: \n\n\t";
        int matchCount = 0;
        TaskList matched = new TaskList();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.getTask(i);
            if (task.getDescription().contains(details)) {
                matched.addTask(task);
                //System.out.println((matchCount + 1) + "." + task);
                matchCount++;
            }
        }
        if (matchCount == 0) {
            return "No tasks found with the keyword: " + details;
        }
        return message + matched.listTasks();
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