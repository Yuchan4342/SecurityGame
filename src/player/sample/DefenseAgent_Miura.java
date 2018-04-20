package player.sample;

import player.Agent;
import reward.Reward_Task1;

import java.util.Random;

public class DefenseAgent_Miura extends Agent{

    Random rand = new Random();

    public DefenseAgent_Miura(){

        /**
         * 結果に表示する名前を決めてください．
         **/
        this.name = "Defense三浦";

        /**
         * ゲーム1回目の行動(初手)をきめてください．
         **/
        this.action = 0;

        this.rewardScheme = new Reward_Task1();

    }
    @Override
    public void strategy(int attack_action,int reward) {


        double p = 0.62;
        //戦略名[エリアA死守]
//        double p = 0.55;
//        this.action= 0;
//        Random rand = new Random();
        if(rand.nextDouble()<p){
            this.action=0;
        }else{
            this.action=1;
        }

        /**
         * ここに行動戦略を書いてください
         *
         * attack_action: 攻撃側が前回攻めたエリア, reward: 前回自分が得たreward
         * actionフィールドに次のステップで攻めるエリアを代入してください
         *
         * */
    }

    @Override
    public int action() { return action; }


    @Override
    public int getReward(int opponent_action) {
        return rewardScheme.getDefenseReward(action,opponent_action);
    }
    @Override
    public String get_name(){
        return this.name;
    }
    @Override
    public void afterGame(){}


}
