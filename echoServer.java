import java.net.ServerSocket;
import java.net.Socket;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.OutputStream;
import java.io.DataOutputStream;
import java.io.*;

class echoServer {
    static int a[] = new int[200];
    static int num = 0;

    public static void main(String args[]) {
        try {
            int port = 8888; //サーバ側の待受ポート番号
            ServerSocket ss = new ServerSocket(port);

            while(true) {
                Socket s = ss.accept(); //クライアントからの通信開始要求が来るまで待機

                // 以下、クライアントからの要求発生後
                InputStream is = s.getInputStream(); //クライアントから数値を受信
                DataInputStream dis = new DataInputStream(is);
                int req = dis.readInt();

                score(req);
                num = read();
                bubble_sort(a,num);
                int rank = ranking(req,num);

                OutputStream os = s.getOutputStream(); //二乗した結果を送信
                DataOutputStream dos = new DataOutputStream(os);
                dos.writeInt(rank);

                // ストリームを閉じる
                dos.close();
                dis.close();
            }
        }
        catch(Exception e) {
            System.out.println("Exception: " + e);
        }
    }

    public static void score(int score){
        try{
            File file = new File("score.txt");

            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file,true)));//上書きではなく追加できるように第2引数にtrue

            pw.println(score);

            pw.close();
      
            System.out.println("score :"+score);
      
        }catch(IOException e){
            System.out.println(e);
        }
  }

  public static int read(){
        FileReader fr = null;
        BufferedReader br = null;
        int n = 0;

        try {
            fr = new FileReader("score.txt");
            br = new BufferedReader(fr);
 
            String line;
            while ((line = br.readLine()) != null) {
                //System.out.println(line);
                a[n] = Integer.parseInt(line);
                n++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return n;
    }

    public static void bubble_sort(int a[],int n){
        int i,j,t;

        for (i = 0;i < n-1;i++ ) {
            for (j = n-1;j > 0 ;j-- ) {
                if (a[j-1] < a[j]) {//通常のバブルソートとは逆（昇順）にしている
                    //System.out.println(a[j-1]+"&"+a[j]);
                    t = a[j];
                    a[j] = a[j-1];
                    a[j-1] = t;
                }
                
            }
            
        }

        for (i = 0;i < n ;i++ ) {
            System.out.println(i+":"+a[i]);
        }
    }

    public static int  ranking(int r,int num){
        int i;
        for (i = 1;i < num ;i++ ) {
            if(r == a[i]){
                return i;
            }
        }

        return 0;
    }


}