package task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Iterator;


public class TaskList implements Iterable<Task>{
    private final List<Task> taskList=new ArrayList<>();

    public TaskList(){ }


    public TaskList(Collection<Task> Tasks) {
        taskList.addAll(Tasks);
    }

    public TaskList(TaskList load) {
        taskList.addAll(load.taskList);
    }


    public void addTask(Task toAdd) throws DuplicateTaskException {
        if(contains(toAdd)){
            throw new DuplicateTaskException();
        }
        taskList.add(toAdd);
    }

    public boolean contains(Task tasks){
        for(Task task : taskList){
            if(task.equals(tasks)){
                return true;
            }
        }
        return false;
    }

    public Task TaskIdx(int taskIdx){
        return taskList.get(taskIdx-1);
    }

    public void removeTask(Task tasks){
        taskList.remove(tasks);
    }

    public void removeTask(int taskIdx){
        taskList.remove(taskIdx);
    }

    public int getSize(){
        return taskList.size();
    }

    public TaskList getAllTasks(){
        return new TaskList(taskList);
    }

    public void clear() {
        taskList.clear();
    }

    @Override
    public Iterator<Task> iterator() {
        return taskList.iterator();
    }

    public static class DuplicateTaskException extends Exception{
        public DuplicateTaskException() { super("There is a same task which already existed in the task list.");  }
    }
}
