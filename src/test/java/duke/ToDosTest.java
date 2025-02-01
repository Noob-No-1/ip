package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class ToDosTest {
    @Test
    public void toFileFormatTest() {
        ToDos todo = new ToDos("Do nothing", false);
        String fileFormat = todo.toFileFormat();
        String expectedFileFormat = "T | 0 | Do nothing";
        assertEquals(fileFormat, expectedFileFormat);
    }

    @Test
    public void toStringTest() {
        ToDos todo = new ToDos("Do nothing", false);
        String stringFormat = todo.toString();
        String expectedStringFormat = "[T][ ] Do nothing";
        assertEquals(stringFormat, expectedStringFormat);
    }
}
