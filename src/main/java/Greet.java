import java.util.ArrayList;
import java.util.Scanner;

public class Greet {
    public static void main(String[] args) {

        String line;

        Scanner in = new Scanner(System.in);
        System.out.print("____________________________________________________________\n");
        System.out.print("Hello! I'm Duke\n");
        System.out.print("What can I do for you?\n");
        System.out.print("____________________________________________________________\n");
        line = in.nextLine();
        Tasklist tasklist = new Tasklist();
        Task task = new Task(line);






        while (!line.equals("bye")){
            System.out.print("____________________________________________________________\n");
            System.out.println(line);
            System.out.print("____________________________________________________________\n");




            if (line.equals("list")){
                    System.out.print("____________________________________________________________\n");
                System.out.println(tasklist.showlist());
                    System.out.print("____________________________________________________________\n");
                }
            else {
                String input = line.split(" ")[0];
                if (input.equals("done")) {
                    int doneIndex = Integer.valueOf(line.split(" ")[1]);
                    task.markdone();
                    tasklist.done(doneIndex);
                    System.out.print("____________________________________________________________\n");
                    System.out.println(task.getDoneIcon() + task.description);
                    System.out.print("____________________________________________________________\n");
                } else {
                    Task newtask = new Task(line);
                    tasklist.addtolist(newtask);


                }
            }

            line = in.nextLine();



        }
        System.out.print("____________________________________________________________\n");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.print("____________________________________________________________\n");


    }
}
