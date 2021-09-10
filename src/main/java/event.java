public class event extends Task{
    protected String by;
    public event(String description, String by) {
        super(description);
        this.by = by;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + by + ")";
    }

}
