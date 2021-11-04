package task;

public class ToDo extends task{

    public ToDo(String content) {
        super(content);
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
