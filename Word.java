public class Word{
    private String word;
    private int len;//一応word.length()で求められるから、いらないかも。。
    private int cur;
    private WordList wordlist;

    final String SPACE = "⌷";/*or ⎕*/
//    final String ENTER = "⏎";/*or ↲*/

    Word(int num){
	wordlist = new WordList(num);
	setNextWord(true);
    }

    void setNextWord(boolean doSort){
//	this.word = wordlist.getRandomWord();
	this.word = wordlist.getNextWord(doSort);
	this.len = word.length();
	this.cur = 0;
    }

    char curChar(){
	return this.word.charAt(cur);
    }

    char curChar(boolean IGNORECASE){
	char curchar = word.charAt(cur);
	if(IGNORECASE){
		if(curchar >= 'A' && curchar <= 'Z'){
			curchar += 0x20;
		}
	}
	return curchar;
    }

    String getWord(){
	return this.word;
    }

    int getCur(){
	return cur;
    }

    int nextCur(){
	cur++;
	if(cur < len){
		return 0;
	}else{
		setNextWord(false);
		return 1;
	}
    }

    String headToCur(boolean contain){
	if(contain){
		return this.word.substring(0, cur+1).replaceAll(" ", SPACE);
	}else{
//		if(cur < 1){
//			return "";
//		}
		return this.word.substring(0, cur).replaceAll(" ", SPACE);
	}
    }

    String curToTail(boolean contain){
	if(contain){
		return this.word.substring(cur).replaceAll(" ", SPACE);
	}else{
		return this.word.substring(cur+1).replaceAll(" ", SPACE);
	}
    }

    String curStr(){
	if(curChar() == ' '){
		return SPACE;
	}else{
		return String.valueOf(curChar());
	}
    }
}
