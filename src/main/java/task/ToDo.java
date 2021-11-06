package task;

import java.time.LocalDateTime;

public class ToDo extends Task {

    public ToDo(String content) {
        super(content);
    }

    public ToDo(String description, boolean isDone, LocalDateTime finishTime) {
        super(description,isDone,finishTime);
    }

    public ToDo editContent (String newContent) {
        ToDo newTask = new ToDo(newContent);
        newTask.isDone = this.isDone;
        return newTask;
    }



    @Override
    public String toString() {
        if (!this.isDone) {
            return "T | 0 | " + super.toString();
        } else {
            return "T | 1 | " + super.toString();
        }
    }
}
