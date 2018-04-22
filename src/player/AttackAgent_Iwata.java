package player;


import reward.Reward_Task1;

public class AttackAgent_Iwata extends Agent{

    public AttackAgent_Iwata(){

        /**
         * 結果に表示する名前を決めてください．
         **/
        this.name = "yiwata";

        /**
         * ゲーム1回目の行動(初手)をきめてください．
         **/
        this.action = 1;

        this.rewardScheme = new Reward_Task1();




    }

    public void strategy(int defense_action,int reward) {

        this.action= 1;

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

    @Override
    public void afterGame(){}


}
