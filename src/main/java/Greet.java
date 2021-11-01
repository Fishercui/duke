import java.util.ArrayList;
import java.util.Scanner;
import java.text.ParseException;

public class Greet {
    public static void main(String[] args) throws DukeException {

        String line;

        Scanner in = new Scanner(System.in);
        System.out.print("____________________________________________________________\n");
        System.out.print("Hello! I'm Duke\n");
        System.out.print("What can I do for you?\n");
        System.out.print("____________________________________________________________\n");
        line = in.nextLine();
        Tasklist tasklist = new Tasklist();
        Task task = new Task(line);


        while (!line.equals("bye")) {
            System.out.print("____________________________________________________________\n");
            System.out.println(line);
            System.out.print("____________________________________________________________\n");

            if (line.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                System.out.println(tasklist.showlist());
                System.out.print("____________________________________________________________\n");
            } else {
                String input = line.split(" ")[0];
                try {
                    if (input.equals("done")) {
                        int doneIndex = Integer.valueOf(line.split(" ")[1]);
                        task.markdone();
                        tasklist.done(doneIndex);

                        System.out.println(task.getDoneIcon() + tasklist.taskname(doneIndex));
                        System.out.print("____________________________________________________________\n");
                    } else if (input.equals("todo")) {
                        try {
                            String keyword = line.split(" ", 2)[1];
                            todo newtask = new todo(keyword);
                            System.out.println(newtask.toString());
                            tasklist.addtolist(newtask);
                            System.out.println("Got it. I've added this task: ");
                            System.out.println("Now you have " + tasklist.getlistsize() + " tasks in the list");
                            System.out.print("____________________________________________________________\n");
                        } catch (Exception e) {
                            throw new DukeException(("OOPS!!! The description cannot be empty."));
                        }
                    } else if (input.equals("deadline")) {
                        System.out.println("Got it. I've added this task:");
                        String keyword = line.split(" ", 2)[1];
                        String description = keyword.split("/by")[0];
                        String by = keyword.split("/by")[1];
                        Deadline newtask = new Deadline(description, by);
                        System.out.println(newtask.toString());
                        tasklist.addtolist(newtask);
                        System.out.println("Now you have " + tasklist.getlistsize() + " tasks in the list");
                        System.out.print("____________________________________________________________\n");
                    } else if (input.equals("event")) {
                        System.out.println("Got it. I've added this task:");
                        String keyword = line.split(" ", 2)[1];
                        String description = keyword.split("/at")[0];
                        String by = keyword.split("/at")[1];
                        event newtask = new event(description, by);
                        System.out.println(newtask.toString());
                        tasklist.addtolist(newtask);
                        System.out.println("Now you have " + tasklist.getlistsize() + " tasks in the list");
                        System.out.print("____________________________________________________________\n");
                    } else {
                        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-{");
                    }
                } catch (DukeException e1) {
                    System.out.println(e1);

                }
            }
            line = in.nextLine();

        }
        System.out.print("____________________________________________________________\n");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.print("____________________________________________________________\n");
    }
}