
public class Attack_Team extends Agent{



    public Attack_Team(){

        this.name = "";

        /**
        * 結果に表示する名前を決めてください．
        * */


    }
    @Override
    public void strategy() {

        this.strategy= 0;

        /**
        * ここに行動戦略を書いてください
        *
        * attack_strategy: 攻撃側が前回攻めたエリア， defense_strategy: 警備側が前回守ったエリア, reward: 前回自分が得たreward
        * strategyフィールドに次のステップで攻めるエリアを代入してください
        *
        * */
    }

    @Override
    public int action() { return strategy; }

    @Override
    public String get_name(){
        return this.name;
    }

}
