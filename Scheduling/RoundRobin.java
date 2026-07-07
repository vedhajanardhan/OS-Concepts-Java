import java.util.*;

public class RoundRobin {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input: number of processes
        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        int[] burstTime = new int[n];
        int[] waitingTime = new int[n];
        int[] turnaroundTime = new int[n];

        // Input: burst times
        for (int i = 0; i < n; i++) {
            System.out.print("Enter burst time for process " + (i+1) + ": ");
            burstTime[i] = sc.nextInt();
        }

        // Input: time quantum
        System.out.print("Enter time quantum: ");
        int quantum = sc.nextInt();

        // Copy burst times for processing
        int[] remainingTime = Arrays.copyOf(burstTime, n);
        int time = 0;

        // Round Robin logic
        while (true) {
            boolean done = true;
            for (int i = 0; i < n; i++) {
                if (remainingTime[i] > 0) {
                    done = false;
                    if (remainingTime[i] > quantum) {
                        time += quantum;
                        remainingTime[i] -= quantum;
                    } else {
                        time += remainingTime[i];
                        waitingTime[i] = time - burstTime[i];
                        remainingTime[i] = 0;
                    }
                }
            }
            if (done) break;
        }

        // Calculate turnaround times
        for (int i = 0; i < n; i++) {
            turnaroundTime[i] = burstTime[i] + waitingTime[i];
        }

        // Output results
        System.out.println("\nProcess\tBurst\tWaiting\tTurnaround");
        for (int i = 0; i < n; i++) {
            System.out.println((i+1) + "\t" + burstTime[i] + "\t" + waitingTime[i] + "\t" + turnaroundTime[i]);
        }

        // Average times
        double avgWait = Arrays.stream(waitingTime).sum() / (double)n;
        double avgTurn = Arrays.stream(turnaroundTime).sum() / (double)n;
        System.out.println("\nAverage Waiting Time: " + avgWait);
        System.out.println("Average Turnaround Time: " + avgTurn);
    }
}
