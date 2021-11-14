package parser;

import command.*;
import exception.*;
import task.Deadline;
import task.Event;
import task.ToDo;
import Utils.Utils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class parser {
    /**
     * Separation for command word and args.
     */
    public static final Pattern Command_Format= Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    public static final Pattern Keyword_Format = Pattern.compile("(?<keywords>\\S+(?:\\s+\\S+)*)");

    public static final Pattern Deadline_Format = Pattern.compile("(?<deadlineDesc>[^/]+)" +
            " by/(?<byYear>\\d{4})"+"-"+"(?<byMonth>\\d{2})"+"-"+"(?<byDay>\\d{2})" +
            " " + "(?<byHour>\\d{2})(?<byMin>\\d{2})");

    public static final Pattern Event_Format = Pattern.compile("(?<eventDesc>[^/]+)" +
            " at/(?<atYear>\\d{4})"+"-"+"(?<atMonth>\\d{2})"+"-"+"(?<atDay>\\d{2})" +
            " "+"(?<atHour>\\d{2})(?<atMin>\\d{2})");

    public static final Pattern Index_Format = Pattern.compile("(?<targetIndex>\\d+)");
    public static final Pattern Done_Format = Pattern.compile("(?<targetIndex>\\d+)"+" on/" +
            "(?<year>\\d{4})"+"-"+"(?<month>\\d{2})"+"-"+"(?<day>\\d{2})"+
            " "+"(?<hour>\\d{2})(?<minute>\\d{2})");

    /**
     * Parses user input into command for execution.
     *
     * @param inputCommand full user input string
     * @return the command based on the user input
     */
    public Command parse(String inputCommand) {
        final Matcher matcher = Command_Format.matcher(inputCommand.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand("This is a incorrect format, " +
                    " you may type 'help' to see the correct format.");
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        switch(commandWord){

            case AddCommand.COMMAND_WORD1:
                return prepareAddTodo(arguments);
            case AddCommand.COMMAND_WORD2:
                return prepareAddDeadline(arguments);
            case AddCommand.COMMAND_WORD3:
                return prepareAddEvent(arguments);

            case DoneCommand.COMMAND_WORD:
                return prepareDone(arguments);
            case DeleteCommand.COMMAND_WORD:
                return prepareDelete(arguments);
            case FindCommand.COMMAND_WORD:
                return prepareFind(arguments);

            case ListCommand.COMMAND_WORD:
                return new ListCommand();
            case ExitCommand.COMMAND_WORD:
                return new ExitCommand();
            case ClearCommand.COMMAND_WORD:
                return new ClearCommand();
            case HelpCommand.COMMAND_WORD:
            default:
                return new HelpCommand();
        }

    }
    /**
     * Parses arguments in the context of the add todotask command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private Command prepareAddTodo(String args) {
        return new AddCommand(new ToDo(args.trim()));
    }
    /**
     * Parses arguments in the context of the add deadline task command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private Command prepareAddDeadline(String args) {
        final Matcher matcher= Deadline_Format.matcher(args.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand("This is a incorrect format, " +
                    " you may type 'help' to see the correct format.");
        }
        return new AddCommand(new Deadline(matcher.group("deadlineDesc"),
                LocalDateTime.of(Integer.parseInt(matcher.group("byYear")),
                        Integer.parseInt(matcher.group("byMonth")),
                        Integer.parseInt(matcher.group("byDay")),
                        Integer.parseInt(matcher.group("byHour")),
                        Integer.parseInt(matcher.group("byMin"))) ));
    }
    /**
     * Parses arguments in the context of the add Event task command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private Command prepareAddEvent(String args) {
        final Matcher matcher= Event_Format.matcher(args.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand("This is a incorrect format, " +
                    " you may type 'help' to see the correct format.");
        }

        return new AddCommand(new Event(matcher.group("eventDesc"),
                LocalDateTime.of(Integer.parseInt(matcher.group("atYear")),
                        Integer.parseInt(matcher.group("atMonth")),
                        Integer.parseInt(matcher.group("atDay")),
                        Integer.parseInt(matcher.group("atHour")),
                        Integer.parseInt(matcher.group("atMin"))) ));
    }
    /**
     * Parses argument in the context of the mark a task as done command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private Command prepareDone(String args) {
        try {
            Matcher matcher =Done_Format.matcher(args.trim());
            if (matcher.matches()) {
                int targetIndex=Integer.parseInt((matcher.group("targetIndex")));
                return new DoneCommand(targetIndex,
                        LocalDateTime.of(Integer.parseInt(matcher.group("year")),
                                Integer.parseInt(matcher.group("month")),
                                Integer.parseInt(matcher.group("day")),
                                Integer.parseInt(matcher.group("hour")),
                                Integer.parseInt(matcher.group("minute")))  );
            }
            else{
                int targetIndex = parseArgsAsDisplayedIndex(args);
                return new DoneCommand(targetIndex,LocalDateTime.now());
            }
        }catch (ParseException pe){
            return new IncorrectCommand("This is a incorrect format, " +
                    " you may type 'help' to see the correct format.");
        }

    }
    /**
     * Parses argument in the context of the delete a task command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private Command prepareDelete(String args) {
        try {
            final int targetIndex = parseArgsAsDisplayedIndex(args);
            assert targetIndex>0 : "Invalid index.";
            return new DeleteCommand(targetIndex);
        }catch (ParseException pe){
            return new IncorrectCommand("This is a incorrect format, " +
                    " you may type 'help' to see the correct format.");
        }
    }
    /**
     * Parses argument in the context for find task command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private Command prepareFind(String args) {
        final  Matcher matcher=Keyword_Format.matcher((args.trim()));
        if(!matcher.matches()){
            return new IncorrectCommand("This is a incorrect format, " +
                    " you may type 'help' to see all the commands.");
        }

        // keywords delimited by whitespace
        final String[] keywords = matcher.group("keywords").split("\\s+");
        final Set<String> keywordSet = new HashSet<>(Arrays.asList(keywords));
        return new FindCommand(keywordSet);
    }




    private int parseArgsAsDisplayedIndex(String args) throws ParseException{
        final Matcher matcher = Index_Format.matcher(args.trim());
        if(!matcher.matches()){
            throw   new  ParseException("Invalid index.");
        }
        return Integer.parseInt(matcher.group("targetIndex"));
    }

    public static class ParseException extends Exception {
        ParseException(String message) {
            super(message);
        }
    }


}
