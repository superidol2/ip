package awebo.parser;

import awebo.task.Task;
import awebo.todo.ToDo;
import awebo.ui.Ui;
import awebo.deadline.Deadline;
import awebo.event.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {
    private ArrayList<Task> taskList;
    private Parser parser;
    private MockUi mockUi;

    @BeforeEach
    void setUp() {
        taskList = new ArrayList<>();
        parser = new Parser(taskList, "test.txt");
        mockUi = new MockUi();
    }

    @Test
    void testProcessCommand_listCommand() {
        parser.processCommand("list", mockUi);
        assertEquals("No tasks available.", mockUi.getLastMessage());
    }

    @Test
    void testProcessCommand_todoCommand() {
        parser.processCommand("todo Read book", mockUi);
        assertEquals(1, taskList.size());
        assertTrue(taskList.get(0) instanceof ToDo);
        assertEquals("Got it. I've added this task:\n[T][ ] Read book\nNow you have 1 tasks in the list.", mockUi.getLastMessage());
    }

    @Test
    void testProcessCommand_deadlineCommand() {
        parser.processCommand("deadline Submit report /by 2025-02-10", mockUi);
        assertEquals(1, taskList.size());
        assertTrue(taskList.get(0) instanceof Deadline);
    }

    @Test
    void testProcessCommand_eventCommand() {
        parser.processCommand("event Team meeting /at 2025-02-15", mockUi);
        assertEquals(1, taskList.size());
        assertTrue(taskList.get(0) instanceof Event);
    }

    @Test
    void testProcessCommand_unknownCommand() {
        parser.processCommand("unknown command", mockUi);
        assertEquals("Unknown command. Try 'todo', 'deadline', or 'event'.", mockUi.getLastMessage());
    }

    // Mock UI to capture output messages
    static class MockUi extends Ui {
        private String lastMessage;

        @Override
        public void showMessage(String message) {
            this.lastMessage = message;
        }

        public String getLastMessage() {
            return lastMessage;
        }
    }
}
