import java.util.Scanner;

public class Green_Floyd {
    public String separateBar = new String("#########################################################");
    private boolean isRunning;

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
    public void handleCommand (String input) {
        switch (input.trim().toLowerCase()) {
            case "bye":
                exitChat();
                break;
            default:
                echo(input);
        }
    }
    public void printSeparateBar() {
        System.out.println(separateBar);
    }


    public void greeting() {
        printSeparateBar();
        System.out.println("Hello! I'm Green Floyed \n" + "What can I do for you?\n");
        printSeparateBar();
    }

    public void farewell() {
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
        chatBot.farewell();
    }
}
