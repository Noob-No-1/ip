package duke;

import duke.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private final String separator = "#########################################################";

    /**
     * Genrates welcome messages when app is started
     */
    public String greeting() {
        return "Ni Hao fine shiz\n" + "What can I do for you?\n";
    }

    /**
     * Generates goodbye message and exit the app
     */
    public String bye() {
        return "See you fine shiz =_=";
    }

    /**
     * Prints current task lists in specified format to user
     * @param list a list of the currently saved tasks
     */
    public void listTask(ArrayList<Task> list) { //List all added tasks in the list, print it in console
        int tasks = list.size();
        if (tasks == 0) {
            System.out.println("No tasks added yet! Try typing tasks to me, I will add them to list");
        } else {
            for (int i = 0; i < tasks; i++) {
                Task task = list.get(i);
                System.out.println( (i + 1) + "." + task.toString());
            }
        }
    }
    /**
     * Executes the ListCommand by displaying the current tasks in the task list
     * to the user. If the task list is empty, returns a String indicating that the list is empty
     * is shown. Otherwise, returns a String representing a summary of the tasks.
     *
     * @param list The TaskList to be displayed.
     * @return A String showing a list of the tasks.
     */
    public String listTask(TaskList list) { //List all added tasks in the list, print it in console
        int tasks = list.size();
        if (tasks == 0) {
            return "No tasks added yet! Try typing tasks to me, I will add them to list";
        } else {
            return  list.listTasks();
        }
    }
    /**
     * Prints loading errors
     * @param message error message
     */
    public void showError(String message) {
        System.out.println("Error: " + message);
    }

    /**
     * Prints error when issues occurs when loading task list from local file
     */
    public void showLoadingError() {
        System.out.println("Error loading tasks from file.");
    }

    /**
     * Scans user input lines and return the user input as string
     * @return user input
     */
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Prints add task message when user added a task
     * @param task a task that is added
     * @param taskCount current number of tasks before adding
     */
    public void printAddedTask(Task task, int taskCount) {
        System.out.println("Got it, added tasks to the task list:");
        System.out.println("  " + task);
        System.out.println("Currently have " + taskCount + " tasks in your list");
    }

    public String printAddedTaskStr (Task task, int taskCount) {
        return "Got it, added tasks to the task list:" + ".\n\n" + task.toString() + "\n\n" +
                "Currently have " + taskCount + " tasks in your list";
    }

    /**
     * Prints delete task message when user deleted a task
     * @param task a task that is deleted
     * @param taskCount current number of tasks before deleting
     */
    public void printDeletedTask(Task task, int taskCount) {
        System.out.println("Noted. Deleted this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    public String printDeletedTaskStr(Task task, int taskCount) {
        return "Got it, deleted tasks to the task list:" + ".\n\n" + task.toString() + "\n\n" +
                "Currently have " + taskCount + " tasks in your list";
    }
    /**
     * Prints separation line between messages
     */
    public void printSeparateBar() {
        System.out.println(separator);
    }
}
