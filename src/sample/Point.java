package sample;

/**
 * Created by Pat111 on 12/23/2017.
 */
public class Point <T extends Number> {

    final T x;
    final T y;

    Point(T x, T y) {

        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {

        return "x: " + x + " y: " + y;
    }
}
