package player;

import reward.RewardScheme;

abstract public class Agent {

        public int action;                                //エリアAを攻める：action=0, エリアBを攻める:action=1
        public String name;
        public RewardScheme rewardScheme;

        abstract public void strategy(int opponents_action,int reward);

        abstract public int getReward(int opponent_action);

        public int action() { return action; }
        public String get_name(){ return this.name; }
        public void afterGame(){}


}
