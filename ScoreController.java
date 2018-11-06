import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.*;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.net.Socket;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.OutputStream;
import java.io.DataOutputStream;

public class ScoreController {
    public Label textlabel1;
    public Label textlabel2;
    public Label textlabel3;
    public Label textlabel4;
    public Label textlabel5;

    static int a[] = new int[200];
    static int resnum = 0;
    int num = 0;
    int sendnum = 0;


    public void handleButtonAction(ActionEvent event) {
        sendnum = TypingGameController.game_score;
        send(sendnum);
        textlabel1.setText("先ほどのスコアは"+String.valueOf(sendnum)+"点です.");
        textlabel2.setText("順位は"+String.valueOf(resnum)+"位です.");
        /*textlabel2.setText("2nd:"+String.valueOf(a[1]));
        textlabel3.setText("3rd:"+String.valueOf(a[2]));
        textlabel4.setText("4th:"+String.valueOf(a[3]));
        textlabel5.setText("5th:"+String.valueOf(a[4]));*/
        System.out.println("high score");
    }

    public void returnButtonAction(ActionEvent event) {
        System.out.println("RESTART");
        try{//こうしないと出来ない。。
          TypingGame.getInstance().setRestart();
        }catch(Exception e){
          System.err.print(e);
        }
    }

    public static void send(int score) {
        try {
            String server = "localhost";
            int port = 8888; //サーバー側のポート番号
            Socket s = new Socket(server, port);

            // サーバーに数値を送信
            OutputStream os = s.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);
            dos.writeInt(score);

            // 演算結果を受信
            InputStream is = s.getInputStream();
            DataInputStream dis = new DataInputStream(is);
            int res = dis.readInt();
            resnum = res;
            System.out.println(res);

            // ストリームを閉じる
            dis.close();
            dos.close();
        }
        catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }

    
}