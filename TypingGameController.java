import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
/*
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
*/
import javafx.scene.layout.GridPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.util.Duration;

import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;



import java.applet.Applet; 
import java.applet.AudioClip;

/*
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.IOException;
*/

class Music extends Applet{
        AudioClip music;
        public Music(String name,int action){
			music=newAudioClip(getClass().getResource(name));
        	operate(action);
        }

    public void operate(int action) {
        if( action == 0 ) {
            music.loop();
        } else if( action == 1 ) {
            music.play();
        } else if( action == 2 ) {
            music.stop();
        }
    }
}




public class TypingGameController implements Initializable {

    final int GAMESTART = 1;
    final int GAMERESULT = 2;
    final int GAMESTOP = 3;
    final char OTHERCHAR = 0;
    final int TIMELIMIT = 120;
    final int MINUSTIME = 10;
    Timeline timer;
    int timecount;
    int gameFlg;
    boolean IGNORECASE;//true:(A==a), false:(A!=a)
    boolean shiftPressed;

    int truecount;
    int misscount;

    static int game_score = 0;

    public Label timerLabel;
    public Label wordLabel;

    Text headToCurText;
    Text curStrText;
    Text curToTailText;

    public Label messageLabel;
    public Label resultLabel;

    public TextFlow inputText;

    Word word;
    int timelimit;
    int level;

