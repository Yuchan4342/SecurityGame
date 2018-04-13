package task;

import player.*;
import reward.*;
import utility.*;

import java.util.ArrayList;
import java.util.Collections;

public class Task2 {

    static int TRIAL;       //繰り返しゲーム数

    static int ATTACK_SCORE = 0;
    static int DEFENSE_SCORE = 0;


    static private ResultLogger result_csv = new ResultLogger("Task2");



    //課題3-2 チーム戦
    public static void game(Team Defense_team, Team Attack_team) {

        //行動
        Attack_team.action();
        Defense_team.action();

        //各エリアの人数に従いrewardを決定

        int attack_reward_AREA_A = Attack_team.getReward(0,Defense_team.getAREA_A());
        int attack_reward_AREA_B = Attack_team.getReward(1,Defense_team.getAREA_B());
        int defense_reward_AREA_A = Defense_team.getReward(0,Attack_team.getAREA_A());
        int defense_reward_AREA_B = Defense_team.getReward(1,Attack_team.getAREA_B());

        //rewardを各プレイヤーのスコアに追加
        ATTACK_SCORE += (attack_reward_AREA_A+attack_reward_AREA_B);
        DEFENSE_SCORE += (defense_reward_AREA_A+defense_reward_AREA_B);

        String str = String.format("A(%d:%d),B(%d:%d) scoreA(%d:%d),scoreB(%d:%d) 警備側%d: , 攻撃側%d:",
                Defense_team.getAREA_A(),Attack_team.getAREA_A(),Defense_team.getAREA_B(),Attack_team.getAREA_B(),
                defense_reward_AREA_A,attack_reward_AREA_A,defense_reward_AREA_B,attack_reward_AREA_B,
                DEFENSE_SCORE,ATTACK_SCORE);
        System.out.println(str);

        //各チームの次の戦略を決定する
        Attack_team.strategy(Defense_team.getArea(),(attack_reward_AREA_A+attack_reward_AREA_B));
        Defense_team.strategy(Attack_team.getArea(),(defense_reward_AREA_A+defense_reward_AREA_B));

        //各エリアの人数をリセットする
        Attack_team.resetAREA();
        Defense_team.resetAREA();

    }


    public static void start(int trial) {

        TRIAL=trial;

        //チームリスト作成
        ArrayList<Team> Attack_team_list = new ArrayList<>();
        ArrayList<Team> Defense_team_list = new ArrayList<>();

        //チーム作成
        /**
         * ここでチームを作る
         * **/
        Team team_Attack_sample = new AttackTeam("Attack_sample");
        Team team_Defense_sample = new DefenseTeam("Defense_sample");



        //チーム追加
        Collections.addAll(Attack_team_list,
                team_Attack_sample

                /**
                 *ここに作ったチームを追加する
                 * **/
        );
        Collections.addAll(Defense_team_list,
                team_Defense_sample

                /**
                 *ここに作ったチームを追加する
                 * **/
        );


        //チーム選択
        for (Team Attack_team : Attack_team_list) {
            for (Team Defense_team : Defense_team_list) {

                //TRIAL回数ゲームを行う
                for (int i = 0; i < TRIAL; i++) {

//

                    game(Defense_team, Attack_team);


                }

                //ゲーム結果をcsvファイルに出力する
                result_csv.csvprinter(Attack_team.get_name(), Defense_team.get_name(), ATTACK_SCORE, DEFENSE_SCORE);

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
