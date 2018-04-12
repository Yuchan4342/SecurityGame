package utility;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class ResultLogger {
    String currentTime;
    String taskName;

    public ResultLogger(String taskName){

        this.taskName = taskName;
        Calendar cTime = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
        currentTime = sdf.format(cTime.getTime());
    }

    public  void csvprinter(String attack_name, String defence_name,int attack_win,int defence_win){




        try {
            FileWriter fw = new FileWriter("["+taskName+":"+this.currentTime+"]result.csv",true);
            fw.write(attack_name+","+defence_name+","+attack_win+","+defence_win+"\n");
            fw.close();
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

}
