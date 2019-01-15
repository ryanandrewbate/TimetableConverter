import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by rebecca on 01/10/15.
 */
public class Event {

    private String name;
    private String desc;
    private String loc;
    private int start; //hrs, non timezone specific
    private int end; //as above
    private String dates;

    /*public Event(String inputname, String inputdesc, String inputloc, int inputstart, int inputend ){
        name = inputname;
        desc = inputdesc;
        loc = inputloc;
        start = inputstart;
        end = inputend;
    }*/

    public Event(ArrayList<String> data){
        System.out.println(data.get(1));
        loc = data.get(6);
        name = data.get(3).substring(5,8)+" "+data.get(5);
        desc = data.get(4)+" "+data.get(3)+" "+data.get(7);
        start = Integer.parseInt(data.get(1).replaceAll(":", ""));
        end = Integer.parseInt(data.get(2).replaceAll(":",""));
        dates = data.get(8);
    }

    public String Output(){
        return "Name: "+name+", Description: "+desc;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public String getLoc() {
        return loc;
    }

    public String[] getDates() {
        return dates.split(";");
    }




}
