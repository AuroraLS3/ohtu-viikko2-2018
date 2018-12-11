package ohtu.kivipaperisakset.io;

public interface IO {

    void print(Object msg);

    default void println(Object msg) {
        print(msg);
        println();
    }

    default void println() {
        print("\n");
    }

    String input();

}
