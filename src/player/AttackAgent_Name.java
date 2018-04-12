package player;


import reward.Reward_Task1;

public class AttackAgent_Name extends Agent{

    public AttackAgent_Name(){

        /**
         * 結果に表示する名前を決めてください．
         **/
        this.name = "";

        /**
         * ゲーム1回目の行動(初手)をきめてください．
         **/
        this.action = 0;

        this.rewardScheme = new Reward_Task1();




    }

    public void strategy(int defense_action,int reward) {

        this.action= 0;

        /**
        * ここに行動戦略を書いてください
        *
        * defense_action: 警備側が前回守ったエリア, reward: 前回自分が得たreward
        * actionフィールドに次のステップで攻めるエリアを代入してください
        *
        * */
    }

    @Override
    public int action() { return action; }


    @Override
    public int getReward(int opponent_action) {
        return rewardScheme.getAttackReward(opponent_action,action);
    }
    @Override
    public String get_name(){
        return this.name;
    }


}
