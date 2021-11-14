package task;

import java.time.LocalDateTime;

public class ToDo extends Task {
    private static final String TASK_TYPE = "T";

    public ToDo(String content) {
        super(content);
    }

    public ToDo(String description, boolean isDone, LocalDateTime finishTime) {
        super(description, isDone, finishTime);
    }

    public ToDo editContent(String newContent) {
        ToDo newTask = new ToDo(newContent);
        newTask.isDone = this.isDone;
        return newTask;
    }

    public String getTaskType() {
        return TASK_TYPE;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
