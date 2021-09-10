import java.util.ArrayList;
import java.util.Scanner;



public class test {

    public static void main(String[] args) {

        String line;
        Tasklist tasklist = new Tasklist();
        Scanner in = new Scanner(System.in);
        //line = in.nextLine();
        //for (int i=0; i<3; i++){
        //    line = in.nextLine();
        //    Task task = new Task(line);
        //   tasklist.addtolist(task);
        //    if(line.equals("list")){
        //        System.out.println(tasklist.showlist());}
        //}

        String test = "deadline return book /by Sunday";

        String test1 = test.split(" ", 2)[1];
        String test2 = test1.split("/by")[0];
        String test3 = test1.split("/by")[1];

        System.out.println(test3);

        //Task task = new Task("test123");
        //Task task2 = new Task("sdsdsds");
        //Tasklist tasklist = new Tasklist();
        //tasklist.addtolist(task);
        //tasklist.addtolist(task2);
        //task.markdone();

        //System.out.println(tasklist.showlist());



    }
}
