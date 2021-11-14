package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {

    protected String description;
    protected boolean isDone;
    private LocalDateTime taskTime=LocalDateTime.of(2000,1,1,0,0);
    protected LocalDateTime finishTime;
    private static final String TASK_TYPE=" ";

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description,boolean isDone,LocalDateTime finishTime) {
        this.description = description;
        this.isDone = isDone;
        if(this.isDone){
            this.finishTime=finishTime;
        }
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public LocalDateTime getFinishTime(){
        return finishTime;
    }

    public void markdone(LocalDateTime finishTime){
        isDone = true;
        this.finishTime = finishTime;
    }

    public String getDoneIcon() {
        return (isDone ? "X"
                : " ");// mark done task with X
    }

    public boolean getStatus(){

        return isDone;
    }

    public String getDescription() {
        return description;
    }

    public String getTaskType(){
        return TASK_TYPE;
    }

    public LocalDateTime getTaskTime(){
        return taskTime;
    }

    @Override
    public boolean equals(Object obj) {

        Task otherTask = (Task) obj;
        return obj == this // short circuit if same object
                || (obj instanceof Task // instanceof handles nulls
                && otherTask.description.equals(this.description)
                && otherTask.isDone == this.isDone
                && otherTask.taskTime.equals(this.taskTime));
    }


        @Override
        public String toString(){
            return "["+getDoneIcon()+"] "+getDescription();
        }
}
