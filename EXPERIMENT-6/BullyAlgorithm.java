import java.util.Scanner;

public class BullyAlgorithm {
    static int n;
    static int[] processes;
    static boolean[] active;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of processes: ");
        n = sc.nextInt();
        processes = new int[n];
        active = new boolean[n];

        for (int i = 0; i < n; i++) {
            processes[i] = i + 1;
            active[i] = true;
        }

        System.out.print("Enter process to crash: ");
        int crash = sc.nextInt();
        active[crash - 1] = false;

        System.out.print("Enter process initiating election: ");
        int init = sc.nextInt();
        bullyElection(init - 1);
    }

    static void bullyElection(int init) {
        int leader = init;
        for (int i = init + 1; i < n; i++) {
            if (active[i]) {
                System.out.println("Process " + processes[init] + " sends ELECTION to Process " + processes[i]);
                leader = i;
            }
        }
        if (leader != init) {
            bullyElection(leader);
        } else {
            System.out.println("Process " + processes[leader] + " becomes LEADER");
        }
    }
}