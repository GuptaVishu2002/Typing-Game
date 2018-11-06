class Qsort{
    private static int[] resultindex;
    private static double[] randNum;

    static void quicksort(int maxtableindex, int[] resultindex_p, double[] randNum_p){
	int l, r, v;
	int[] low = new int[100];
	int[] high = new int[100];
	int sp;
	resultindex = resultindex_p;
	randNum = randNum_p;

	low[0] = 0;
	high[0] = maxtableindex - 1;
	sp = 1;

	while(sp > 0){
		sp--;
		l = low[sp];
		r = high[sp];

		if(l < r){
			v = partition(l, r);
			if(v - l < r - v){
				low[sp] = v + 1;
				high[sp++] = r;
				low[sp] = l;
				high[sp++] = v - 1;
			}else{
				low[sp] = l;
				high[sp++] = v - 1;
				low[sp] = v + 1;
				high[sp++] = r;
			}
		}
	}
    }

    static int partition(int l, int r){
	int i, j, pivot, c;

	i = l-1;
	j = r;
	c = (l+r)/2;

	if(mycompare(l, c) > 0){
		if(mycompare(c, r) > 0){myswap(c, r);}
		else if(mycompare(l, r) < 0){myswap(l, r);}
		else {}
	}
	else if(mycompare(l, r) > 0){myswap(l, r);}
	else if(mycompare(c, r) > 0){}
	else {myswap(c, r);}

	pivot = r;

	for(;;){
		while(mycompare(++i, pivot) < 0);
		while(i < --j && mycompare(pivot, j) < 0);

		if(i >= j){break;}

		myswap(i, j);
  	}
	myswap(i, pivot);
	return i;
    }


    static int mycompare(int i, int j){
	if((randNum[resultindex[i]] - randNum[resultindex[j]]) < 0){
		return -1;
	}else{
		return 1;
	}
    }
    static void myswap(int i, int j){
	int tmp;
	tmp = resultindex[i];
	resultindex[i] = resultindex[j];
	resultindex[j] = tmp;
    }


}
