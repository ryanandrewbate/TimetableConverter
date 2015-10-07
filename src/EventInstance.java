import java.util.Date;

/**
 * Created by rebecca on 01/10/15.
 */
public class EventInstance {

    private String datestring;
    private Event event;

    public EventInstance(Event inputevent, String inputdate ) {

        event = inputevent;
        datestring = inputdate;

    }
    public Event getEvent(){
        return event;
    }

    public String getDate() {
        return datestring;
    }

    public String output(){
        return "Name: "+event.getName()+", Description: "+event.getDesc()+", Date: "+datestring;

    }

    public String getISOStart(){
        String start = "";
        start = start + "20" + datestring.split("/")[2];
        start = start + datestring.split("/")[1];
        start = start + datestring.split("/")[0];
        String starttime = Integer.toString(getEvent().getStart());
        if(starttime.length() == 3) starttime = "0"+starttime;
        start = start + "T" + starttime;
        start = start + "00";
        return start;
    }

    public String getISOEnd(){
        String end = "";
        end = end + "20" + datestring.split("/")[2];
        end = end + datestring.split("/")[1];
        end = end + datestring.split("/")[0];
        String endtime = Integer.toString(getEvent().getEnd());
        if(endtime.length() == 3) endtime = "0"+endtime;
        end = end + "T" + endtime;
        end = end + "00";
        return end;
    }

}
