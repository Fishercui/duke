package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



public class Deadline extends Task {

    protected LocalDateTime by;
    private static final String TASK_TYPE="D";

    /**
     * create a deadline task
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by=by;
    }

    public Deadline(String description, LocalDateTime by, boolean isDone,LocalDateTime finishTime){
        super(description,isDone,finishTime);
        this.by=by;
    }

    @Override
    public String getTaskType() {
        return TASK_TYPE;
    }

    public LocalDateTime getTaskTime() {
        return by;
    }

    @Override
    public boolean equals(Object obj) {

        if (! super.equals(obj)) return false;
        if (this.getClass() != obj.getClass())
            return false;
        Deadline d = (Deadline) obj;
        return this.by.equals(d.by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }
}