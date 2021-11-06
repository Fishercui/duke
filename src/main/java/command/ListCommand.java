package command;

public class ListCommand extends Command {

    public static final String COMMAND_WORD="list";

    public static final String MESSAGE_USAGE="||"+COMMAND_WORD+": list the all the tasks." ;


    @Override
    public void execute(){
        if(taskList.getSize()==0){
            System.out.println("No tasks in the list.");
            return;
        }
        for (int i = 1; i <= taskList.getSize(); i++) {
            System.out.println(i + ". " + taskList.TaskIdx(i).toString());
        }
    }
}