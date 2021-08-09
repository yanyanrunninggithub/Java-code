//#1360. Number of Days Between Two Dates
class Solution {
    public int daysBetweenDates(String date1, String date2) {
        if(date1.compareTo(date2)==0) return 0;
        if(date1.compareTo(date2)>0){//make date1 less than date2
            String s = date1;
            date1 = date2;
            date2 = s;
        }
        String[] d1 = date1.split("-");
        String[] d2 = date2.split("-");
        int[] month = new int[]{0,31,28,31,30,31,30,31,31,30,31,30,31};
        int yr1 = Integer.valueOf(d1[0]);
        int yr2 = Integer.valueOf(d2[0]);
        int mo1 = Integer.valueOf(d1[1]);
        int mo2 = Integer.valueOf(d2[1]);
        int day1 = Integer.valueOf(d1[2]);
        int day2 = Integer.valueOf(d2[2]);
        int ans = 0;
        if(yr1==yr2 && mo1==mo2){
          return Math.abs(day2-day1);  
        }
        else if(yr1==yr2){
            if(isLeap(yr1))
                month[2] = 29;
            for(int i=mo1+1;i<mo2;++i){
                ans += month[i];
            }
            ans += month[mo1]-day1;
            ans += day2;
        }
        else{
            for(int i=yr1+1;i<yr2;++i){
                ans += (i%4==0)? 366 :365;
            }
            //dec 31-mo1-d1
            for(int i=mo1+1;i<13;++i){
                ans += month[i];
                if(i==2 && isLeap(yr1))
                    ans+=1;
            }
            ans += month[mo1]-day1;
            ans += (mo1==2 && isLeap(yr1)) ? 1 : 0;
            for(int i=0;i<mo2;++i){
                ans += month[i];
                if(i==2 && isLeap(yr2))
                    ans+=1;
            }
            ans += day2;
        }
        return ans;
    }
    public boolean isLeap(int year) {
      return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);
    }
}