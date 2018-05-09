package player;

import player.sample.AttackTeamAgent_Miura;

import java.util.Collections;

public class AttackTeam extends Team {

    public AttackTeam(String name){
        super(name);

        Collections.addAll(agent_list,
                new AttackTeamAgent_Iwata(),
                new AttackTeamAgent_Iwata(),
                new AttackTeamAgent_Iwata(),
                new AttackTeamAgent_Iwata(),
                new AttackTeamAgent_Iwata()
                /**
                 * ここにプレイヤーを突っ込めば試せる．
                 */
        );
    }


    public void strategy(int[] defense_area,int score){

        for(Agent agent: agent_list){
            agent.strategy(defense_area[agent.action],score);   //引数1:agentが攻撃したエリアにいた警備人数，引数2:チームが得た利得
        }

    }

    @Override
    public int getReward(int area, int defense) {

        int rate=1;

        //攻撃側が攻撃人数0人なら
        if(this.area[area]==0) {
            return 0;
        }
        //攻撃側が警備側を上回ったら
        else if(this.area[area]>defense){
            if(area==1){ rate = this.area[area]-defense;}   //エリアBを攻撃成功した場合，人数差*利得
            return rate * rewardScheme.getAttackReward(area,0);
        }
        //警備側が攻撃人数以上だったら
        else{
            return rewardScheme.getAttackReward(area,1);
        }

    }
}
