package command;

import task.Task;
import java.util.*;
public class FindCommand extends Command {

    public static final String COMMAND_WORD="find";

    public static final String HELP_MESSAGE= "-- "+COMMAND_WORD+": finds the specific tasks based on the keywords you entered.\n" +
            "<< Example: find meeting >>";


    private final Set<String> keywords;

    public FindCommand(Set<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public void execute(){
        final List<Task> foundTasks= getTasksFromKeyword(keywords);
        if(foundTasks.isEmpty()){
            System.out.print("No Result Found\n");
            return;
        }
        for (int i = 1; i <= foundTasks.size(); i++) {
            System.out.println(i + ". " + foundTasks.get(i-1).toString());
        }

    }


    private List<Task> getTasksFromKeyword(Set<String> keywords) {
        final List<Task> matchedTasks= new ArrayList<>();
        for(Task task : taskList.getAllTasks()){
            Set<String> descriptionWords= new HashSet<>( Arrays.asList(task.getDescription().split(" ")));
            if (descriptionWords.containsAll(keywords)){
                matchedTasks.add(task);
            }
        }
        return  matchedTasks;
    }


}
