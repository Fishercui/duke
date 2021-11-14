package command;

public class IncorrectCommand extends Command {
    /**
     * Show incorrect command and feedback to user.
     */
    public final String feedbackToUser;

    public IncorrectCommand(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }


    public void execute() {
        System.out.println(feedbackToUser);
    }
}
