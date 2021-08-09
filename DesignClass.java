//#1603 Design Parking System
class ParkingSystem {
    int bigSlot, mediumSlot, smallSlot;

    public ParkingSystem(int big, int medium, int small) {
        bigSlot = big;
        mediumSlot = medium;
        smallSlot = small;        
    }
    
    public boolean addCar(int carType) {
        if(carType == 1){
            if(bigSlot>=1){
                bigSlot--;
                return true;
            }
            else
                return false;
        }
        else if(carType == 2){
            if(mediumSlot>=1){
                mediumSlot--;
                return true;
            }
            else
                return false;
        }
        else if(carType == 3){
            if(smallSlot>=1){
                smallSlot--;
                return true;
            }
            else
                return false;
        }
        else
            return false;
        
    }
}

//#1656. Design an Ordered Stream
class OrderedStream {
    String[] str;
    int ptr;

    public OrderedStream(int n) {
        str = new String[n];
        ptr = 0;
    }
    
    public List<String> insert(int idKey, String value) {
        str[idKey-1] = value;
        List<String> res = new ArrayList<>();
        while(ptr<str.length && str[ptr] != null){
                res.add(str[ptr]);
                ptr++;
            }
        return res;
        
    }
}