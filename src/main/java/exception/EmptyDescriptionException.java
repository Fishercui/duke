package exception;

public class EmptyDescriptionException extends Exception{
    private String message;

    public EmptyDescriptionException(String message)
    {
        this.message=message;
    }

    @Override
    public String getMessage(){
        return message;
    }
}
