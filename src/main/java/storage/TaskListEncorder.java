package storage;

import task.Task;
import task.TaskList;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TaskListEncorder {
    /**
     * Encodes the {@code taskList} object into a data file for storage.
     * Encodes all the {@code Task} in the {@code toSave} into a list of decodable and string to store
     */
    public static List<String> encodeTaskList(TaskList toSave){
        final List<String> encodedTasks= new ArrayList<>();
        for(int i=1;i<=toSave.getSize();i++){
            encodedTasks.add(encodeTaskToString(toSave.TaskIdx(i)));
        }
        return encodedTasks;

    }

    private static String encodeTaskToString(Task task) {
        final StringBuilder encodedTaskBuilder =new StringBuilder();

        encodedTaskBuilder.append(task.getTaskType());
        encodedTaskBuilder.append("|");
        encodedTaskBuilder.append(task.getStatus() ? "1" : "0");
        encodedTaskBuilder.append("|");
        encodedTaskBuilder.append(task.getDescription());
        encodedTaskBuilder.append("|");
        if(task.getTaskType()=="D"||task.getTaskType()=="E"){
            encodedTaskBuilder.append(task.getTaskTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")));
            encodedTaskBuilder.append("|");
        }
        if(task.getStatus()){
            encodedTaskBuilder.append(task.getFinishTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")));
        }


        return encodedTaskBuilder.toString();


    }
}
