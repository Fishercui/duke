package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime at;
    private static final String TASK_TYPE="E";

    public Event(String description,LocalDateTime at) {
        super(description);
        this.at=at;
    }

    public Event(String description,LocalDateTime at, boolean isDone,LocalDateTime finishTime){
        super(description,isDone,finishTime);
        this.at=at;
    }


    @Override
    public String getTaskType() {
        return TASK_TYPE;
    }

    @Override
    public LocalDateTime getTaskTime() {
        return at;
    }

    @Override
    public boolean equals(Object obj) {

        if (! super.equals(obj)) return false;
        if (this.getClass() != obj.getClass())
            return false;
        Event e = (Event) obj;
        return this.at.equals(e.at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }
}
