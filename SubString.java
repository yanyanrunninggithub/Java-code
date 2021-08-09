//#1763. Longest Nice Substring
//A string s is nice if, for every letter it appears both in uppercase and lowercase
//Given a string s, return the longest substring of s that is nice. If there are multiple, return the substring of the earliest occurrence
//solution:Brute force and check each substring to see if it is nice.
public String longestNiceSubstring(String s) {
	if(s.length()<2)
		return "";
	int len = 0;
	String ans = "";
	for(int i=0;i<s.length()-1;++i){
		for(int j=s.length();j>i;--j){//j is the end index+1
			String sub = s.substring(i,j);
			if(isNice(sub)){
				if(sub.length()>len){
					len = sub.length();
					ans = sub;
				}
			}
		}
	}
	return ans;
}
private boolean isNice(String s){
	int[] up = new int[26];
	int[] low= new int[26];
	for(int i=0; i<s.length();++i){ 
		char ch = s.charAt(i);
		if(ch>='a' && ch<='z')
			low[ch-'a']=1;
		else
			up[ch-'A']=1;
	}
	for(int i=0;i<26;++i){
		if(low[i]!=up[i])
			return false;
	}
	return true;
}