
public class Defense_Team implements Agent {

    public int strategy;                                //エリアAを攻める：strategy=0, エリアBを攻める:strategy=1
    public String name;
    static final int[][] defense_reward={{5,-1},         //警備されているAを攻撃：5,警備されていないBを攻撃:-1
                                        {-5,3}};         //警備されていないAを攻撃：-５,警備されているBを攻撃：3

    public Defense_Team(){

        this.name="";

        /**
        * 結果に表示する名前を決めてください．
        * */

    }

    @Override
    public void strategy(int attack_strategy, int defense_strategy,int reward) {

        this.strategy =0 ;

        /**
        * ここに行動戦略を書いてください
        *
        * attack_strategy: 攻撃側が前回攻めたエリア， defense_strategy: 警備側が前回守ったエリア, reward: 前回自分が得たreward
        * strategyフィールドに次のステップで守るエリアを代入してください
        *
        * */

    }

    @Override
    public int action(){ return strategy; }

    @Override
    public String get_name(){
        return this.name;
    }

}
