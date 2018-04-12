import java.util.Random;

public class Attack_sample extends Agent{

//    public int strategy;                                //エリアAを攻める：strategy=0, エリアBを攻める:strategy=1
//    public String name;
    static final int[][] attack_reward={{-3,1},         //警備されているAを攻撃：-3,警備されていないBを攻撃:1
                                          {5,-1}};      //警備されていないAを攻撃：５,警備されているBを攻撃：-1

    public Attack_sample(){
        this.name = "ランダムに攻めるマン";

    }
    @Override
    public void strategy(int attack_strategy, int defense_strategy,int reward) {

        Random rand = new Random();
        this.strategy = rand.nextInt(2);

    }

    @Override
    public int action() { return strategy; }

    @Override
    public String get_name(){
        return this.name;
    }

}
