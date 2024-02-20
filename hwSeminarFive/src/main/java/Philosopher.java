import java.util.concurrent.CountDownLatch;

public class Philosopher extends Thread {

    private final int MAX_FOOD_COUNT = 3;
    private final int TIME_FOR_EAT = 2000;
    private final int TIME_FOR_MEDITATE = 1000;

    private int foodCount;
    private boolean isFull;
    private final String name;
    private final Fork leftFork;
    private final Fork rightFork;

    private final CountDownLatch countDownLatch;

    public Philosopher(String name, Fork leftFork, Fork rightFork, CountDownLatch countDownLatch) {
        isFull = false;
        this.name = name;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        while (!isFull) {
            try {
                eat();
                meditate();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(name + " is full and sleeping)");
        countDownLatch.countDown();
    }

    private void meditate() throws InterruptedException {
        System.out.println(name + " meditating.");
        Thread.sleep(TIME_FOR_MEDITATE);
    }

    private void eat() throws InterruptedException {
        if (lockForks()) {
            Thread.sleep(TIME_FOR_EAT);

            if (foodCount >= MAX_FOOD_COUNT) {
                isFull = true;
            }

            unlockForks();
        }
    }

    private boolean lockForks() {
        if (!leftFork.tryLock()) {
            return false;
        }
        if (!rightFork.tryLock()) {
            leftFork.unlock();
            return false;
        }
        foodCount++;
        System.out.printf("%s take %s, %s and start %s approach to spaghetti. \n", name, leftFork, rightFork, foodCount);
        return true;

    }

    private void unlockForks() {
        System.out.printf("%s finish and put back %s and %s. \n", name, leftFork, rightFork);
        leftFork.unlock();
        rightFork.unlock();
    }
}
