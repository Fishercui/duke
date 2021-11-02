import java.util.ArrayList;
import java.util.List;


public class Tasklist {
    private List<Task> tasks;

    Tasklist() {
        this.tasks = new ArrayList<>();
    }

    public void addtolist(Task task) {
        tasks.add(task);
    }

    public String showlist() {

        String result = "";
        for (int i = 0; i < tasks.size(); i++) {
            result += String.valueOf(i + 1) + "." + " " + tasks.get(i) + "\n";
        }
        return result;
    }

    public void done(int index) {
        tasks.get(index - 1).markdone();

    }

    public String taskname(int index) {
        return tasks.get(index - 1).description;
    }

    public int getlistsize() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    public Task getTaskindex(int taskindex) {
        return this.tasks.get(taskindex);
    }


    public void deleteTask(int taskindex) {

        tasks.remove(taskindex - 1);
    }

    public Task getTask(int index) {
        return this.tasks.get(index - 1);
    }
}