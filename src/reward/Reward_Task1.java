package reward;

public class Reward_Task1 extends RewardScheme{

    private static final int[][] attack_reward_structure = {{-3, 1},         //[0][0],[0][1]
            {5, -1}};      //[1]0],[1][1]
    private static final int[][] defense_reward_structure = {{5, -1},         //[0][0],[0][1]
            {-5, 3}};      //[1]0],[1][1]


    public int getAttackReward(int defense_action, int attack_action){
        return attack_reward_structure[defense_action][attack_action];

    }
    public int getDefenseReward(int defense_action, int attack_action){
        return defense_reward_structure[defense_action][attack_action];

    }

    public void test(){}

}
