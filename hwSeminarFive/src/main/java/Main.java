import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("hw seminar five");

        //Пять безмолвных философов сидят вокруг круглого стола, перед каждым философом стоит тарелка спагетти.
        //Вилки лежат на столе между каждой парой ближайших философов.
        //Каждый философ может либо есть, либо размышлять.
        //Философ может есть только тогда, когда держит две вилки — взятую справа и слева.
        //Философ не может есть два раза подряд, не прервавшись на размышления (можно не учитывать)


        Fork fork1 = new Fork("1");
        Fork fork2 = new Fork("2");
        Fork fork3 = new Fork("3");
        Fork fork4 = new Fork("4");
        Fork fork5 = new Fork("5");

        CountDownLatch countDownLatch = new CountDownLatch(5);

        List<Philosopher> roundTable = new ArrayList<>();

        roundTable.add(new Philosopher("Diogen", fork1, fork2, countDownLatch));
        roundTable.add(new Philosopher("Platon", fork2, fork3, countDownLatch));
        roundTable.add(new Philosopher("Aristotel", fork3, fork4, countDownLatch));
        roundTable.add(new Philosopher("Hippokrat", fork4, fork5, countDownLatch));
        roundTable.add(new Philosopher("Sokrat", fork5, fork1, countDownLatch));

        System.out.println("The banquet begins.");

        for (Philosopher philosopher : roundTable) {
            philosopher.start();
        }

        while (countDownLatch.getCount() != 0) {
            Thread.sleep(1000);
        }

        System.out.println("The banquet is over.");
    }
}
