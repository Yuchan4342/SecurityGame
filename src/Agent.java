abstract public class Agent {

        public int strategy;                                //エリアAを攻める：strategy=0, エリアBを攻める:strategy=1
        public String name;

        public void strategy(int attack_strategy, int defense_strategy,int reward) {

            this.strategy= 0;

            /**
             * ここに行動戦略を書いてください
             *
             * attack_strategy: 攻撃側が前回攻めたエリア， defense_strategy: 警備側が前回守ったエリア, reward: 前回自分が得たreward
             * strategyフィールドに次のステップで攻めるエリアを代入してください
             *
             * */
        }

        public void strategy(){

        }

        public int action() { return strategy; }

        public String get_name(){
            return this.name;
        }


    }
