package reward;

abstract public class RewardScheme {

    abstract public int getAttackReward(int opponent_action, int action);

    public abstract int getDefenseReward(int action, int opponent_action);

}
