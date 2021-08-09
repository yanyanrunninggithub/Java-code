//#1528 Shuffle String
//Input: s = "codeleet", indices = [4,5,6,7,0,2,1,3] Output: "leetcode"
public String restoreString(String s, int[] indices) {
	Map<Integer,Character> map = new HashMap<>();
	for(int i=0; i<s.length();++i){
		map.put(indices[i],s.charAt(i));
	}
	TreeMap<Integer,Character> tree = new TreeMap<>(map);
	String res = "";

	List<Character> word = new ArrayList<>(tree.values());
	for(var c : word){
		res += c;
	}
	return res;
}
//#1309. Decrypt String from Alphabet to Integer Mapping
//Characters ('a' to 'i') are represented by ('1' to '9') respectively.Characters ('j' to 'z') are represented by ('10#' to '26#') respectively. 
//Input: s = "10#11#12" Output: "jkab"
 public String freqAlphabets(String s) {
	String res = "";
	int first = s.indexOf("#");
	for(int i=0; i<s.length(); ++i){
		if((i+2<s.length() && s.charAt(i+2)!='#') || i+2>=s.length())
		   res += Character.toString(s.charAt(i)-'1'+'a');
		if(i+2<s.length() && s.charAt(i+2)=='#'){
		   int n = Integer.valueOf(s.substring(i,i+2))-10;
			res += Character.toString(n+'j');
			i = i+2;
		}

	}
	return res;        
}
//#1436. Destination City
//Input: paths = [["London","New York"],["New York","Lima"],["Lima","Sao Paulo"]] Output: "Sao Paulo" 
//Explanation: Starting at "London" city you will reach "Sao Paulo" city which is the destination city. Your trip consist of: "London" -> "New York" -> "Lima" -> "Sao Paulo".
public String destCity(List<List<String>> paths) {
	String des = "";
	List<String> from = new ArrayList<String>();
	List<String> to = new ArrayList<String>();
	for(var p : paths){
		from.add(p.get(0));
		to.add(p.get(1));
	}
	des = to.get(0);
	while(from.contains(des)){
		des = to.get(from.indexOf(des));//get the index of the city in the from, then get its matched to city
	}
	return des;	
}
//#811. Subdomain Visit Count
//Input: ["900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"]
//Output: ["901 mail.com","50 yahoo.com","900 google.mail.com","5 wiki.org","5 org","1 intel.mail.com","951 com"]
public List<String> subdomainVisits(String[] cpdomains) {
  Map<String,Integer> map = new HashMap<>();
	List<String> res = new ArrayList<>();
	for(int i=0; i<cpdomains.length; ++i){
		String[] str = cpdomains[i].split(" ");
		int cnt = Integer.valueOf(str[0]);
		//map.put(str[1],cnt);
		String site = str[1];
		int dotIndex = 0;
		while(dotIndex!=-1){
			if(map.containsKey(site))
				map.put(site,map.get(site)+cnt);
			else
				map.put(site, cnt);
			dotIndex = site.indexOf(".");
			site = site.substring(dotIndex+1);                
		}
	}
	for(var kvp : map.entrySet()){
		String website = kvp.getValue() + " "+String.valueOf(kvp.getKey());
		res.add(website);
	}
	return res;
}
//#821. Shortest Distance to a Character
//Input: s = "loveleetcode", c = "e"Output: [3,2,1,0,1,0,0,1,2,2,1,0]
//scan from 0->end then scan from end->0
class Solution {
	 public int[] shortestToChar(String s, char c) {
        int[] ans = new int[s.length()];
        int index = -1;
        for(int i=0; i<s.length();++i){
            if(s.charAt(i) == c)
                index = i;
            if(index<0){
                ans[i] = Integer.MAX_VALUE;
                continue;
            }
            ans[i] = Math.abs(i-index);        
        }
        index = -1;
        for(int i=s.length()-1;i>=0;--i){
            if(s.charAt(i) == c)
                index = i;
            ans[i] = Math.min(ans[i],Math.abs(i-index));        
        }  
        return ans;        
    }
}
//#1758. Minimum Changes To Make Alternating Binary String
//straight forward: change string to chararray, because s.charAt(i) can not be changed value
 public int minOperations(String s) {
	int cnt = 0;
	if(s.length()<2)
		return 0;
	char[] arr = s.toCharArray();
	for(int i=1; i<arr.length; ++i){
		if(arr[i]==arr[i-1]){
			arr[i] = (arr[i] == '1')? '0' : '1';
			cnt++;
		}
	}
//1101 cnt=3, but change the first char should be min change, so 2 ways to change, cnt or length-cnt	
	return Math.min(cnt,s.length()-cnt);
}
//#917. Reverse Only Letters
//Input: "a-bC-dEf-ghIj" Output: "j-Ih-gfE-dCba"
//2 pointer solution
public String reverseOnlyLetters(String S) {
	char[] arr = S.toCharArray();
	int i=0, j=arr.length-1;
	while(i<j){
		if(Character.isLetter(arr[i]) && Character.isLetter(arr[j])){
			char ch = arr[i];
			arr[i] = arr[j];
			arr[j] = ch;
			i++;
			j--;
		}
		else{
			if(!Character.isLetter(arr[i]))
				i++;
			if(!Character.isLetter(arr[j]))
				j--;
		}                
	}
	return new String(arr);        
}
//#696. Count Binary Substrings
//Give a string s, count the number of non-empty (contiguous) substrings that have the same number of 0's and 1's, 
//Input: "00110011" Output: 6
//Explanation: There are 6 substrings that have equal number of consecutive 1's and 0's: "0011", "01", "1100", "10", "0011", and "01".
//solution 1:straight forward
public int countBinarySubstrings(String s) {
	int cnt = 0;
	for(int i=0;i<s.length();++i){
		for(int len=2; i+len<s.length();len=len+2){
			//check i~i+len/2-1 i+len/2~i+len-1
			char key1 = s.charAt(i);
			char key2 = (key1=='1')? '0' : '1';
			int k=i;
			for(k=i;k<i+len;++k){
				if(k<i+len/2 && key1!=s.charAt(k))
					break;
				if(k>=i+len/2 && key2!=s.charAt(k))
					break;
			}
			//if not break, after the last ++k, k should be i+len
			if((k==(i+len)) && (key2==s.charAt(k)))
				cnt++;
		}
	}
	return cnt;
}
//solution 2: update pre and cur, pre is previous different digit cnt, cur is current digit cnt, such as 001, there must be 1 good substring 
public int countBinarySubstrings(String s) {
	int cnt = 0;
	int pre = 0, cur = 1;
	for(int i=1;i<s.length();++i){
	   if(s.charAt(i) == s.charAt(i-1))
		   cur++;
		else{
			pre = cur;
			cur = 1;
		}
		if(pre>=cur)
			cnt++;
	}
	return cnt;
}
//#953. Verifying an Alien Dictionary
public boolean isAlienSorted(String[] words, String order) {
	if(words.length<=1)
		return true;
	for(int i=1;i<words.length;++i){
		int len = Math.min(words[i-1].length(),words[i].length());
		int j=0;
		for(;j<len;++j){
			char pre = words[i-1].charAt(j);
			char cur = words[i].charAt(j);
			if(pre != cur){
				if(order.indexOf(pre)>order.indexOf(cur))
					return false;
				else
					break;
			}
		}
		if(j<words[i-1].length() && j==words[i].length())
			return false;
	}
	return true;
}
//#1071. Greatest Common Divisor of Strings
//Input: str1 = "ABABAB", str2 = "ABAB" Output: "AB"
//solution 1: get common len of str1 len and str2 len, then from big start to check
 public String gcdOfStrings(String str1, String str2) {
	int l1 = str1.length();
	int l2 = str2.length();
	List<Integer> len = new ArrayList<>();
	len.add(1);
	for(int i=2;i<=Math.min(l1,l2);++i){
		if(l1%i==0 && l2%i==0)
			len.add(i);
	}
	Collections.sort(len); 
	Collections.reverse(len);
	for(int i=0;i<len.size();++i){
	   String ans = str1.substring(0,len.get(i));
		int cnt1 = l1/len.get(i), cnt2 = l2/len.get(i);
		String s1="",s2="";
		for(int j=0;j<cnt1;++j)
			s1 += ans;
		for(int j=0;j<cnt2;++j)
			s2 += ans;
		if(s1.equals(str1) && s2.equals(str2))
			return ans;
	}
	return "";
}
//solution 2: let the longer length str show up before smaller length str, then str1 must contains str2, remove str2 from str1, until 2 str equals, that will be the common str
public String gcdOfStrings(String str1, String str2) {
	if(str1.length()<str2.length())
		return gcdOfStrings(str2,str1);//bigger length is before smaller
	else if(!str1.startsWith(str2))
		return "";
	else if(str1.equals(str2))
		return str2;
	else
		return gcdOfStrings(str1.substring(str2.length()),str2);
}
//#1576. Replace All ?'s to Avoid Consecutive Repeating Characters
 public String modifyString(String s) {
	char[] ans = s.toCharArray();
	int i=0;
	while(i<s.length()){
		while(i<s.length() && ans[i]!='?'){
			i++;
		}
		while(i<s.length() && ans[i]=='?'){
			ans[i] = getGoodChar(ans,i);
			i++;
		}
	}
	return new String(ans);
}
public char getGoodChar(char[] s, int pos){
	for(char ch='a'; ch<='z'; ++ch){
		if(pos>0 && s[pos-1]==ch)
			continue;
		if(pos<s.length-1 && s[pos+1]==ch)
			continue;
		return ch;
	}
	return '0';
}
//#28. Implement strStr()
public int strStr(String haystack, String needle) {
	if(needle=="") return 0;
	int p=0,q=0;
	while(q<needle.length()&&p<haystack.length()){
		if(haystack.charAt(p) == needle.charAt(q)){
			p++;
			q++;
			
		}
		else{
			p = p-q+1;
			q=0;
		}
	}
	return (q==needle.length()) ? p-needle.length() : -1;	   
}
//#925. Long Pressed Name
 public boolean isLongPressedName(String name, String typed) {
	if(typed.length()<name.length())
		return false;
	int[] cnt = new int[26];
	int p=0,q=0;
	while(q<typed.length()){
		if(p<name.length() && name.charAt(p)==typed.charAt(q)){
			p++;
			q++;
		}
		else{
			if(q==0 || typed.charAt(q-1)!=typed.charAt(q))
				return false;
			q++;
		}
	}
 }
 //#1668. Maximum Repeating Substring
 public int maxRepeating(String sequence, String word) {
	if(sequence.indexOf(word) == -1) return 0;
	String s = word;
	int i=1;
	for(i=1;s.length()<=sequence.length();++i){
		if(!sequence.contains(s))
			return i-1;
		s += word;
	}
	return i-1;
}
//OR Writing:  
public int maxRepeating(String sequence, String word) {
	if(sequence.indexOf(word) == -1) return 0;
	String s = word;
	int i=1;
	for(i=1;;++i){//no terminationCondition in for loop, so it will jump out from if condition then we have to remove the outside return
		if(!sequence.contains(s))
			return i-1;
		s += word;
	}
	//return i-1;//here we have to remove return, otherwise it will compile error because this line always not execute
}
//#459. Repeated Substring Pattern
public boolean repeatedSubstringPattern(String s) {
	int len = s.length();
	for(int i=len/2;i>=1;--i){
		if(len%i!=0)
			continue;
		int cnt= len/i;
		String sub = s.substring(0,i);
		String copy = "";
		for(int j=0;j<cnt;++j){
			copy += sub;
		}
		if(s.equals(copy))
			return true;
	}
	return false;
}