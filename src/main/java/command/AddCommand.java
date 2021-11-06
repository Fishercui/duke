package command;

import task.TaskList;
import task.Task;


public class AddCommand extends Command{

    public static final String COMMAND_WORD1 = "todo";
    public static final String COMMAND_WORD2 = "deadline";
    public static final String COMMAND_WORD3 = "event";

    public static final String MESSAGE_USAGE=  "||"+COMMAND_WORD1+": Adds a Todo Task to the task list. \n" +
            "||"+ COMMAND_WORD2+": Adds a Deadline task to the task list.\n" +
            "||"+ COMMAND_WORD3+": Adds an Event task to the task list.\n";
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
