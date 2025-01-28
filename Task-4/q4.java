import java.io.*;
import java.util.*;

public class q4 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int[] ans = new int[10];
        for(int i=0; i<s.length(); i++){
            int curr = Character.getNumericValue(s.charAt(i));
            if(curr >=0 && curr <= 9){
                ans[curr] =  ans[curr] + 1;
            }
        }
        for(int count: ans){
            System.out.print(count + " ");
        }
    }
}