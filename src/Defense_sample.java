import java.util.Random;

public class Defense_sample extends Agent {

//    public int strategy;
//    public String name;
    static final int[][] defense_reward = {{5, -1},         //警備されている①を攻撃：5,警備されていない②を攻撃:-1
                                             {-5, 3}};         //警備されていない①を攻撃：-５,警備されている②を攻撃：3

    public double i;

    public Defense_sample(double i) {
        this.name = "エリアAを"+i+"%守るマン";
        this.i = i;
    }

    @Override
    public void strategy(int attack_strategy, int defense_strategy,int reward) {
        Random rand = new Random();
        if (rand.nextDouble() <= i) {
            this.strategy = 0;
        } else {
            this.strategy = 1;
        }
    }

    @Override
    public int action() {
        return strategy;
    }

    @Override
    public String get_name() {
        return this.name;
    }
}

