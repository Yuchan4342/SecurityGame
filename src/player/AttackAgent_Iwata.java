package player;


import reward.Reward_Task1;

public class AttackAgent_Iwata extends Agent {
    private int n; // 終わった対戦の回数
    private int[] def_n = new int[2]; // 警備側がA,Bそれぞれを警備した回数
    private int[] recently_def = new int[50]; // 警備側の最近50回の警備エリアの履歴
    private int[][] rewards = new int[2][2]; // 攻守の利得差行列(総利得の大小で勝敗が決まるので実質的にこの数字が重要)

    public AttackAgent_Iwata() {

        /**
         * 結果に表示する名前を決めてください．
         **/
        this.name = "yiwata";

        /**
         * ゲーム1回目の行動(初手)をきめてください．
         **/
        this.n = 0;
        def_n[0] = 0;
        def_n[1] = 0;
        this.rewardScheme = new Reward_Task1();
        rewards[0][0] = this.rewardScheme.getAttackReward(0, 0) - this.rewardScheme.getDefenseReward(0, 0);
        rewards[0][1] = this.rewardScheme.getAttackReward(0, 1) - this.rewardScheme.getDefenseReward(0, 1);
        rewards[1][0] = this.rewardScheme.getAttackReward(1, 0) - this.rewardScheme.getDefenseReward(1, 0);
        rewards[1][1] = this.rewardScheme.getAttackReward(1, 1) - this.rewardScheme.getDefenseReward(1, 1);
        /*
         * 警備側が50:50の確率でA,Bを選択するとしたときの,A,Bそれぞれの利得差rewardsの期待値を算出し,
         大きい方のエリアを攻撃する。
        */
        this.action = (double)(rewards[0][0] + rewards[1][0]) * 0.5 >= (double)(rewards[0][1] + rewards[1][1]) * 0.5 ? 0 : 1;
    }

    public void strategy(int defense_action, int reward) {
        /**
        * ここに行動戦略を書いてください
        *
        * defense_action: 警備側が前回守ったエリア, reward: 前回自分が得たreward
        * actionフィールドに次のステップで攻めるエリアを代入してください
        *
        * */
        this.recently_def[this.n % 50] = defense_action;
        this.n++;
        this.def_n[defense_action]++;
        // 51回目以降の対戦では, 直近50回の警備側のA,Bの回数から確率を出し, その確率による期待値で攻撃エリアを決める。
        if (this.n >= 50) {
            int count = 0;
            for (int i = 0; i < 50; i++) {
                if (this.recently_def[i] == 0) count++;
            }
            double p = count / 50; // 直近50回でAを警備した回数 / 50
            this.action = (double)(rewards[0][0] * p + rewards[1][0] * (1 - p)) >= (double)(rewards[0][1] * p + rewards[1][1] * (1 - p)) ? 0 : 1;
        }
        // 6回目以降50回目以内の対戦では, それまでの警備側のA,Bの回数から確率を出し, その確率による期待値で攻撃エリアを決める。
        else if (this.n >= 5) {
            double p = this.def_n[0] / this.n; // Aを警備した回数 / 総対戦回数
            this.action = (double)(rewards[0][0] * p + rewards[1][0] * (1 - p)) >= (double)(rewards[0][1] * p + rewards[1][1] * (1 - p)) ? 0 : 1;
        }
    }

    @Override
    public int action() { return action; }


    @Override
    public int getReward(int opponent_action) {
        return rewardScheme.getAttackReward(opponent_action, action);
    }
    @Override
    public String get_name() {
        return this.name;
    }

    @Override
    public void afterGame() {}


}
