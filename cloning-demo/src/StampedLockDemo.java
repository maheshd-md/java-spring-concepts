import java.util.concurrent.locks.StampedLock;

public class StampedLockDemo {

    private double amount;

    private StampedLock stampedLock = new StampedLock();

    /*
        tryOptimisticRead()
            ↓
    Read data without blocking
            ↓
    validate(stamp)
            ↓
         valid?
        /     \
      yes      no
       ↓        ↓
    use data   acquire real read lock
     */
    public double getAmount() {
        long optimisticStamp = stampedLock.tryOptimisticRead();
        double currentAmount = amount;
        if (!stampedLock.validate(optimisticStamp)) {
            long readStamp = stampedLock.readLock();
            try {
                currentAmount = amount;
            } finally {
                stampedLock.unlock(readStamp);
            }
        }
        return currentAmount;
    }

    public void updateAmount(double newAmount) {
        long writeStamp = stampedLock.writeLock();
        try {
            this.amount = newAmount;
        } finally {
            stampedLock.unlock(writeStamp);
        }
    }

    public static void main(String[] args) throws InterruptedException {

        StampedLockDemo demo = new StampedLockDemo();

        // Writer thread
        Thread writer = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                double newAmount = i * 100;
                demo.updateAmount(newAmount);
                System.out.println(Thread.currentThread().getName() + " updated amount to: " + newAmount);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }, "Writer");

        // Reader 1
        Thread reader1 = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                double amount = demo.getAmount();
                System.out.println(Thread.currentThread().getName() + " read amount: " + amount);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }, "Reader-1");

        // Reader 2
        Thread reader2 = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                double amount = demo.getAmount();
                System.out.println(Thread.currentThread().getName() + " read amount: " + amount);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }, "Reader-2");

        writer.start();
        reader1.start();
        reader2.start();

        writer.join();
        reader1.join();
        reader2.join();

        System.out.println("Final amount: " + demo.getAmount());
    }
}