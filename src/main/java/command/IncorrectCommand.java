package command;

public class IncorrectCommand extends Command {

    public final String feedbackToUser;

    public IncorrectCommand(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }


    public void execute() {
        System.out.println(feedbackToUser);
    }
}
