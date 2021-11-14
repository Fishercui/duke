package command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DoneCommand extends Command {
    public static final String COMMAND_WORD="done";

    public static final String HELP_MESSAGE="-- "+COMMAND_WORD+": mark a task as done status.\n" +
            "<< Example: done 1 >>";
    private LocalDateTime finishTime;
    public DoneCommand(int targetIndex,LocalDateTime finishTime) {
        super(targetIndex);
        this.finishTime=finishTime;
    }

    @Override
    public void execute(){
        taskList.TaskIdx(getIdx()).markdone(finishTime);
        System.out.print( taskList.TaskIdx(getIdx())+" is done on "+
                finishTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"))+"\n");
    }
}
