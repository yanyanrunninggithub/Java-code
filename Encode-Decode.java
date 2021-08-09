
import java.util.HashMap;
import java.util.Random;


public class Main {
    protected static HashMap<String,String> dic = new HashMap<String,String>();

    public static void main(String[] args) {

        String longstr = " https://leetcode.com/problems/design-tinyurl";
        String encode = encode(longstr);
        String decode = decode(encode);
    }
    public static String getRandomStr(int n)
    {
        String source = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String salt ="";
        Random rnd = new Random();
        while(salt.length()<n)
        {
            salt += source.charAt(rnd.nextInt(62));
        }
        return salt;
    }

    public static String encode(String longstr){
        String res = "";
        String shortstr = "";
        if(dic.containsValue((longstr)))
        {
            String key = "";
            for(HashMap.Entry entry: dic.entrySet()){
                if(entry.getValue() == longstr){
                    key = entry.getKey().toString();
                    return "http://tinyurl.com/"  + key;
                }
            }
        }
        else {
            String v = getRandomStr(4);
            dic.put(v,longstr);
            shortstr = "http://tinyurl.com/" + v;
        }
        return shortstr;
    }
    public static String decode(String shortstr){
        String longstr = "";
        String[] str = shortstr.split("/");
        if(dic.containsKey(str[str.length-1]))
        {
            longstr = dic.get(str[str.length-1]);
        }
        return longstr;
    }
}
