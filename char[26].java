//#1370. Increasing Decreasing String
// Input: s = "aaaabbbbcccc" Output: "abccbaabccba"
class Solution {
    public String sortString(String s) {
        int[] cnt = new int[26];//use a cnt array to help to mark each elements showed times
        String res = "";
        for(int i=0; i<s.length(); ++i){
            int index = s.charAt(i)-'a';
            cnt[index]++;
        }
        while(res.length()<s.length()){
            res += addLowToHigh(cnt);
            res += addHighToLow(cnt);
        }
        return res;
    }
    private String addLowToHigh(int[] cnt){
        String str = "";
        for(int i=0; i<cnt.length; ++i){
            if(cnt[i]!=0) {
                str += Character.toString('a' + i);
                cnt[i]--;
            }
        }
        return str;
    }
    private String addHighToLow(int[] cnt){
        String str = "";
        for(int i=cnt.length-1; i>=0; --i){
            if(cnt[i]!=0) {
                str += Character.toString('a' + i);
                cnt[i]--;
            }
        }
        return str;
    }
}
//#1002. Find Common Characters
//Input: ["bella","label","roller"]Output: ["e","l","l"]
public List<String> commonChars(String[] A) {
	List<String> ans = new ArrayList<>();
	int[] minTimes = new int[26];
	Arrays.fill(minTimes,Integer.MAX_VALUE);
	int[] eachTimes = new int[26];
	for(String s : A){
		for(int i=0;i<s.length();++i){
			eachTimes[(s.charAt(i)-'a')]++;
		}
		for(int i=0; i<26; ++i){
			minTimes[i] = Math.min(minTimes[i],eachTimes[i]);
		}
		Arrays.fill(eachTimes,0);
	}
	for(int i=0; i<26; ++i){
		for(int cnt=0; cnt<minTimes[i];--cnt)
			ans.add("" + (char)(i+'a'));   
	}
	return ans;
}
//#1624. Largest Substring Between Two Equal Characters
//Input: s = "abcaca" Output: 4 if not exist return -1
public int maxLengthBetweenEqualCharacters(String s) {
	int max = -1;
	int[] idx = new int[26];
	Arrays.fill(idx,-1);
	for(int i=0; i<s.length();++i){
		char ch = s.charAt(i);
		if(idx[ch-'a']==-1){
			idx[ch-'a'] = i;
		}
		max = Math.max(max,(i-idx[ch-'a']-1));
	}
	return max;
}
//#242. Valid Anagram
//all letter showed times in s and t are same
public boolean isAnagram(String s, String t) {
	int[] times = new int[26];
	if(s.length() != t.length())
		return false;
	for(int i=0;i<s.length();++i){
		times[s.charAt(i)-'a']++;
	}
	 for(int i=0;i<t.length();++i){
		times[t.charAt(i)-'a']--;
		 if(times[t.charAt(i)-'a']<0)//because for same length 2 string, if there is a different letter, it must make the times <0
			 return false;
	}
	return true;
}