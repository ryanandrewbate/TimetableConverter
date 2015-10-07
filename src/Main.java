import java.io.*;
import java.util.ArrayList;

public class Main {

    private ArrayList<Event> events = new ArrayList<Event>();
    private ArrayList<EventInstance> instances = new ArrayList<EventInstance>();

    public static void main(String[] args) throws IOException {
        Main obj = new Main();
        // Checks for not null but does not validate file existence nor correct number of variables
        if (args.length == 2){
            String input = args[0];
            String html = obj.getFileContents(input);
            obj.parseHTML(html);
            obj.createInstances();
            //System.out.println(obj.icsOutput());
            String output = args[1];
            obj.icsOutput(output);
        } else {
            System.out.println("Usage: java Main timetable.html timetable.ics");
        }

    }

    public String getFileContents(String path)
            throws IOException, FileNotFoundException
        {
        FileReader fr = new FileReader(path);
        BufferedReader textReader = new BufferedReader(fr);

        String contents = "";
        String line = " ";
        while (line != null) {
            line = textReader.readLine();
            contents = contents + line;
        }

        return contents;
    }

    public void createInstances() {
        for(Event event: events) {
            for(String date: event.getDates()) {
                EventInstance instance = new EventInstance(event,date);
                instances.add(instance);
            }
        }
    }

    public void parseHTML(String input){

        int offset = 0;
        while(input.indexOf("<td>Day</td>",offset) > offset) {

            // Find the next day
            int daystart = input.indexOf("<td>Day</td>", offset);
            int dayend = input.indexOf("</table>", offset);

            int offset1 = daystart;

            while (offset1 < dayend) {
                //Find the next event.
                //if(input.indexOf("<tr>", offset1) > ){

                //NEEDS SOMETHING TO BREAK LOOP ONCE LAST DAY HAS BEEN PROCESSED

                int eventstart = input.indexOf("<tr>", offset1);
                int eventend = input.indexOf("</tr>", eventstart);
                //System.out.println("Eventstart: "+eventstart);
                //System.out.println(eventend);

                int offset2 = eventstart;
                ArrayList<String> eventdata = new ArrayList<String>();

                // Go through each field of the event's data
                while (offset2 < eventend) {
                    int datastart = input.indexOf("<td>", offset2);
                    int dataend = input.indexOf("</td>", offset2);
                    offset2 = dataend + 5;
                    //System.out.println(input.substring(datastart + 4, dataend));
                    eventdata.add(input.substring(datastart + 4, dataend));
                }
                // Create an event for this event.
                events.add(new Event(eventdata));

                offset1 = eventend + 5;
            }

            offset = dayend + 5;

        }
    }

    public String stringOutput(){
        String result = "";
        for(EventInstance instance : instances){
            result = result + "[" + instance.getDate() + " "+ instance.getEvent().getName() + "]" + System.getProperty("line.separator");
        }
        return result;
    }

    public void icsOutput(String output)
            throws FileNotFoundException, UnsupportedEncodingException
    {
        PrintWriter writer = new PrintWriter(output,"UTF-8");
        writer.println("BEGIN:VCALENDAR");
        writer.println("VERSION:2.0");
        writer.println("PRODID:TimetableConverter");
        for(EventInstance instance : instances){
            writer.println("BEGIN:VEVENT");
            writer.println("SUMMARY:" + instance.getEvent().getName());
            writer.println("DESCRIPTION:" + instance.getEvent().getDesc());
            writer.println("LOCATION:"+ instance.getEvent().getLoc());
            writer.println("DTSTART:" + instance.getISOStart());
            writer.println("DTEND:" + instance.getISOEnd());
            writer.println("END:VEVENT");
        }
        writer.println("END:VCALENDAR");
        writer.close();
    }
}
