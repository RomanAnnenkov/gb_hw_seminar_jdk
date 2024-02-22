import java.util.concurrent.locks.ReentrantLock;

public class Fork extends ReentrantLock {
    private final String name;


    public Fork(String name) {
        this.name = name;

    }

    @Override
    public String toString() {
        return String.format("fork%s", name);
    }


}
