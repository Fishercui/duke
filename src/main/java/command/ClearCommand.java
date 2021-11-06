package command;

public class ClearCommand extends Command{

    public static final String COMMAND_WORD="clear";
    public static final String MESSAGE_USAGE="||"+COMMAND_WORD+": clear all the tasks in the list.";

    @Override
    public void execute() {
        taskList.clear();
        System.out.println("Tasks have been cleared");
    }
}