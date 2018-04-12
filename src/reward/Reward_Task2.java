package reward;

public class Reward_Task2 extends RewardScheme{


    private static final int[][] attack_reward_structure = {{5, -3},         //[0][0]:エリアAで攻撃成功,[0][1]:エリアAで攻撃失敗
                                                             {1, -1}};      //[1][0]:エリアBで攻撃成功:,[1][1]:エリアBで攻撃失敗

    private static final int[][] defense_reward_structure = {{5, -5},         //[0][0]:エリアAで警備成功,[0][1]:エリアAで警備失敗
                                                             {3, -1}};      //[1]0]:エリアBで警備成功:,[1][1]:エリアBで警備失敗



    public int getAttackReward(int area, int result){
        return attack_reward_structure[area][result];

    }
    public int getDefenseReward(int area, int result){
        return defense_reward_structure[area][result];

    }

}
