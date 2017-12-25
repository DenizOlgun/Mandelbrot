package sample;

/**
 * Created by Pat111 on 12/23/2017.
 */
public class Point {
//Point represents a location in 2D space, without any directional heading

    final double x;
    final double y;

    Point(double x, double y) {

        this.x = x;
        this.y = y;
    }

    //reflects this over the reflector (i.e. returns the point which is collinear and equidistant from this and the reflector)
    public Point reflect(Point reflector) {
//TODO:  test that reflect works as desired, also in DirectionalPoint
        return new Point(2*reflector.x - this.x, 2*reflector.y - this.y);
    }

    @Override
    public String toString() {

        return "x: " + x + " y: " + y;
    }
}
