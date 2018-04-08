public interface Agent {

    void strategy(int attack_strategy, int defense_strategy,int reward);
    int action();
    String get_name();


}
