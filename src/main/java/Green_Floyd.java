import java.util.ArrayList;
import java.util.Scanner;

public class Green_Floyd {
    public String separateBar = new String("#########################################################");
    private boolean isRunning;

    private ArrayList<Task> list = new ArrayList<>();
    public Green_Floyd() {
        this.isRunning = true;
    }
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (isRunning) {
            String userInput = scanner.nextLine();
            handleCommand(userInput);
        }
    }

    public void echo(String input) {
        printSeparateBar();
        System.out.println(input);
        printSeparateBar();
    }
    public void exitChat() {
        farewell();
        isRunning = false;
    }
    public void handleCommand (String input) { //handle different behaviors base on user input
        //formatting input
        String[] parts = input.split(" ", 2);
        String action = parts[0].trim().toLowerCase();
        String details = parts.length > 1 ? parts[1].trim() :"";
        switch (action) {
            case "bye":
                exitChat();
                break;
            case "list":
                printSeparateBar();
                listTask();
                printSeparateBar();
                break;
            case "mark": //input structure: action + no.
                printSeparateBar();
                markDone(action, details);
                printSeparateBar();
                break;
            case "unmark"://input structure: action + no.
                printSeparateBar();
                undone(action, details);
                printSeparateBar();
                break;
            case "delete"://input structure: action + no.
                printSeparateBar();
                deleteTask(action, details);
                printSeparateBar();
                break;
            case "todo":
                printSeparateBar();
                addToList(details, "T");
                printSeparateBar();
                break;
            case "deadline":
                printSeparateBar();
                addToList(details, "D");
                printSeparateBar();
                break;
            case "event":
                printSeparateBar();
                addToList(details, "E");
                printSeparateBar();
                break;
            default:
                printSeparateBar();
                System.out.println("Unknown type of instruction, please adhere to the input format. ");
                printSeparateBar();
        }
    }
    public void printSeparateBar() { //print separation bar for aesthetic reason
        System.out.println(separateBar);
    }

    public void printAddedCase(Task t) {
        System.out.println("Got it, added tasks to the task list:");
        System.out.println("  " + t.toString());
        System.out.println("Currently have " + list.size() + " tasks in your list");
    }

    public void deleteTask(String action, String taskNum) {
        try {
            int index = Integer.parseInt(taskNum);
            if (index < 0 || index > list.size()) {
                throw new IndexOutOfBoundsException("Invalid index number!");
            }
            Task task = list.get(index - 1);
            System.out.println("Noted. Deleted this task:\n");
            System.out.println("  " + task.toString());
            list.remove(index - 1);
            System.out.println("Now you have " + list.size() + " tasks in the lists.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid index");
        } catch (NumberFormatException e) {
            System.out.println("Please enter command with a valid task number e.g. 'mark 2'");
        }
    }
    public void markDone(String action, String details) {
        try {
            int index = Integer.parseInt(details);
            if (index < 0 || index > list.size()) {
                throw new IndexOutOfBoundsException("Invalid index number!");
            }
            Task task = list.get(index - 1);
            task.markAsDone();
            System.out.println("Nice! I've marked this task done:\n");
            System.out.println(task.toString());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid index");
        } catch (NumberFormatException e) {
            System.out.println("Please enter command with a valid task number e.g. 'mark 2'");
        }
    }

    public void undone(String action, String details) {
        try {
            int index = Integer.parseInt(details);
            if (index < 0 || index > list.size()) {
                throw new IndexOutOfBoundsException("Invalid index number!");
            }
            Task task = list.get(index - 1);
            task.markAsUndone();
            System.out.println("Ok, I've marked this task as not done yet:\n");
            System.out.println(task.toString());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid index");
        } catch (NumberFormatException e) {
            System.out.println("Please enter command with a valid task number e.g. 'mark 2'");
        }
    }
    public void listTask() { //List all added tasks in the list, print it in console
        int tasks = list.size();
        if (tasks == 0) {
            System.out.println("No tasks added yet! Try typing tasks to me, I will add them to list");
        } else {
            //System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks; i++) {
                Task task = list.get(i);
                System.out.println( (i + 1) + "." + task.toString());
            }
        }
    }
    public void addToList(String input, String type) { //add the input task into list
        if (input == "") {
            System.out.println("Description cannot be empty");
        } else {
            switch (type) {
                case "T":
                    ToDos t = new ToDos(input);
                    list.add(t);
                    printAddedCase(t);
                    break;
                case "D":
                    String[] parts = input.split("/by", 2);
                    if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
                        System.out.println("Error: Please specify a valid description and deadline.");
                        return;
                    }
                    Deadlines d = new Deadlines(parts[0].trim(), parts[1].trim());
                    list.add(d);
                    printAddedCase(d);
                    break;
                case "E":
                    String[] parts1 = input.split("/from", 2);
                    if (parts1.length < 2) {
                        System.out.println("Error: Please specify a valid description, start, and end time.");
                        return;
                    }

                    String description = parts1[0].trim();
                    String[] timeParts = parts1[1].split("/to", 2);
                    if (timeParts.length < 2 || description.isEmpty() || timeParts[0].trim().isEmpty() || timeParts[1].trim().isEmpty()) {
                        System.out.println("Error: Please specify a valid description, start, and end time.");
                        return;
                    }
                    Events e = new Events(description, timeParts[0].trim(), timeParts[1].trim());
                    list.add(e);
                    printAddedCase(e);
                    break;
                default:
                    System.out.println("Unknown task type.");
            }
        }
    }
    public void greeting() { //say hello :)
        printSeparateBar();
        System.out.println("Hello! I'm Green Floyed\n" + "What can I do for you?\n");
        printSeparateBar();
    }

    public void farewell() { //say byebye
        printSeparateBar();
        System.out.println("Till next time. See you!\n");
        printSeparateBar();
    }
    public static void main(String[] args) {
        Green_Floyd  chatBot = new Green_Floyd();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        chatBot.greeting();
        chatBot.run();
        //chatBot.farewell();
    }
}
