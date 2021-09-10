

public class todo extends Task{


    public todo(String description){
        super(description);

    }

    public String toString(){
        return "[T]" + super.toString();
    }

}
