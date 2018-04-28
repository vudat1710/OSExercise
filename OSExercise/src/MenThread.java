public class MenThread extends Thread {
    private static Bathroom b = new Bathroom();
    private int id;
    private boolean canLeave= false;

    public MenThread(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        manEnter(id);
        if (this.canLeave) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Done");
            manExit(id);
        }
    }

    public void manEnter(int id) {
        b.lock.lock();
        if (b.freeResources > 0 && b.nWomenUsing == 0) {
            System.out.println("Man " + id + " enter the bathroom");
            b.nMenUsing++;
            b.freeResources--;
            this.canLeave= true;
            if (b.freeResources == 0) System.out.println("The bathroom is full");
        }
//        else {
//            try {
//                b.nMenWaiting++;
//                b.menQueue.await();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        b.lock.unlock();
    }

    public void manExit(int id) {
        b.lock.lock();
        System.out.println("Man " + id + " left the bathroom");
        b.nMenUsing--;
        b.freeResources++;
        if (b.freeResources == b.CAPACITY) System.out.println("The bathroom is empty");
//        while (b.nMenUsing > 0) {
//
//        }
//        if (b.nMenUsing==0 && b.nWomenWaiting > 0) {
//            b.nWomenWaiting--;
//            b.womenQueue.signal();
//        }
//
//        while(b.nMenUsing!=0 || b.nWomenWaiting == 0){
//            try {
//                b.womenQueue.await();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        b.lock.unlock();
    }
}
