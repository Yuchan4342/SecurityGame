package player;

public class AttackTeamAgent_Iwata1 extends Agent{

    public AttackTeamAgent_Iwata1(){

        /**
         * 結果に表示する名前を決めてください．
         **/
        this.name = "yiwata";

        /**
         * ゲーム1回目の行動(初手)をきめてください．
         **/
        this.action = 0;




    }

    public void strategy(int defense_action,int reward) {

        /**
        * ここに行動戦略を書いてください
        *
        * defense_action: 警備側が前回守ったエリア, reward: 前回自分が得たreward
        * actionフィールドに次のステップで攻めるエリアを代入してください
        *
        * */
    	if(this.action == 0) this.action = 1;
    	else if(defense_action >= 2) this.action = 0;
    	else this.action = 1;
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
    @Override
    public void afterGame(){}


}
