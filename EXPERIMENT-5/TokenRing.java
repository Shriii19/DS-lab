import java.util.Scanner;

class TokenRing {
    int n;
    int token;

    TokenRing(int n) {
        this.n = n;
        this.token = 0;
    }

    void runSimulation() {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < n; i++) {
            System.out.println("Process " + i + " ready.");
        }

        int choice;
        do {
            System.out.println("\nCurrent token with Process " + token);
            System.out.print("Do you want to enter critical section? (1-Yes / 0-No): ");
            choice = sc.nextInt();

            if (choice == 1) {
                System.out.println("Process " + token + " entering Critical Section...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {}
                System.out.println("Process " + token + " leaving Critical Section...");
            }
            token = (token + 1) % n;
        } while (true);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();
        TokenRing ring = new TokenRing(n);
        ring.runSimulation();
    }
}