package exception;

public class DuplicateTaskException extends Exception{

    public DuplicateTaskException() { super("There is a same task which already existed in the task list.");  }

}
