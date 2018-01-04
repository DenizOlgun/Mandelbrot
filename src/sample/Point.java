package sample;

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
    public void setX(double x) {

        this.x = x;
    }

    public void setY(double y) {

        this.y = y;
    }

    //reflects this over the reflector, considering only the x coordinate
    public void reflectX(Point reflector) {

        this.setX(2*reflector.x - x);
    }

    //reflects this over the reflector, considering only the y coordinate
    public void reflectY(Point reflector) {

        this.setY(2*reflector.y - y);
    }

    //reflects this over the reflector (i.e. returns the point which is collinear and equidistant from this and the reflector)
    public void reflect(Point reflector) {

        this.setX(2*reflector.x - x);
        this.setY(2*reflector.y - y);
    }

    @Override
    public String toString() {

        return "x: " + x + " y: " + y;
    }
}
