package Utils;

import exception.IllegalValueException;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    public static final Pattern BASIC_DATE_TIME_FORMAT=Pattern.compile("(?<year>\\d{4})"+"-"+"(?<month>\\d{2})"+
            "-"+"(?<day>\\d{2})"+
            " "+"(?<hour>\\d{2})(?<minute>\\d{2})");

    public static  boolean elementsAreUnique(Collection<?> items){
        final Set<Object> testSet=new HashSet<>();
        for (Object item :items){
            final boolean itemAlreadyExists=!testSet.add(item);// set is not allowed duplicate.
            if(itemAlreadyExists){
                return false;
            }
        }
        return  true;
    }

    public static LocalDateTime getDatetimeFromString(String timeString) throws IllegalValueException {
        Matcher matcher=BASIC_DATE_TIME_FORMAT.matcher(timeString.trim());
        if(!matcher.matches()){
            throw new IllegalValueException("Date format no match, please check your txt file format");
        }
        return  LocalDateTime.of(Integer.parseInt(matcher.group("year")),
                Integer.parseInt(matcher.group("month")),
                Integer.parseInt(matcher.group("day")),
                Integer.parseInt(matcher.group("hour")),
                Integer.parseInt(matcher.group("minute")));
    }
}
