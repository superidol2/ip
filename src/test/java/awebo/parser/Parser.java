package awebo.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import awebo.deadline.Deadline;
import awebo.event.Event;
import awebo.task.Task;
import awebo.todo.ToDo;
import awebo.ui.Ui;

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
        assertEquals("Your task list is empty.", mockUi.getLastMessage());
    }

    @Test
    void testProcessCommand_todoCommand() {
        parser.processCommand("todo Read book", mockUi);
        assertEquals(1, taskList.size());
        assertTrue(taskList.get(0) instanceof ToDo);
        assertEquals("Got it. I've added this task:\n  [T][ ] Read book\nNow you have 1 tasks in the list.",
                mockUi.getLastMessage());
    }

    @Test
    void testProcessCommand_deadlineCommand() {
        parser.processCommand("deadline Submit report /by 2025-02-10", mockUi);
        assertEquals(1, taskList.size());
        assertTrue(taskList.get(0) instanceof Deadline);
        assertEquals("Got it. I've added this task:\n  [D][ ] Submit report (by: 2025-02-10)\nNow you have 1 tasks in the list.",
                mockUi.getLastMessage());
    }

    @Test
    void testProcessCommand_eventCommand() {
        parser.processCommand("event Team meeting /from 2025-02-15 1000 /to 2025-02-15 1200", mockUi);
        assertEquals(1, taskList.size());
        assertTrue(taskList.get(0) instanceof Event);
        assertEquals("Got it. I've added this task:\n  [E][ ] Team meeting (from: 2025-02-15 1000, to: 2025-02-15 1200)\nNow you have 1 tasks in the list.",
                mockUi.getLastMessage());
    }

    @Test
    void testProcessCommand_unknownCommand() {
        parser.processCommand("unknown command", mockUi);
        assertEquals("Unknown command. Try 'todo', 'deadline', or 'event'.", mockUi.getLastMessage());
    }

    @Test
    void testProcessCommand_findCommand() {
        parser.processCommand("find book", mockUi);
        assertEquals("Here are the tasks in your list from your search: book", mockUi.getLastMessage());
    }

    @Test
    void testProcessCommand_cheerCommand() {
        parser.processCommand("cheer", mockUi);
        String lastMessage = mockUi.getLastMessage();
        assertTrue(lastMessage.contains("engineers"), "Expected cheer message but got: " + lastMessage);
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

