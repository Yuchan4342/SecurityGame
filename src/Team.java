import javax.swing.text.TabableView;
import java.util.ArrayList;

public class Team {

    private int score_A;
    private int score_B;
    private int score;

    private String name;
    public ArrayList<Agent> agent_list = new ArrayList<Agent>();

    public Team(){
        this.name = "";
        this.score_A = 0;
        this.score_B = 0;

    }
    public Team(String name){
        this.name = name;
    }

    public void strategy(int[] AREA_A, int[] ARE_B){

        for(Agent agent: agent_list){
            agent.strategy();


        }

    }

    public void add_Score_A(int i){
        this.score_A+=i;
    }
    public void add_Score_B(int i){
        this.score_B+=i;
    }

    public int getScore(){
        score = score_A+score_B;
        score_A = 0;
        score_B = 0;
        return score;
    }

    public String get_name(){
        return name;
    }



}
