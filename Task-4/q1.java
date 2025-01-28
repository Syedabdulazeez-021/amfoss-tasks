import java.io.*;
import java.util.*;

public class q1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0;i<n;i++){
            arr[i] = sc.nextInt();
        }
        int m = sc.nextInt();
        int[] arr2 = new int[m];
        for(int i = 0;i<m;i++){
            arr2[i] = sc.nextInt();
        }
        boolean[] selected = new boolean[m];
        Arrays.sort(arr);
        Arrays.sort(arr2);
        for(int i = 0;i<n;i++){
            for(int j = 0;j<m;j++){
                if(selected[j] == false && arr[i] == arr2[j]){
                    selected[j] = true;
                    break;
                }
            }
        }
        for(int i = 0;i<m;i++){
            if(selected[i] == false){
                System.out.print(arr2[i] + " ");
            }
        }
    }
}