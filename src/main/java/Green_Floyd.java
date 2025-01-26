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
            case "mark":
                printSeparateBar();
                markDone(action, details);
                printSeparateBar();
                break;
            case "unmark":
                printSeparateBar();
                undone(action, details);
                printSeparateBar();
                break;
            default:
                //echo(input);
                printSeparateBar();
                addToList(input);
                printSeparateBar();
        }
    }
    public void printSeparateBar() { //print separation bar for aesthetic reason
        System.out.println(separateBar);
    }

    public void markDone(String action, String details) {
        try {
            int index = Integer.parseInt(details);
            if (index < 0 || index >= list.size()) {
                throw new IndexOutOfBoundsException("Invalid index number!");
            }
            Task task = list.get(index - 1);
            task.markAsDone();
            System.out.println("Nice! I've marked this task done!\n");
            System.out.println("[" + task.getStatus() + "] " + task.description);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid index");
        } catch (NumberFormatException e) {
            System.out.println("Please enter command with a valid task number e.g. 'mark 2'");
        }
    }

    public void undone(String action, String details) {
        try {
            int index = Integer.parseInt(details);
            if (index < 0 || index >= list.size()) {
                throw new IndexOutOfBoundsException("Invalid index number!");
            }
            Task task = list.get(index - 1);
            task.markAsUndone();
            System.out.println("Nice! I've marked this task done!\n");
            System.out.println("[" + task.getStatus() + "] " + task.description);
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
            for (int i = 0; i < tasks; i++) {
                Task task = list.get(i);
                String description = task.description;
                String status = task.getStatus();
                System.out.println((i + 1) + ". " + "[" + status + "] " + description );
            }
        }
    }
    public void addToList(String input) { //add the input task into list
        Task task = new Task(input);
        list.add(task);
        System.out.println("Added: " + input);
    }
    public void greeting() { //say hello :)
        printSeparateBar();
        System.out.println("Hello! I'm Green Floyed \n" + "What can I do for you?\n");
        printSeparateBar();
    }

    public void farewell() { //say byebye
        printSeparateBar();
        System.out.println("Till next time. See you! \n");
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
