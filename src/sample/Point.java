package sample;

import java.util.Comparator;
import static java.util.Comparator.*;

/**
 * Created by Pat111 on 12/23/2017.
 */
public class Point {
//Point represents a location in 2D space, without any directional heading

    private double x;
    private double y;

    Point(double x, double y) {

        this.x = x;
        this.y = y;
    }

    //getters
    public double getX() {

        return x;
    }

    public double getY() {

        return y;
    }

    //setters
    public Point setX(double x) {

        return new Point(x, this.y);
    }

    public Point setY(double y) {

        return new Point(this.x, y);
    }

    //reflects this over the reflector, considering only the x coordinate
    public Point reflectX(Point reflector) {

        return new Point(2*reflector.x - x, this.y);
    }

    //reflects this over the reflector, considering only the y coordinate
    public Point reflectY(Point reflector) {

        return new Point(this.x, 2*reflector.y - y);
    }

    //reflects this over the reflector (i.e. returns the point which is collinear and equidistant from this and the reflector)
    public Point reflect(Point reflector) {

        return new Point(
                2*reflector.x - x,
                2*reflector.y - y
        );
    }

    public static Comparator<Point> compareHorizontal = comparing(Point::getX);
    public static Comparator<Point> compareVertical = comparing(Point::getY);

    @Override
    public String toString() {

        return "x: " + x + " y: " + y;
    }
}
