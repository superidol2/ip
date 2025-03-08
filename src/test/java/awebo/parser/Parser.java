package awebo.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

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
        parser = new Parser(taskList);
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
        parser.processCommand("deadline Submit report /by 02/10/2025 1000", mockUi);
        assertEquals(1, taskList.size());
        assertTrue(taskList.get(0) instanceof Deadline);
        String allMessages = mockUi.getAllMessages();
        String expectedOutput = "Got it. I've added this task:\n"
                + "  [D][ ] Submit report (by: 2nd of October 2025, 10AM)\n"
                + "Now you have 1 tasks in the list.";
        assertEquals(expectedOutput, allMessages);
    }


    @Test
    void testProcessCommand_eventCommand_addsTask() {
        assertEquals(0, taskList.size());
        parser.processCommand("event Team meeting /from 2025/02/15 1000 /to 2025/02/15 1200", mockUi);
        assertEquals(1, taskList.size());
        assertTrue(taskList.get(0) instanceof Event);
    }


    @Test
    void testProcessCommand_unknownCommand() {
        parser.processCommand("unknown command", mockUi);
        assertEquals("Unknown command. Try 'todo', 'deadline', or 'event'.", mockUi.getLastMessage());
    }

    @Test
    void testProcessCommand_findCommand() {
        parser.processCommand("todo book", mockUi);
        parser.processCommand("find book", mockUi);
        String allMessages = mockUi.getAllMessages();
        String[] messages = allMessages.split("\n");
        StringBuilder actualOutput = new StringBuilder();
        for (int i = 3; i < messages.length; i++) {
            actualOutput.append(messages[i]).append("\n");
        }
        String finalOutput = actualOutput.toString().trim();
        String expectedOutput = "Here are the tasks in your list from your search: book\n"
                + "1. [T][ ] book";
        assertEquals(expectedOutput, finalOutput);
    }




    @Test
    void testProcessCommand_cheerCommand() {
        parser.processCommand("cheer", mockUi);
        String lastMessage = mockUi.getLastMessage();
        assertTrue(lastMessage.contains("engineers"), "Expected cheer message but got: " + lastMessage);
    }

    // Mock UI to capture output messages
    static class MockUi extends Ui {
        private final List<String> messages = new ArrayList<>();

        @Override
        public void showMessage(String message) {
            messages.add(message);
        }

        public String getLastMessage() {
            return messages.isEmpty() ? "" : messages.get(messages.size() - 1);
        }

        public String getAllMessages() {
            return String.join("\n", messages);
        }
    }
}

