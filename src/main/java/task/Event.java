package task;

public class Event extends task {

    protected String by;

    public Event(String description, String by) {
        super(description);
        this.by = by;

    }
    @Override
    public String toString() {
        return "[" + "E" + "]" + super.toString() + " (at: " + by + ")";
    }
}
