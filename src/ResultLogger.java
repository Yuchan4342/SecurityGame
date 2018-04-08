
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class ResultLogger {

    public static void csvprinter(String attack_name, String defence_name,int attack_win,int defence_win){
        String currentTime;
        Calendar cTime = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
        currentTime = sdf.format(cTime.getTime());



        try {
            FileWriter fw = new FileWriter("["+currentTime+"]result.csv",true);
            fw.write(attack_name+","+defence_name+","+attack_win+","+defence_win+"\n");
            fw.close();
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

}
