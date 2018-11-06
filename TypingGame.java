import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;

public class TypingGame extends Application {
	Stage stage;
	static TypingGame instance;
	static Setting setting;//追加

	@Override
	public void start(Stage primaryStage) throws Exception {
	stage = primaryStage;
	instance = this;
	setting = new Setting();//追加
	primaryStage.setTitle("TYPING GAME");
	Pane myPane_top = (Pane)FXMLLoader.load(getClass().getResource("Start.fxml"));
	Scene myScene = new Scene(myPane_top);
	primaryStage.setScene(myScene);
	primaryStage.show();

	}
	public static void main(String[] args) {
		launch(args);
		System.exit(0);
	}

	public void setTypingGame() throws Exception {
	   Pane myPane_top = (Pane)FXMLLoader.load(getClass().getResource("TypingGame.fxml"));
	   Scene myScene = new Scene(myPane_top);
	   myScene.getRoot().requestFocus();//追加！
	   stage.setScene(myScene);
	   stage.show();
	}

	public void setScore() throws Exception {
	   Pane myPane_top = (Pane)FXMLLoader.load(getClass().getResource("Score.fxml"));
	   Scene myScene = new Scene(myPane_top);

	   stage.setScene(myScene);
	   stage.show();
	}

	public void setRestart() throws Exception {
	   Pane myPane_top = (Pane)FXMLLoader.load(getClass().getResource("Start.fxml"));
	   Scene myScene = new Scene(myPane_top);

	   stage.setScene(myScene);
	   stage.show();
	}

	public void setClear() throws Exception {
	   Pane myPane_top = (Pane)FXMLLoader.load(getClass().getResource("Clear.fxml"));
	   Scene myScene = new Scene(myPane_top);

	   stage.setScene(myScene);
	   stage.show();
	}

	public static TypingGame getInstance(){
	  return instance;
	}
	public static Setting getSetting(){//追加
	  return setting;
	}
}
