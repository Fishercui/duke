package command;

public class HelpCommand extends Command {
    public static final String COMMAND_WORD="help";

    public static final String HELP_MESSAGE = "-- "+COMMAND_WORD + ": Shows command instructions.";

    @Override
    public void execute(){

        System.out.println(AddCommand.HELP_MESSAGE
                + "\n" + ClearCommand.HELP_MESSAGE
                + "\n" + DeleteCommand.HELP_MESSAGE
                + "\n" + DoneCommand.HELP_MESSAGE
                + "\n" + FindCommand.HELP_MESSAGE
                + "\n" + HelpCommand.HELP_MESSAGE
                + "\n" + ListCommand.HELP_MESSAGE
                + "\n" + ExitCommand.HELP_MESSAGE);
    }
}
