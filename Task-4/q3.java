import java.util.Scanner;

public class q3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int X = sc.nextInt();
        int N = sc.nextInt();
        System.out.println(findWays(X, N, 1));

        sc.close();
    }

    private static int findWays(int X, int N, int num) {
        int power = (int) Math.pow(num, N);
        if (power > X) {
            return 0;
        }

        // Base case: If power equals X, we found one valid combination
        if (power == X) {
            return 1;
        }

        // Recursive case: Include the current number and exclude it
        return findWays(X - power, N, num + 1) + findWays(X, N, num + 1);
    }
}