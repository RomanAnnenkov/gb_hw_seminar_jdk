public class Fork {
    private final String name;


    public Fork(String name) {
        this.name = name;

    }

    @Override
    public String toString() {
        return String.format("fork%s", name);
    }


}
