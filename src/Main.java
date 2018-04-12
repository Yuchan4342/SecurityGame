import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {


    static final int[][] attack_reward_structure = {{-3, 1},         //[0][0],[0][1]
            {5, -1}};      //[1]0],[1][1]
    static final int[][] defense_reward_structure = {{5, -1},         //[0][0],[0][1]
            {-5, 3}};      //[1]0],[1][1]


    static final int TRIAL = 500;       //繰り返しゲーム数

    static int ATTACK_SCORE = 0;
    static int DEFENSE_SCORE = 0;

    static int[] AREA_A = new int[2];
    static int[] AREA_B = new int[2];


    //課題3-1 個人戦
    public static void game_Indivisiual(Agent defense, Agent attack) {

        //お互いの行動をもとにrewardを決定
        int attack_reward = defense_reward_structure[defense.action()][attack.action()];
        int defense_reward = attack_reward_structure[defense.action()][attack.action()];

        //rewardを各プレイヤーのスコアに追加
        ATTACK_SCORE += attack_reward;
        DEFENSE_SCORE += defense_reward;

        //各プレイヤーの次の戦略を決定する
        attack.strategy(attack.action(), defense.action(), attack_reward);
        defense.strategy(attack.action(), defense.action(), defense_reward);

    }


    public static void Task1_start(ResultLogger result_csv) {

        Team team_Attack = new Team("Attack");
        Team team_Defense = new Team("Defense");


        //プレイヤー追加
        Collections.addAll(team_Attack.agent_list,
                new Attack_sample()

                /**
                 * ここに自分のオブジェクトを突っ込めば試せる．
                 */
        );
        Collections.addAll(team_Defense.agent_list,
                new Defense_sample(0.1),
                new Defense_sample(0.3),
                new Defense_sample(0.5),
                new Defense_sample(0.7),
                new Defense_sample(0.9)
                /**
                 * ここに自分のオブジェクトを突っ込めば試せる．
                 */
        );



        //プレイヤー選択(攻撃側と警備側の総当たり)
        for (Agent attack : team_Attack.agent_list) {
            for (Agent defense : team_Defense.agent_list) {

                //勝利回数の合計がTRIALになるまでゲームを行う．
                for (int i = 0; i < TRIAL; i++) {

                    //STEP回数繰り返しゲームを行う．
                    game_Indivisiual(defense, attack);

                }

                //ゲーム結果をcsvファイルに出力する
                result_csv.csvprinter(attack.get_name(), defense.get_name(), ATTACK_SCORE, DEFENSE_SCORE);

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




    //課題3-2 チーム戦

    public static void game_Team(Agent attack, Agent defense){



    }

    public static void Task2_start(ResultLogger result_csv){

        Team team_Attack = new Team();
        Team team_Defense = new Team();


        //プレイヤー追加
        Collections.addAll(team_Attack.agent_list,
                new Attack_sample()

                /**
                 * ここに自分のオブジェクトを突っ込めば試せる．
                 */
        );
        Collections.addAll(team_Defense.agent_list,
                new Defense_sample(0.1),
                new Defense_sample(0.3),
                new Defense_sample(0.5),
                new Defense_sample(0.7),
                new Defense_sample(0.9)
                /**
                 * ここに自分のオブジェクトを突っ込めば試せる．
                 */
        );


        for(int i = 0; i < TRIAL; i++) {

            //警備(攻撃)エリアの決定
            for (Agent attack : team_Attack.agent_list) {
                if (attack.action() == 0) {
                    AREA_A[0]++;
                } else {
                    AREA_B[0]++;
                }
            }
            for (Agent defense : team_Defense.agent_list) {

                if (defense.action() == 0) {
                    AREA_A[1]++;
                } else {
                    AREA_B[1]++;
                }
            }


            //警備(攻撃)成功失敗の評価
            if (AREA_A[0] >= AREA_A[1]) {
                //A:警備側の勝利
                team_Attack.add_Score_A(5);
                if (AREA_A[1] == 0) {
                    team_Defense.add_Score_A(0);
                } else {
                    team_Defense.add_Score_A(-3);
                }

            } else {
                //A:攻撃側の勝利
                team_Attack.add_Score_A(-5);
                team_Defense.add_Score_A(5);
            }

            if (AREA_B[0] >= AREA_B[1]) {
                //B:警備側の勝利
                team_Attack.add_Score_B(3);
                if (AREA_B[1] == 0) {
                    team_Defense.add_Score_B(0);
                } else {
                    team_Defense.add_Score_B(-1);
                }

            } else {
                //B:攻撃側の勝利
                team_Attack.add_Score_B(-1 * (AREA_B[1] - AREA_B[0]));
                team_Defense.add_Score_B(1 * (AREA_B[1] - AREA_B[0]));
            }

            team_Attack.strategy(AREA_A, AREA_B);
            team_Defense.strategy(AREA_A, AREA_B);

            ATTACK_SCORE += team_Attack.getScore();
            DEFENSE_SCORE += team_Defense.getScore();

            Arrays.fill(AREA_A, 0);
            Arrays.fill(AREA_B, 0);

        }


        //ゲーム結果をcsvファイルに出力する
        result_csv.csvprinter(team_Attack.get_name(), team_Defense.get_name(), ATTACK_SCORE,DEFENSE_SCORE);

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


    public static void main(String[] args) {




        //課題3-1
        Task1_start(new ResultLogger());

        //課題3-2
//        Task2_start(new ResultLogger());
    }

}

