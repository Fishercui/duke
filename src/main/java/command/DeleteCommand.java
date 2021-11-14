package command;

public class DeleteCommand extends Command {

    public static final String COMMAND_WORD="delete";

    public static final String HELP_MESSAGE="-- "+COMMAND_WORD+": delete task from the tasklist based on the task index.\n" +
            "<< Example: delete 1 >>";


    public DeleteCommand(int targetIndex) {
        super(targetIndex);
    }

    @Override
    public void execute(){

        taskList.removeTask(getIdx()-1);
        System.out.print("Task has been removed.\n");
    }
}
