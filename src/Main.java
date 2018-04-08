import java.util.ArrayList;
import java.util.Collections;

public class Main {


    static final int[][] attack_reward_structure={{-3,1},         //[0][0],[0][1]
                                    {5,-1}};      //[1]0],[1][1]
    static final int[][] defense_reward_structure={{5,-1},         //[0][0],[0][1]
                                    {-5,3}};      //[1]0],[1][1]

    static final int STEP = 50;        //繰り返しステップ数
    static final int TRIAL = 100;       //繰り返しゲーム数

    static int ATTACK_WIN;
    static int DEFENSE_WIN;

    static int ATTACK_SCORE=0;
    static int DEFENSE_SCORE=0;

    public static void game(Agent defense, Agent attack){

        //お互いの行動をもとにrewardを決定
        int attack_reward = defense_reward_structure[defense.action()][attack.action()];
        int defense_reward = attack_reward_structure[defense.action()][attack.action()];

        //rewardを各プレイヤーのスコアに追加
        ATTACK_SCORE+=attack_reward;
        DEFENSE_SCORE+=defense_reward;

        //各プレイヤーの次の戦略を決定する
        attack.strategy(attack.action(), defense.action(),attack_reward);
        defense.strategy(attack.action(), defense.action(),defense_reward);

    }





    public static void main(String[] args){

        ArrayList<Agent> attacker_list = new ArrayList<Agent>();
        ArrayList<Agent> defense_list = new ArrayList<Agent>();

        ResultLogger result_csv = new ResultLogger();

        //プレイヤー追加
        Collections.addAll(attacker_list,
                new Attack_sample()
                /**
                 * ここに自分のオブジェクトを突っ込めば試せる．
                 */
        );
        Collections.addAll(defense_list,
                new Defense_sample()
                /**
                 * ここに自分のオブジェクトを突っ込めば試せる．
                 */
        );




        //プレイヤー選択(攻撃側と警備側の総当たり)
        for (Agent attack: attacker_list) {
            for (Agent defense : defense_list) {

                //勝利回数のリセット
                ATTACK_WIN = 0;
                DEFENSE_WIN = 0;

                //勝利回数の合計がTRIALになるまでゲームを行う．
                while (ATTACK_WIN + DEFENSE_WIN != TRIAL) {
                    for (int i = 0; i < STEP; i++) {

                        //STEP回数繰り返しゲームを行う．
                        game(defense, attack);

                    }

                    //STEP回数行った後スコアが大きいほうが勝ち
                    if (ATTACK_SCORE > DEFENSE_SCORE) {
                        ATTACK_WIN++;
                    } else if (DEFENSE_SCORE > ATTACK_SCORE) {
                        DEFENSE_WIN++;
                    } else {
                    }

                    //スコアのリセット
                    ATTACK_SCORE=0;
                    DEFENSE_SCORE=0;


                }

                //ゲーム結果をcsvファイルに出力する
                result_csv.csvprinter(attack.get_name(), defense.get_name(), ATTACK_WIN, DEFENSE_WIN);
            }
        }


    }
}
