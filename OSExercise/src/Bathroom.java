import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Bathroom {
    int size;
    List<String> users = new ArrayList<>();
    String currentSex;
    static int men, women;

    public Bathroom() {
    }   

    public Bathroom(int size) {
        this.size = size;
    }

    public boolean isEmpty(){ return this.users.isEmpty();}

    public boolean isFull(){return this.users.size()==size;}

    public void incomingUser() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of men: ");
        men = sc.nextInt();
        System.out.println("Enter the number of women: ");
        women = sc.nextInt();
        int c = 1;
        while (men != 0 || women != 0) {
            int r = new Random().nextInt(2);
            switch (r) {
                case 0:
                    if (men > 0) {
                        users.add("Man" + c);
                        men--;
                        c++;
                    }
                    break;
                case 1:
                    if (women > 0) {
                        users.add("Woman" + c);
                        women--;
                        c++;
                    }
                    break;
            }

        }
    }
}
