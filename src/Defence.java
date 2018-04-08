import java.util.Random;

public class Defence implements Agent {

    public int strategy;
    public int reward;
    public String name;
    static final int[][] defence_reward={{5,-1},         //警備されている①を攻撃：5,警備されていない②を攻撃:-1
                                        {-5,2}};         //警備されていない①を攻撃：-５,警備されている②を攻撃：2

    public Defence(int i){
        this.reward=0;
        this.name="守ります"+i;

    }

    @Override
    public void strategy(int attack_strategy, int defence_strategy) {

        Random rand = new Random();
//        this.strategy = rand.nextInt(2);
        if(attack_strategy==0){ this.strategy=0;}
        else{this.strategy = rand.nextInt(2);}

    }

    @Override
    public int action(){

        return strategy;
    }

    @Override
    public void addReward(int reward) {
        this.reward+=reward;
    }

    @Override
    public int score(){
        return this.reward;
    }

    @Override
    public String name(){
        return this.name;
    }

    public void reset_score(){
        this.reward=0;
    }
}
