package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Storage class represents the local storage where tasks are being saved so
 * that they persist even after the chatbot is exited. It contains methods to
 * load and save tasks from and into a .txt file for local storage.
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads in tasks saved in file to the chatbot
     * @return a tasklist
     * @throws BrainrotException skibidi error
     */
    public ArrayList<Task> load() throws BrainrotException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            throw new BrainrotException("File not found: " + filePath);
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = Parser.parseTaskFromFile(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (FileNotFoundException e) {
            throw new BrainrotException("Error loading tasks from file.");
        }
        return tasks;
    }

    /**
     * Saves task list into the specified file (filepath)
     * @param tasks the task list
     */
    public void saveTasks(ArrayList<Task> tasks) {
        try {
            File file = new File(filePath);
            PrintWriter writer = new PrintWriter(file);
            for (Task task : tasks) {
                writer.println(task.toFileFormat());
            }
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error saving tasks to file.");
        }
    }

}
