package task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Iterator;

/**
 * Create tasks list.
 */
public class TaskList implements Iterable<Task>{
    private final List<Task> taskList=new ArrayList<>();

    /**
     * Construct an empty taskList.
     */
    public TaskList(){ }


    public TaskList(Collection<Task> Tasks) {
        taskList.addAll(Tasks);
    }
    /**
     * Load tasks to the list
     * @param load the task to add
     */
    public TaskList(TaskList load) {
        taskList.addAll(load.taskList);
    }

    /**
     * Add task to the list
     * @param addTask and check with has duplicate task in the list
     */
    public void addTask(Task toAdd) throws DuplicateTaskException {
        if(contains(toAdd)){
            throw new DuplicateTaskException();
        }
        taskList.add(toAdd);
    }

    /**
     * check if a task is already exist in the taskList
     * @param tasks the task to check
     */
    public boolean contains(Task tasks){
        for(Task task : taskList){
            if(task.equals(tasks)){
                return true;
            }
        }
        return false;
    }

    /**
     * gets index of the task in the list
     */
    public Task TaskIdx(int taskIdx){
        return taskList.get(taskIdx-1);
    }
    /**
     * remove task with the same index in the list
     */

    public void removeTask(int taskIdx){
        taskList.remove(taskIdx);
    }
    /**
     * get the size of the tasklist
     */
    public int getSize(){
        return taskList.size();
    }


    public TaskList getAllTasks(){
        return new TaskList(taskList);
    }
    /**
     * clean the tasklist
     */
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
