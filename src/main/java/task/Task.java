package task;

public class task {

    protected String description;
    protected boolean isDone;

    public task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public task(String description,boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public void markdone(){
        isDone = true;
    }

    public String getDoneIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public boolean getStatus(){

        return isDone;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString(){
        String mystring = getDoneIcon() + " " + description;
        return mystring;
    }
}
