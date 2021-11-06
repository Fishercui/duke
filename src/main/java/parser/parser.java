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

    public static final Pattern BASIC_COMMAND_FORMAT= Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    public static final Pattern KEYWORDS_ARGS_FORMAT = Pattern.compile("(?<keywords>\\S+(?:\\s+\\S+)*)");

    public static final Pattern TASK_TYPE_DEADLINE_ARGS_FORMAT = Pattern.compile("(?<deadlineDesc>[^/]+)" +
            " by/(?<byYear>\\d{4})"+"-"+"(?<byMonth>\\d{2})"+"-"+"(?<byDay>\\d{2})" +
            " " + "(?<byHour>\\d{2})(?<byMin>\\d{2})");

    public static final Pattern TASK_TYPE_EVENT_ARGS_FORMAT = Pattern.compile("(?<eventDesc>[^/]+)" +
            " at/(?<atYear>\\d{4})"+"-"+"(?<atMonth>\\d{2})"+"-"+"(?<atDay>\\d{2})" +
            " "+"(?<atHour>\\d{2})(?<atMin>\\d{2})");

    public static final Pattern TASK_INDEX_ARGS_FORMAT = Pattern.compile("(?<targetIndex>\\d+)");
    public static final Pattern TASK_DONE_TIME_FORMAT = Pattern.compile("(?<targetIndex>\\d+)"+" on/" +
            "(?<year>\\d{4})"+"-"+"(?<month>\\d{2})"+"-"+"(?<day>\\d{2})"+
            " "+"(?<hour>\\d{2})(?<minute>\\d{2})");

    public static final Pattern VIEW_DONE_TASK_BY_TIME_FORMAT = Pattern.compile("from/(?<fromTime>[^/]+)"+" to/(?<toTime>[^/]+)");

    public Command parse(String inputCommand) {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(inputCommand.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand("This is a incorrect format, " +
                    " you may type the list to see all the commands.");
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
            case ViewDoneCommand.COMMAND_WORD:
                return prepareViewDone(arguments);

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

    private Command prepareAddTodo(String args) {
        return new AddCommand(new ToDo(args.trim()));
    }

    private Command prepareAddDeadline(String args) {
        final Matcher matcher= TASK_TYPE_DEADLINE_ARGS_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand("This is a incorrect format, " +
                    " you may type 'help' to see all the commands.");
        }
        return new AddCommand(new Deadline(matcher.group("deadlineDesc"),
                LocalDateTime.of(Integer.parseInt(matcher.group("byYear")),
                        Integer.parseInt(matcher.group("byMonth")),
                        Integer.parseInt(matcher.group("byDay")),
                        Integer.parseInt(matcher.group("byHour")),
                        Integer.parseInt(matcher.group("byMin"))) ));
    }

    private Command prepareAddEvent(String args) {
        final Matcher matcher= TASK_TYPE_EVENT_ARGS_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand("This is a incorrect format, " +
                    " you may type 'help' to see all the commands.");
        }

        return new AddCommand(new Event(matcher.group("eventDesc"),
                LocalDateTime.of(Integer.parseInt(matcher.group("atYear")),
                        Integer.parseInt(matcher.group("atMonth")),
                        Integer.parseInt(matcher.group("atDay")),
                        Integer.parseInt(matcher.group("atHour")),
                        Integer.parseInt(matcher.group("atMin"))) ));
    }

    private Command prepareDone(String args) {
        try {
            Matcher matcher =TASK_DONE_TIME_FORMAT.matcher(args.trim());
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
                    " you may type 'help' to see all the commands.");
        }

    }

    private Command prepareDelete(String args) {
        try {
            final int targetIndex = parseArgsAsDisplayedIndex(args);
            assert targetIndex>0 : "Invalid number, the index should be larger than 0.";
            return new DeleteCommand(targetIndex);
        }catch (ParseException pe){
            return new IncorrectCommand("This is a incorrect format, " +
                    " you may type the list to see all the commands.");
        }
    }

    private Command prepareFind(String args) {
        final  Matcher matcher=KEYWORDS_ARGS_FORMAT.matcher((args.trim()));
        if(!matcher.matches()){
            return new IncorrectCommand("This is a incorrect format, " +
                    " you may type 'help' to see all the commands.");
        }

        // keywords delimited by whitespace
        final String[] keywords = matcher.group("keywords").split("\\s+");
        final Set<String> keywordSet = new HashSet<>(Arrays.asList(keywords));
        return new FindCommand(keywordSet);
    }


    private Command prepareViewDone(String args) {
        try {
            final Matcher matcher = VIEW_DONE_TASK_BY_TIME_FORMAT.matcher(args.trim());
            if (!matcher.matches()) {
                return new IncorrectCommand("This is a incorrect format, " +
                        " you may type 'help' to see all the commands.");
            }
            return new ViewDoneCommand(Utils.getDatetimeFromString(matcher.group("fromTime")),
                    Utils.getDatetimeFromString(matcher.group("toTime")));
        }catch (IllegalValueException ive){
            return new IncorrectCommand("This is a incorrect format, " +
                    " you may type 'help' to see all the commands.");
        }

    }

    private int parseArgsAsDisplayedIndex(String args) throws ParseException{
        final Matcher matcher = TASK_INDEX_ARGS_FORMAT.matcher(args.trim());
        if(!matcher.matches()){
            throw   new  ParseException("Could not match to the correct index.");
        }
        return Integer.parseInt(matcher.group("targetIndex"));
    }

    public static class ParseException extends Exception {
        ParseException(String message) {
            super(message);
        }
    }


}