    public Music BGM;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
	int level = TypingGame.getSetting().getLevel();
	initInputText();
	word = new Word(level);
	IGNORECASE = TypingGame.getSetting().getIgnorecase();
	timelimit = TypingGame.getSetting().getTimelimit(level);
	System.out.println("IGNORECASE: "+IGNORECASE);//for debug
	System.out.println("Sound on: "+TypingGame.getSetting().getSound());//for debug
	System.out.println("level: "+level+", timelimit: "+timelimit);//for debug
	initTimer();
	shiftPressed = false;
	truecount = 0;
	misscount = 0;
	gameFlg = GAMESTOP;
	//printInputWord1();//for debug
	//System.out.println(word.getWord().length());//for debug2
	setAllText();
    }

    public void initInputText(){
	final int fontsize = 25;
	headToCurText = new Text();
	curStrText = new Text();
	curToTailText = new Text();
	headToCurText.setFont(Font.font(fontsize));
	curStrText.setFill(Color.RED);
	curStrText.setFont(Font.font(fontsize));
	curToTailText.setFont(Font.font(fontsize));
	inputText.setMaxHeight(fontsize + 20);
	inputText.getChildren().addAll(headToCurText, curStrText, curToTailText);
    }

    public void initTimer(){
	timecount = timelimit;
	timerLabel.setText(String.format("残り %3d秒", timecount));
	timer = new Timeline(new KeyFrame(Duration.millis(500), new EventHandler<ActionEvent>(){
		@Override
		public void handle(ActionEvent event) {
			timecountAdd(-1);
		}
	}));

	timer.setCycleCount(timelimit*2);
	timer.setAutoReverse(true);
    }

    public void keyAction(KeyEvent event){
	KeyCode key = event.getCode();
	//System.out.println(event.getCode());//キーコード確認用
	if(key == KeyCode.ESCAPE){
		timer.stop();
		try{
			TypingGame.getInstance().setRestart();
		}catch(Exception e){
			throw new RuntimeException(e);//これでエラーが色々見える模様。。
		}
	}else if(gameFlg == GAMESTART){
		if(key == KeyCode.SHIFT){
			shiftPressed = true;
		}else{
			judgeInputKey(key);
		}
	}else{
		if(gameFlg == GAMESTOP && key == KeyCode.SPACE){
			gameStart();
		}
		if(gameFlg == GAMERESULT && key == KeyCode.ENTER){
			nextGameSetting();
		}
	}
    }

    public void keyReleasedAction(KeyEvent event){
	KeyCode key = event.getCode();
	if(key == KeyCode.SHIFT){
		shiftPressed = false;
	}
    }

    public void judgeInputKey(KeyCode key){
	char inputChar = toChar(key);
	//printInputWord2(inputChar);//for debug
	if(IGNORECASE){
		if(inputChar >= 'A' && inputChar <= 'Z'){
			inputChar += 0x20;
		}
	}
	if(inputChar == word.curChar(IGNORECASE)){
		truecount++;
		//System.out.print("OK!\n");//for debug
		int r = word.nextCur();//全ての文字がOKであれば、この段階で次の単語に移ってます。
		if(r == 1){//次の単語に移った場合
			//printInputWord1();//for debug2
			//System.out.println(word.getWord().length());//for debug2
			//inputLabel.setText("");
		}

	}else{
		misscount++;
		timecountAdd(-MINUSTIME);
		//System.out.print("NG!\n");//for debug
	}
	setAllText();
	//printInputWord1();//for debug
    }

    public char toChar(KeyCode key){//コードごとに shiftが押されているかどうかを聞く形式に変えるかも。
	char ch;
	if(shiftPressed){
		if(key == KeyCode.A){
			ch = 'A';
		}else if (key == KeyCode.B){
			ch = 'B';
		}else if (key == KeyCode.C){
			ch = 'C';
		}else if (key == KeyCode.D){
			ch = 'D';
		}else if (key == KeyCode.E){
			ch = 'E';
		}else if (key == KeyCode.F){
			ch = 'F';
		}else if (key == KeyCode.G){
			ch = 'G';
		}else if (key == KeyCode.H){
			ch = 'H';
		}else if (key == KeyCode.I){
			ch = 'I';
		}else if (key == KeyCode.J){
			ch = 'J';
		}else if (key == KeyCode.K){
			ch = 'K';
		}else if (key == KeyCode.L){
			ch = 'L';
		}else if (key == KeyCode.M){
			ch = 'M';
		}else if (key == KeyCode.N){
			ch = 'N';
		}else if (key == KeyCode.O){
			ch = 'O';
		}else if (key == KeyCode.P){
			ch = 'P';
		}else if (key == KeyCode.Q){
			ch = 'Q';
		}else if (key == KeyCode.R){
			ch = 'R';
		}else if (key == KeyCode.S){
			ch = 'S';
		}else if (key == KeyCode.T){
			ch = 'T';
		}else if (key == KeyCode.U){
			ch = 'U';
		}else if (key == KeyCode.V){
			ch = 'V';
		}else if (key == KeyCode.W){
			ch = 'W';
		}else if (key == KeyCode.X){
			ch = 'X';
		}else if (key == KeyCode.Y){
			ch = 'Y';
		}else if (key == KeyCode.Z){
			ch = 'Z';
		}else if (key == KeyCode.SPACE){
			ch = ' ';
		}else if (key == KeyCode.ENTER){//ここ いらないなら削除しておk
			ch = '\n';
		}else if (key == KeyCode.PERIOD){
			ch = '>';
		}else if (key == KeyCode.COMMA){
			ch = '<';
		}else if (key == KeyCode.MINUS){
			ch = '=';
		}else if (key == KeyCode.DIGIT1){
			ch = '!';
		}else if (key == KeyCode.DIGIT2){
			ch = '"';
		}else if (key == KeyCode.DIGIT3){
			ch = '#';
		}else if (key == KeyCode.DIGIT4){
			ch = '$';
		}else if (key == KeyCode.DIGIT5){
			ch = '%';
		}else if (key == KeyCode.DIGIT6){
			ch = '&';
		}else if (key == KeyCode.DIGIT7){
			ch = '\'';
		}else if (key == KeyCode.DIGIT8){
			ch = '(';
		}else if (key == KeyCode.DIGIT9){
			ch = ')';
		}else if (key == KeyCode.DIGIT0){
			ch = '~';//Windows: no chara, Linux: '~' ??
		}else if (key == KeyCode.SLASH){
			ch = '?';
		}else if (key == KeyCode.COLON){
			ch = '*';
		}else if (key == KeyCode.SEMICOLON){
			ch = '+';
		}else{
			ch = OTHERCHAR;
		}
	}else{
		if(key == KeyCode.A){
			ch = 'a';
		}else if (key == KeyCode.B){
			ch = 'b';
		}else if (key == KeyCode.C){
			ch = 'c';
		}else if (key == KeyCode.D){
			ch = 'd';
		}else if (key == KeyCode.E){
			ch = 'e';
		}else if (key == KeyCode.F){
			ch = 'f';
		}else if (key == KeyCode.G){
			ch = 'g';
		}else if (key == KeyCode.H){
			ch = 'h';
		}else if (key == KeyCode.I){
			ch = 'i';
		}else if (key == KeyCode.J){
			ch = 'j';
		}else if (key == KeyCode.K){
			ch = 'k';
		}else if (key == KeyCode.L){
			ch = 'l';
		}else if (key == KeyCode.M){
			ch = 'm';
		}else if (key == KeyCode.N){
			ch = 'n';
		}else if (key == KeyCode.O){
			ch = 'o';
		}else if (key == KeyCode.P){
			ch = 'p';
		}else if (key == KeyCode.Q){
			ch = 'q';
		}else if (key == KeyCode.R){
			ch = 'r';
		}else if (key == KeyCode.S){
			ch = 's';
		}else if (key == KeyCode.T){
			ch = 't';
		}else if (key == KeyCode.U){
			ch = 'u';
		}else if (key == KeyCode.V){
			ch = 'v';
		}else if (key == KeyCode.W){
			ch = 'w';
		}else if (key == KeyCode.X){
			ch = 'x';
		}else if (key == KeyCode.Y){
			ch = 'y';
		}else if (key == KeyCode.Z){
			ch = 'z';
		}else if (key == KeyCode.SPACE){
			ch = ' ';
		}else if (key == KeyCode.ENTER){//ここ いらないなら削除しておk
			ch = '\n';
		}else if (key == KeyCode.PERIOD){
			ch = '.';
		}else if (key == KeyCode.COMMA){
			ch = ',';
		}else if (key == KeyCode.MINUS){
			ch = '-';
		}else if (key == KeyCode.DIGIT1){
			ch = '1';
		}else if (key == KeyCode.DIGIT2){
			ch = '2';
		}else if (key == KeyCode.DIGIT3){
			ch = '3';
		}else if (key == KeyCode.DIGIT4){
			ch = '4';
		}else if (key == KeyCode.DIGIT5){
			ch = '5';
		}else if (key == KeyCode.DIGIT6){
			ch = '6';
		}else if (key == KeyCode.DIGIT7){
			ch = '7';
		}else if (key == KeyCode.DIGIT8){
			ch = '8';
		}else if (key == KeyCode.DIGIT9){
			ch = '9';
		}else if (key == KeyCode.DIGIT0){
			ch = '0';
		}else if (key == KeyCode.SLASH){
			ch = '/';
		}else if (key == KeyCode.COLON){
			ch = ':';
		}else if (key == KeyCode.SEMICOLON){
			ch = ';';
		}else{
			ch = OTHERCHAR;
		}
	}
	return ch;
    }

    public void gameStart(){
	gameFlg = GAMESTART;
	messageLabel.setVisible(false);
	timer.play();
    }

    public void gameStop(){
	timer.stop();//念のため?追加
	gameFlg = GAMERESULT;
	calcScore();
	messageLabel.setText("Enterキーで、もう一度行います");
	messageLabel.setVisible(true);
    }

    public void nextGameSetting(){
	gameFlg = GAMESTOP;
	messageLabel.setText("Spaceキーで、開始します。");
	resultLabel.setText("");
	shiftPressed = false;
	truecount = 0;
	misscount = 0;
	word.setNextWord(true);//または、word = new 以下略 でもよい?
	initTimer();
	setAllText();
    }

    public void calcScore(){
	//int score;
	double rate;
	if((truecount+misscount) > 0){
		rate = ((double)truecount / (double)(truecount+misscount)) * 100;
	}else{
		rate = 0;
	}
	game_score = truecount - misscount * 2;//スコアの計算方法は、適当に変えてください。
	System.out.println("truecount-misscount*2="+game_score);//for debug
	if(!IGNORECASE){
		game_score += (double)game_score * 0.5;
		System.out.println("score*1.5="+game_score);//for debug
	}
	resultLabel.setText(String.format("正しい入力: %d , ミスタイプ: %d , 正答率: %3.2f , 得点: %d", truecount, misscount, rate, game_score));

	if(game_score < 60){
		BGM = new Music("25.wav",1);//bgm
	}
    }

    public void timecountAdd(int num){
	timecount += num;
	timerLabel.setText(String.format("残り %3d秒", timecount));
	if(timecount <= 0){
		gameStop();
	}
    }

    public void setAllText(){
//	wordLabel.setText(word.getWord());
	headToCurText.setText(word.headToCur(false));
	curStrText.setText(word.curStr());
	curToTailText.setText(word.curToTail(false));
    }

    /*for debug*/
    public void printInputWord1(){
	System.out.println(word.getWord());
	System.out.print(word.headToCur(false));
    }
    public void printInputWord2(char ch){
	if(ch == OTHERCHAR){
		System.out.print("[OTHERCHAR] ... ");
	}else{
		System.out.print(""+ch+" ... ");
	}
    }
}
