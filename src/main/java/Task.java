public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getName(){

        return  description;

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

    public String toString(){
        String mystring = getDoneIcon() + " " + description;
        return mystring;
    }

}
