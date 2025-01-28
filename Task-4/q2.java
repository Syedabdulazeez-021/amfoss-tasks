import java.io.*;
import java.util.*;

public class q2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Map<Integer, Integer> hash =  new HashMap<>();
        while(sc.hasNext()){
            int room = sc.nextInt();
            if(hash.containsKey(room)){
                hash.put(room, hash.get(room) + 1);
            } else{
                hash.put(room, 1);
            }
        }
        for(Map.Entry<Integer, Integer> entry: hash.entrySet()){
            if(entry.getValue() == 1){
                System.out.print(entry.getKey());
            }
        }
    }
}