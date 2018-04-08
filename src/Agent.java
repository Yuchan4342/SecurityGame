public interface Agent {

    void strategy(int attack_strategy, int defence_strategy);
    int action();
    void addReward(int reward);
    int score();
    String name();
    void reset_score();

}
