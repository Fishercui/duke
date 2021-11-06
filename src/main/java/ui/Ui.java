package ui;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Ui {
    private final Scanner in;
    private final PrintStream out;
    private static final String SEPARATE_LINE="_________________________________________________";
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String LOADING_ERROR_MSG="Failed to load this Duke Application from storage file.";

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public void showWelcome() {
        System.out.println("Hello from\n" + LOGO);
        System.out.println(SEPARATE_LINE);
        System.out.println("Hello! I am Duke\nWhat can I do for you?");
        System.out.println(SEPARATE_LINE);
    }

    public String readCommand() {
        out.print("Enter Command: ");
        String line=in.nextLine();
        return line;
    }

    public void showLine() {
        out.println(SEPARATE_LINE);
    }

    public void showError(String text) {
        out.println(text);
    }

    public void showLoadingError() {
        out.print(LOADING_ERROR_MSG);
    }
}
