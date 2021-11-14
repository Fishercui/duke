package storage;

import Utils.Utils;
import exception.IllegalValueException;
import task.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TaskListDecoder {
    /**
     * Decodes the storage data file into an {@code taskList} object.
     */
    public static final Pattern To_Format=Pattern.compile("T[|](?<isDone>[01])[|](?<taskDesc>[^|]+)[|]" +
            "(?<finishTime>[^|]*)");
    public static final Pattern Deadline_Format=Pattern.compile("D[|](?<isDone>[01])" +
            "[|](?<taskDesc>[^|]+)" +
            "[|](?<planTime>[^|]+)[|]"+
            "(?<finishTime>[^|]*)");
    public static final Pattern Event_Format=Pattern.compile("E[|](?<isDone>[01])" +
            "[|](?<taskDesc>[^|]+)" +
            "[|](?<planTime>[^|]+)[|]"+
            "(?<finishTime>[^|]*)");
    /**
     * Decodes {@code encodedTasklist} into an {@code TaskList} containing the decoded tasks.
     *
     * @throws IllegalValueException if any of the fields is invalid.
     */
    public static TaskList decodeTaskList(List<String> encodedTasklist) throws IllegalValueException {

        final List<Task> decodedTasks = new ArrayList<>();
        for (String encodedTask : encodedTasklist){
            decodedTasks.add(decodeTaskFromString(encodedTask));
        }
        return new TaskList(decodedTasks);
    }

    private static Task decodeTaskFromString(String encodedTask) throws IllegalValueException{
        final Matcher matcherTodo = To_Format.matcher(encodedTask.trim());
        final Matcher matcherDeadline = Deadline_Format.matcher(encodedTask.trim());
        final Matcher matcherEvent = Event_Format.matcher(encodedTask.trim());

        boolean isDone;
        if (matcherTodo.matches()) {
            isDone="1".equals(matcherTodo.group("isDone"));
            return new ToDo(matcherTodo.group("taskDesc"), isDone,
                    isDone? Utils.getDatetimeFromString(matcherTodo.group("finishTime")) : null  );
        } else if (matcherDeadline.matches()) {
            isDone="1".equals(matcherDeadline.group("isDone"));
            return new Deadline(matcherDeadline.group("taskDesc"),
                    Utils.getDatetimeFromString(matcherDeadline.group("planTime")), isDone,
                    isDone? Utils.getDatetimeFromString(matcherDeadline.group("finishTime")) : null  );
        }
        else if (matcherEvent.matches()) {
            isDone="1".equals(matcherEvent.group("isDone"));
            return new Event(matcherEvent.group("taskDesc"),
                    Utils.getDatetimeFromString(matcherEvent.group("planTime")), isDone,
                    isDone? Utils.getDatetimeFromString(matcherEvent.group("finishTime")) : null  );
        }
        else throw new IllegalValueException("No match, please check your txt file format");

    }
}
