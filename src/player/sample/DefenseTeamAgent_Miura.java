package player.sample;

import player.Agent;

import java.util.Random;

public class DefenseTeamAgent_Miura extends Agent{

    public DefenseTeamAgent_Miura(){

        /**
         * 結果に表示する名前を決めてください．
         **/
        this.name = "";

        /**
         * ゲーム1回目の行動(初手)をきめてください．
         **/
        this.action = 0;

    }
    @Override
    public void strategy(int attack_action,int reward) {

        //集中名[ランダム]
        Random rand = new Random();
        this.action=rand.nextInt(2);

        /**
         * ここに行動戦略を書いてください
         *
         * attack_action: 攻撃側が前回攻めたエリア, reward: 前回自分が得たreward
         * strategyフィールドに次のステップで攻めるエリアを代入してください
         *
         * */
    }

    @Override
    public int action() { return action; }


    @Override
    public int getReward(int opponent_action) {
        return -1;
    }
    @Override
    public String get_name(){
        return this.name;
    }


}
