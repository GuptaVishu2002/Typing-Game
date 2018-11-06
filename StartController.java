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
/*
import java.applet.Applet; 
import java.applet.AudioClip;
*/
import javafx.scene.control.CheckBox;

public class StartController implements Initializable {
    public GridPane optGrid;
    public CheckBox ignoreCheck;
    public CheckBox soundCheck;
    //public Music BGM;//いらないなら削除

    @Override
    public void initialize(URL url, ResourceBundle rb) {
	ignoreCheck.setSelected(TypingGame.getSetting().getIgnorecase());
	soundCheck.setSelected(TypingGame.getSetting().getSound());
	optGrid.setVisible(false);
	//BGM = new Music("25.wav",1);//いらないなら削除
    }

    public void keyAction(KeyEvent event){//必要ならこの辺に何か追加。ここを削除するなら、fxmlのほうも削除すること。
	KeyCode key=event.getCode();

    }
    
    public void startButtonAction(int mode){
	System.out.println("START");
	TypingGame.getSetting().setLevel(mode);
        try{
		TypingGame.getInstance().setTypingGame();
        }catch(Exception e){
		throw new RuntimeException(e);//これでエラーが色々見える模様。。
          //System.err.print(e);
        }
    }
    public void mode0ButtonAction(ActionEvent event) {
	startButtonAction(0);
    }
    public void mode1ButtonAction(ActionEvent event) {
	startButtonAction(1);
    }
    public void mode2ButtonAction(ActionEvent event) {
	startButtonAction(2);
    }

    public void scoreButtonAction(){
        System.out.println("SCORE");
        try{
          TypingGame.getInstance().setScore();
        }catch(Exception e){
          System.err.print(e);
        }
    }

    public void scoreButtonAction(ActionEvent event) {
	scoreButtonAction();
    }

    public void optionButtonAction(){
	if(optGrid.isVisible()){
		optGrid.setVisible(false);
	}else{
		optGrid.setVisible(true);
	}
    }
    public void optionButtonAction(ActionEvent event) {
	optionButtonAction();
    }
    public void noteButtonAction(ActionEvent event) {
	try{
		Process process = Runtime.getRuntime().exec(new String[]{ "java", "notepad" });
	}catch(Exception e){
		System.err.println(e);
	}
    }
    public void ignoreCheckAction(ActionEvent event) {
	if(ignoreCheck.isSelected()){
		TypingGame.getSetting().setIgnorecase(true);
	}else{
		TypingGame.getSetting().setIgnorecase(false);
	}
    }
    public void soundCheckAction(ActionEvent event) {
	if(soundCheck.isSelected()){
		TypingGame.getSetting().setSound(true);
	}else{
		TypingGame.getSetting().setSound(false);
	}
    }


}
