package player;

import player.Agent;
import reward.RewardScheme;
import reward.Reward_Task2;

import java.util.ArrayList;

abstract public class Team {

    private String name;
    public int[] area = {0,0};  //エリアA:area[0], エリアB:area[1]
    public ArrayList<Agent> agent_list = new ArrayList<Agent>();
    public RewardScheme rewardScheme =  new Reward_Task2();

    public Team(String name){
        this.name = name;
    }

    public void action(){
        for(Agent agent: agent_list){
            if(agent.action()==0){
                area[0]++;
            }else {
                area[1]++;
            }
        }
    }

    abstract public void strategy(int[] opponent_area,int score);

    public int getAREA_A(){
        return area[0];
    }
    public int getAREA_B(){
        return area[1];
    }
    public int[] getArea(){
        return area;
    }
    abstract public int getReward(int area, int opponent);

    public void resetAREA(){
        area[0]=0;
        area[1]=0;
    }

    public String get_name(){
        return name;
    }



}
