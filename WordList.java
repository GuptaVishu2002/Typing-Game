import java.io.*;

public class WordList{
    private String[] wordlist;
    private double[] randNum;
    private int resultindex[];
    private int cur;
    private int maxtableindex;

    WordList(int num){
	String theFile = "";
	wordlist = new String[200];	
	if(num == 0){
		theFile = "input0.txt";
	}else if(num == 1){
		theFile = "input1.txt";
	}else if(num == 2){
		theFile = "input2.txt";
	}else{
		System.err.println("no wordlist("+num+").");
		System.exit(1);
	}

	try{
		String theLine;
		FileInputStream finstream = new FileInputStream(theFile);
		InputStreamReader fisreader = new InputStreamReader(finstream);
		BufferedReader fbfreader = new BufferedReader(fisreader);
		do {
			theLine = fbfreader.readLine();
			if( theLine != null ) {
				wordlist[maxtableindex] = theLine;
				maxtableindex++;
			}
		} while( theLine != null );
	}catch(Exception e){
		System.err.println(e);
		System.exit(1);
	}

	randNum = new double[maxtableindex];
	resultindex = new int[maxtableindex];

	for(int i = 0; i < maxtableindex; i++){
		resultindex[i] = i;
	}
//	cur = -1;
//	mysort();
    }

    String getNextWord(boolean doSort){
	cur++;
	if(doSort || cur >= maxtableindex){
		mysort();
		cur++;
	}
	return wordlist[resultindex[cur]];
    }

    void mysort(){
	cur = -1;
	for(int i = 0; i < maxtableindex; i++){
		randNum[i] = Math.random();
	}

	Qsort.quicksort(maxtableindex, resultindex, randNum);

//	for(int i = 0; i < maxtableindex; i++){
//		System.out.println(randNum[resultindex[i]]+" , "+resultindex[i]+":"+wordlist[resultindex[i]]);
//	}

    }

/*
    String getRandomWord(){
	int i = (int)(Math.random() * 100000) % wordlist.length;
	return wordlist[i];
    }
*/
}

