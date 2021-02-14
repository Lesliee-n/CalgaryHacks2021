package application;

import java.io.*;

public class ScheduleBuilder {

    public static void makeICS(){
        
        String s = null;
        String[] output;

        Event[] newE;

        try {
            
	    // run the Unix "ps -ef" command
            // using the Runtime exec method:
            Process p = Runtime.getRuntime().exec("py src//script.py");
            
            BufferedReader stdInput = new BufferedReader(new 
                 InputStreamReader(p.getInputStream()));

            BufferedReader stdError = new BufferedReader(new 
                 InputStreamReader(p.getErrorStream()));

            // read the output from the command
            // System.out.println("Here is the standard output of the command:\n");
            while ((s = stdInput.readLine()) != null) {
                // System.out.println(s);
                output = s.split("#");
                String[] days = output[6].split(",");
                try{
                    int hour =Integer.parseInt(output[1]);
                    int min = Integer.parseInt(output[2]);
                    int dur = (int) Float.parseFloat(output[3]);
                    Event event = new Event(output[0], days, hour, min, dur, "https://ucalgary.zoom.us/j/");
                    Main.sb.addEvent(event);
                }catch(Exception e){
                    System.out.println(e);
                }
                
                
                
                
                // for(int i=0; i < output.length; i++){
                //     System.out.println(output[i]);
                // }
                
            }
            
            
            // read any errors from the attempted command
            // System.out.println("Here is the standard error of the command (if any):\n");
            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }
            
        
        }
        catch (IOException e) {
            System.out.println("exception happened - here's what I know: ");
            e.printStackTrace();
            System.exit(-1);
        }

    }

}