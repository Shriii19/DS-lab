import java.util.*;

public class RingAlgorithm {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();
        int[] processes = new int[n];
        boolean[] active = new boolean[n];

        for (int i = 0; i < n; i++) {
            processes[i] = i + 1;
            active[i] = true;
        }

        System.out.print("Enter process to crash: ");
        int crash = sc.nextInt();
        active[crash - 1] = false;

        System.out.print("Enter initiator process: ");
        int init = sc.nextInt() - 1;

        List<Integer> election = new ArrayList<>();
        int i = init;

        do {
            if (active[i]) {
                election.add(processes[i]);
                System.out.println("Process " + processes[i] + " passes message");
            }
            i = (i + 1) % n;
        } while (i != init);

        int leader = Collections.max(election);
        System.out.println("Process " + leader + " becomes LEADER");
    }
}