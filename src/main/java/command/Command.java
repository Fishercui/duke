package command;

import task.TaskList;

public class Command {
    /**
     * Shows executable command.
     */
    protected TaskList taskList;
    private int taskIdx = -1;

    protected Command() {
    }

    private void setIdx(int taskIdx) {
        this.taskIdx = taskIdx;
    }
    public Command(int taskIdx) {
        this.setIdx(taskIdx);
    }

    public int getIdx() {
        return taskIdx;
    }

    public void execute() {
    }

    public void setTask(TaskList taskList){
        this.taskList=taskList;
    }
}
