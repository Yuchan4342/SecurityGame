import java.util.Random;

public class Defence_sample implements Agent {

    public int strategy;
    public String name;
    static final int[][] defence_reward = {{5, -1},         //警備されている①を攻撃：5,警備されていない②を攻撃:-1
            {-5, 3}};         //警備されていない①を攻撃：-５,警備されている②を攻撃：3

    public Defence_sample() {
        this.name = "エリアAを60%守るマン";
    }

    @Override
    public void strategy(int attack_strategy, int defence_strategy,int reward) {
        Random rand = new Random();
        if (rand.nextDouble() <= 0.6) {
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

