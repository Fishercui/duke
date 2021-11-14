package command;

import task.TaskList;
import task.Task;


public class AddCommand extends Command{
    /**
     * add a task to the list with the command keyword
     */
    public static final String COMMAND_WORD1 = "todo";
    public static final String COMMAND_WORD2 = "deadline";
    public static final String COMMAND_WORD3 = "event";

    public static final String HELP_MESSAGE=  "-- "+COMMAND_WORD1+": Adds a Todo Task to the task list. \n" +
            "<< Example: todo borrow book >>\n" +
            "-- "+ COMMAND_WORD2+": Adds a Deadline task to the task list.\n" +
            "<< Example: deadline return book by/2021-12-01 1200 >>\n" +
            "-- "+ COMMAND_WORD3+": Adds an Event task to the task list.\n" +
            "<< Example: event project meeting at/2021-12-01 1200 >>";
    private final Task toAdd;

    public AddCommand(Task toAdd ) {
        this.toAdd = toAdd;
    }


    public void execute() {
        try {
            taskList.addTask(toAdd);
            System.out.println("New " + " Added: "+toAdd.toString());
            System.out.println("Now you have "+taskList.getSize()+" tasks in the list.");
        } catch (TaskList.DuplicateTaskException e) {
            e.printStackTrace();
        }

    }
}
