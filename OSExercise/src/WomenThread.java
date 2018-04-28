public class WomenThread extends Thread {
    private static Bathroom b = new Bathroom();
    private int id;
    private boolean canLeave=false;

    public WomenThread(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        womanEnter(id);
        if (this.canLeave) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Done");
            womanExit(id);
        }
    }

    public void womanEnter(int id) {
        b.lock.lock();
        if (b.freeResources > 0 && b.nMenUsing == 0) {
            System.out.println("Woman " + id + " enter the bathroom");
            b.nWomenUsing++;
            b.freeResources--;
            this.canLeave=true;
            if (b.freeResources == 0) System.out.println("The bathroom is full");
        }
//        else {
//            try {
//                b.nWomenWaiting++;
//                b.womenQueue.await();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        b.lock.unlock();
    }

    public static void womanExit(int id) {
        b.lock.lock();
        System.out.println("Woman " + id + " left the bathroom");
        b.nWomenUsing--;
        b.freeResources++;
        if (b.freeResources == b.CAPACITY) System.out.println("The bathroom is empty");
//        while (b.nWomenUsing > 0) {
//
//        }
//        if (b.nWomenUsing==0 && b.nMenWaiting > 0) {
//            b.nMenWaiting--;
//            b.menQueue.signal();
//        }
//        while(b.nWomenUsing!=0 || b.nMenWaiting == 0){
//            try {
//                b.menQueue.await();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        b.lock.unlock();
    }
}
