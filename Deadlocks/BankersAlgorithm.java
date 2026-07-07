import java.util.*;

public class BankersAlgorithm {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input: number of processes and resources
        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();
        System.out.print("Enter number of resources: ");
        int m = sc.nextInt();

        int[][] max = new int[n][m];
        int[][] allocation = new int[n][m];
        int[][] need = new int[n][m];
        int[] available = new int[m];

        // Input: Max matrix
        System.out.println("Enter Max matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                max[i][j] = sc.nextInt();
            }
        }

        // Input: Allocation matrix
        System.out.println("Enter Allocation matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                allocation[i][j] = sc.nextInt();
            }
        }

        // Input: Available resources
        System.out.println("Enter Available resources:");
        for (int j = 0; j < m; j++) {
            available[j] = sc.nextInt();
        }

        // Calculate Need matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                need[i][j] = max[i][j] - allocation[i][j];
            }
        }

        // Safety algorithm
        boolean[] finish = new boolean[n];
        int[] safeSequence = new int[n];
        int[] work = Arrays.copyOf(available, m);
        int count = 0;

        while (count < n) {
            boolean found = false;
            for (int i = 0; i < n; i++) {
                if (!finish[i]) {
                    int j;
                    for (j = 0; j < m; j++) {
                        if (need[i][j] > work[j]) break;
                    }
                    if (j == m) {
                        for (int k = 0; k < m; k++) {
                            work[k] += allocation[i][k];
                        }
                        safeSequence[count++] = i;
                        finish[i] = true;
                        found = true;
                    }
                }
            }
            if (!found) {
                System.out.println("System is not in a safe state.");
                return;
            }
        }

        // Output safe sequence
        System.out.print("System is in a safe state.\nSafe sequence: ");
        for (int i = 0; i < n; i++) {
            System.out.print("P" + safeSequence[i] + " ");
        }
    }
}
