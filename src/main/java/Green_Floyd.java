public class Green_Floyd {
    public String separateBar = new String("------------------------------------------");

    public void printSeparateBar(){
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
        chatBot.farewell();
    }
}
