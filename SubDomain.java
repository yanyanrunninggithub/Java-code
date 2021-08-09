//Subdomain visit count: Input: ["900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"]
//Output: ["901 mail.com","50 yahoo.com","900 google.mail.com","5 wiki.org","5 org","1 intel.mail.com","951 com"]
public static List<String> subdomainVisitTimes(String[] input){
	List<String> out = new ArrayList<String>();

	Map<String, Integer> m = new HashMap<String,Integer>();
	for(String line : input)
	{
		String[] record = line.split(" ");
		int time = Integer.valueOf(record[0]);
		m.put(record[1], m.getOrDefault(record[1],0)+time);
		for(int i = 0;i<record[1].length();i++){
			if(record[1].charAt(i)=='.'){
				String sub = record[1].substring(i+1);
				m.put(sub,m.getOrDefault(sub,0)+time);
			}
		}
	}
	for(var entry : m.entrySet()){
		out.add(Integer.toString(entry.getValue()) + ' ' + entry.getKey());
	}
	return out;
}