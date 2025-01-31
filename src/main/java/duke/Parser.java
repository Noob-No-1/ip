package duke;

import duke.Deadlines;
import duke.Events;
import duke.Task;

/**
 * Parses user input into an action and details.
 */
public class Parser {
    /**
     * Parses the user input into an action and details.
     *
     * @param input The full command entered by the user.
     * @return A String array containing the action and details.
     */
    public static String[] parseInput(String input) {
        String[] parts = input.split(" ", 2);
        String action = parts[0].trim().toLowerCase();
        String details = parts.length > 1 ? parts[1].trim() : "";
        return new String[]{action, details};
    }

    /**
     * Parses a task from a file line.
     *
     * @param taskString The string representation of the task from the file.
     * @return The parsed task, or null if the line is invalid.
     */
    public static Task parseTaskFromFile(String taskString) {
        try {
            String[] parts = taskString.split(" \\| ");
            String type = parts[0];
            boolean status = parts[1].equals("1");
            String description = parts[2];

            switch (type) {
            case "T":
                return new ToDos(description, status);
            case "E":
                String from = parts[3];
                String to = parts[4];
                return new Events(description, from, to, status);
            case "D":
                String by = parts[3];
                return new Deadlines(description, by, status);
            default:
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
}