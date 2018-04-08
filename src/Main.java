import java.util.ArrayList;

public class Main {


    static final int[][] attack_reward={{-3,1},         //[0][0],[0][1]
                                    {5,-1}};      //[1]0],[1][1]
    static final int[][] defence_reward={{5,-1},         //[0][0],[0][1]
                                    {-5,2}};      //[1]0],[1][1]

    static final int STEP = 5;        //繰り返しステップ数
    static final int TRIAL = 5;       //繰り返しゲーム数

    static int ATTACK_WIN;
    static int DEFENCE_WIN;


    public static void game(Agent defence, Agent attack){

        defence.addReward(defence_reward[defence.action()][attack.action()]);
        attack.addReward(attack_reward[defence.action()][attack.action()]);

//        System.out.println("["+defence.action()+"]["+attack.action()+"]");
        System.out.println("警備側"+defence.score()+":攻撃側"+attack.score());
    }




    public static void main(String[] args){

        ArrayList<Agent> attacker_list = new ArrayList<Agent>();
        ArrayList<Agent> defence_list = new ArrayList<Agent>();

        //プレイヤー追加
        for (int i=0;i<2;i++) {
            attacker_list.add(new Attack(i));
            defence_list.add(new Defence(i));
        }



        //プレイヤー選択
        for (Agent attack: attacker_list) {
            for (Agent defence : defence_list) {

                ATTACK_WIN = 0;
                DEFENCE_WIN = 0;

                while (ATTACK_WIN + DEFENCE_WIN != TRIAL) {
                    for (int i = 0; i < STEP; i++) {

                        //ゲーム
                        game(defence, attack);

                        //戦略決定
                        attack.strategy(attack.action(), defence.action());
                        defence.strategy(attack.action(), defence.action());


                    }

                    if (attack.score() > defence.score()) {
//                    System.out.println("【勝利】"+attack.name());
                        ATTACK_WIN++;
                    } else if (defence.score() > attack.score()) {
//                    System.out.println("【勝利】"+defence.name());
                        DEFENCE_WIN++;
                    } else {
//                        System.out.println("引き分け");
                    }

                    attack.reset_score();
                    defence.reset_score();


                }

                System.out.println("[" + attack.name() + "] VS [" + defence.name() + "]: " + ATTACK_WIN + ":" + DEFENCE_WIN);


                ResultLogger.csvprinter(attack.name(), defence.name(), ATTACK_WIN, DEFENCE_WIN);
            }
        }


    }
}
