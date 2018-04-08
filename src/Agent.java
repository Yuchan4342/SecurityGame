public interface Agent {

    void strategy(int attack_strategy, int defence_strategy,int reward);
    int action();
    String get_name();


}
