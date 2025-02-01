package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Green_FloydTest {
    private Green_Floyd greenFloyd;

    @BeforeEach
    public void setUp() {
        greenFloyd = new Green_Floyd("taskHistoryTest");
    }

    @Test
    public void testMarkTaskAsDone_invalidIndex() {
        BrainrotException exception = assertThrows(BrainrotException.class, () -> {
            greenFloyd.handleCommand("mark 100");
        });

        assertEquals("Invalid task index.", exception.getMessage());
    }

    @Test
    public void testMarkTaskAsDone_nonNumericInput() {
        BrainrotException exception = assertThrows(BrainrotException.class, () -> {
            greenFloyd.handleCommand("mark abc");
        });

        assertEquals("Please provide a valid task number.", exception.getMessage());
    }

    @Test
    public void testAddTodo_validDescription() throws BrainrotException {
        greenFloyd.handleCommand("todo Test Todo");
        Task task = greenFloyd.getTasks().getTask(0);
        assertEquals("[T][ ] Test Todo", task.toString());
    }

    }