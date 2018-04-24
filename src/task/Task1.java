package task;

import player.*;
import player.sample.*;
import utility.*;


import java.util.ArrayList;
import java.util.Collections;


public class Task1 {

    private static int TRIAL;       //繰り返しゲーム数

    static int ATTACK_SCORE = 0;
    static int DEFENSE_SCORE = 0;




    static private ResultLogger result_csv = new ResultLogger("Task1");



    //課題3-1 個人戦
    public static void game(Agent defense, Agent attack) {

        int attack_action = attack.action();
        int defense_action = defense.action();


        //お互いの行動をもとにrewardを決定
        int attack_reward = attack.getReward(defense_action);
        int defense_reward = defense.getReward(attack_action);


        //rewardを各プレイヤーのスコアに追加
        ATTACK_SCORE += attack_reward;
        DEFENSE_SCORE += defense_reward;

        String str = String.format("攻撃側:%d, 警備側:%d,[%d:%d] score(%d:%d)",attack_action,defense_action,attack_reward,defense_reward,
                ATTACK_SCORE,DEFENSE_SCORE);
//        System.out.println(str);

        //各プレイヤーの次の戦略を決定する
        attack.strategy(defense_action, attack_reward);
        defense.strategy(attack_action, defense_reward);



    }


    public static void start(int trial) {

        TRIAL = trial;

        //チーム作成
        ArrayList<Agent> team_Attack = new ArrayList<>();
        ArrayList<Agent> team_Defense = new ArrayList<>();


        //プレイヤー追加
        Collections.addAll(team_Attack
//                ,new AttackAgent_Miura()
                ,new AttackAgent_Yamauchi()

                /**
                 * ここに自分のオブジェクトを突っ込めば試せる．
                 */
        );
        Collections.addAll(team_Defense
                ,new DefenseAgent_Miura()
//                ,new DefenseAgent_Miura()
                /**
                 * ここに自分のオブジェクトを突っ込めば試せる．
                 */
        );


        //プレイヤー選択(攻撃側と警備側の総当たり)
        for (Agent attack : team_Attack) {
            for (Agent defense : team_Defense) {

                //TRIAL回数ゲームを行う．
                for (int i = 0; i < TRIAL; i++) {

                    game(defense, attack);

                }

                //対戦終了後，後処理を行う
                defense.afterGame();
                attack.afterGame();

                //ゲーム結果をcsvファイルに出力する
//                result_csv.csvprinter(attack.get_name(), defense.get_name(), ATTACK_SCORE, DEFENSE_SCORE);

                if (ATTACK_SCORE > DEFENSE_SCORE) {
                    System.out.println("攻撃側の勝ち");
                } else if (ATTACK_SCORE < DEFENSE_SCORE) {
                    System.out.println("警備側の勝ち");
                } else {
                    System.out.println("引き分け");
                }

                //スコアのリセット
                ATTACK_SCORE = 0;
                DEFENSE_SCORE = 0;
            }

        }
    }

}
