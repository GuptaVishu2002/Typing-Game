public class Setting{//変数は、ほんの一例です。必要に応じて追加・削除してください。
    private boolean ignorecase;
    private boolean sound;
    private int level;
    private int[] timelimit = {60, 120, 180};

    Setting(){
	ignorecase = true;
	sound = true;
	level = 1;
    }

    void setIgnorecase(boolean ignorecase){
	this.ignorecase = ignorecase;
    }
    void setSound(boolean sound){
	this.sound = sound;
    }
    void setLevel(int level){
	this.level = level;
    }

    boolean getIgnorecase(){
	return ignorecase;
    }
    boolean getSound(){
	return sound;
    }
    int getLevel(){
	return level;
    }
    int getTimelimit(int level){
	return timelimit[level];
    }


}
