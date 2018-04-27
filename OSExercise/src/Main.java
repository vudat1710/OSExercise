public class Main {
    static Bathroom bathroom = new Bathroom(2);
    static int count = 0;

    public static void main(String[] args) {
        bathroom.incomingUser();
        bathroom.users.forEach((t) -> {
            System.out.println(t);
        });

        Thread[] male = new Thread[bathroom.users.size()];
        Thread[] female = new Thread[bathroom.users.size()];


        bathroom.users.forEach((t) -> {
            if (t.startsWith("M")) {
                male[count] = new MenThread();
                male[count].start();
                count++;
            }
            if (t.startsWith("W")) {
                female[count] = new WomenThread();
                female[count].start();
                count++;
            }
        });

        for (int i = 0; i < bathroom.users.size(); i++) {
            Character a = bathroom.users.get(i + 1).charAt(0);
            Character b = bathroom.users.get(i).charAt(0);
            if (!(a.equals(b))) {
                if (b.equals("M")) {
                    try {
                        male[i].join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (b.equals("W")) {
                    try {
                        female[i].join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
