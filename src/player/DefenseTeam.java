package player;

import player.sample.DefenseTeamAgent_Miura;

import java.util.Collections;

public class DefenseTeam extends Team {

    public DefenseTeam(String name){
        super(name);
        Collections.addAll(agent_list,
                new DefenseTeamAgent_Miura(),
                new DefenseTeamAgent_Miura(),
                new DefenseTeamAgent_Miura(),
                new DefenseTeamAgent_Miura(),
                new DefenseTeamAgent_Miura()
                /**
                 * ここにプレイヤーを突っ込めば試せる．
                 */
        );


    }

    public void strategy(int[] attack_area,int score){
        for(Agent agent: agent_list){
            agent.strategy(attack_area[agent.action],score);    //引数1:agentが警備したエリアにいた攻撃人数，引数2:チームが得た利得
        }

    }

    @Override
    public int getReward(int area, int attack) {

        int rate=1;

        //攻撃側が警備側を上回ったら
        if(this.area[area]<attack){
            if(area==1){ rate = attack-this.area[area];}    //エリアBを警備失敗した場合，人数差*利得
            return rate * rewardScheme.getDefenseReward(area,1);
        }
        //警備側が攻撃人数以上だったら
        else{
            return rewardScheme.getDefenseReward(area,0);
        }

    }
}
