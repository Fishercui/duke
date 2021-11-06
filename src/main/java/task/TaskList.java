package task;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Iterator;

public class TaskList {
    private final List<task> taskList=new ArrayList<>();

    public TaskList(){ }

    public TaskList(task[] tasks){
        final List<task> initialTasks= Arrays.asList(tasks);
        taskList.addAll(initialTasks);
    }

    public TaskList(Collection<task> tasks) {
        taskList.addAll(tasks);
    }

    public void addTask(task toAdd) throws DuplicateTaskException {
        if(contains(toAdd)){
            throw new DuplicateTaskException();
        }
        taskList.add(toAdd);
    }

    public boolean contains(task tasks){
        for(task task : taskList){
            if(task.equals(tasks)){
                return true;
            }
        }
        return false;
    }

    public task TaskIdx(int taskIdx){
        return taskList.get(taskIdx-1);
    }

    public void removeTask(task tasks){
        taskList.remove(tasks);
    }

    public void removeTask(int taskIdx){
        taskList.remove(taskIdx);
    }

    public int getSize(){
        return taskList.size();
    }

    public TaskList showAllTasks(){
        return new TaskList(taskList);
    }

    public void clear() {
        taskList.clear();
    }


    public static class DuplicateTaskException extends Exception{
        public DuplicateTaskException() { super("There is a same task which already existed in the task list.");  }
    }
}
