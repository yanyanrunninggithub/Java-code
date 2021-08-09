//string permutation: abc-->abc,acb,bcs..
//call from permutation(word,"",res)
public static void permutation(String word, String w, List<String> res){
	if(word.length()==0){
		res.add(w);
		return;
	}
	for(int i = 0; i< word.length();i++){
		String wordnew = word.substring(0,i)+word.substring(i+1);//only remove word[i]
		permutation(wordnew,w+word.charAt(i),res);
	}
}
//letter case permutation: Input: S = "a1b2" Output: ["a1b2", "a1B2", "A1b2", "A1B2"]
public static List<String> letterCasePermutation(String s){
	List<String> res = new ArrayList<String>();
	int cnt = 0;//letters count
	for(int i=0;i<s.length();i++){
		if(s.charAt(i)<='9' && s.charAt(i)>='0')
			continue;
		cnt++;
	}
	//total permutation count is equal to 1<<cnt means (2^cnt)
	for(int i = 0; i<(1<<cnt);i++){
		String ss = "";
		int index = cnt-1;
		for(int j=0;j<s.length();j++){
			if(s.charAt(j)>='0' && s.charAt(j)<='9'){
				ss += s.charAt(j);
			}
			else
			{//check every position is '1' or '0', using number right shift to get that position 
				if((1&(i>>index)) == 1){
					ss += Character.toLowerCase(s.charAt(j));
				}
				else{
					ss += Character.toUpperCase(s.charAt(j));
				}
				index--;
			}
		}
		res.add(ss);
	}
	return res;
}
//all sub string for a string:abc-->a,b,c,ab,bc,ac,abc
 public static List<String> substringPermutation(String s){
	List<String> res = new ArrayList<String>();
	int cnt = s.length();
	for(int i = 0; i< (1<<cnt); i++){
		String ss = "";
		for(int j=0;j<cnt;j++){
		//check every position is '1' or '0'
			if((1&(i>>j))==1)
				ss+=s.charAt(j);
		}
		res.add(ss);
	}
	return res;
}