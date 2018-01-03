package sample;

import java.util.Comparator;

import static java.util.Comparator.*;
import static sample.DirectionalPoint.Direction.*;

/**
 * Created by Pat111 on 12/24/2017.
 */
public class DirectionalPoint {
//DirectionalPoint represents a location in 2D space, with directional heading

    private Point location;

    //The directions in which the X and Y values increase, respectively
    private Direction X_INC;
    private Direction Y_INC;

    DirectionalPoint(Point location) {

        this(location, Direction.RIGHT, Direction.UP);
    }

    DirectionalPoint(Point location, Direction X_INC, Direction Y_INC) {

        this.location = location;

        if(X_INC == Direction.DOWN || X_INC == Direction.UP) throw new IllegalArgumentException("The X axis is horizontal; it may not increase in the downwards or upwards directions.");
        if(Y_INC == Direction.LEFT || Y_INC == Direction.RIGHT) throw new IllegalArgumentException("The Y axis is vertical; it may not increase in the leftwards or rightwards directions.");

        this.X_INC = X_INC;
        this.Y_INC = Y_INC;

    }

    public DirectionalPoint(Point newLocation, DirectionalPoint d) {

        this.location = newLocation;
        this.X_INC = d.X_INC;
        this.Y_INC = d.Y_INC;
    }

    //getters
    public Point getLocation() {

        return location;
    }

    public double getX() {

        return location.getX();
    }

    public double getY() {

        return location.getY();
    }

    public Direction getX_INC() {

        return X_INC;
    }

    public Direction getY_INC() {

        return Y_INC;
    }


    //Two DirectionalPoints are directionally equal iff their X_INC and Y_INC are equivalent
    public boolean directionallyEquals(DirectionalPoint d) {

        return this.X_INC == d.X_INC && this.Y_INC == d.Y_INC;
    }

    //Two Points are horizontally equal iff they both exist on one horizontal line
    public boolean horizontallyEquals(DirectionalPoint d) {

        return this.location.getY() == d.location.getY();
    }

    //Two Points are vertiacally equal iff they both exist on one vertical line
    public boolean verticallyEquals(DirectionalPoint d) {

        return this.location.getX() == d.location.getX();
    }

    //Two Points are locationally equal iff they both exist in the same location
    public boolean locationallyEquals(DirectionalPoint d) {

        return this.location.equals(d.location);
    }

    //reflects this over the reflector (i.e. returns the point which is collinear and equidistant from this and the reflector)
    public DirectionalPoint reflect(Point reflector) {
//TODO:  test that reflect works as desired, also in Point
        return new DirectionalPoint(new Point(2*reflector.getX() - this.location.getX(), 2*reflector.getY() - this.location.getY()), this);
    }

    static Comparator<DirectionalPoint> compareLeft = comparing(DirectionalPoint::getX, reverseOrder());
    static Comparator<DirectionalPoint> compareRight = comparing(DirectionalPoint::getX, naturalOrder());

    static Comparator<DirectionalPoint> compareDown = comparing(DirectionalPoint::getY, reverseOrder());
    static Comparator<DirectionalPoint> compareUp = comparing(DirectionalPoint::getY, naturalOrder());

    static Comparator<DirectionalPoint> compareHorizontal = (d1, d2) -> {

        assert d1.directionallyEquals(d2) : "The points are not directionally equal";
        return d1.getX_INC() == LEFT ? compareLeft.compare(d1,d2) :  compareRight.compare(d1,d2);
    };

    static Comparator<DirectionalPoint> compareVertical = (d1, d2) -> {

        assert d1.directionallyEquals(d2) : "The points are not directionally equal";
        return d1.getY_INC() == DOWN ? compareDown.compare(d1, d2) : compareUp.compare(d1, d2);
    };

    public String toString() {

        return "Location: " + location + "\n" +
                "X_INC: " + X_INC + "\n" +
                "Y_INC: " + Y_INC;
    }

    public enum Direction {

        LEFT,
        RIGHT,
        DOWN,
        UP;

        public String toString() {

            switch(this) {

                case LEFT: return "LEFT";
                case RIGHT: return "RIGHT";
                case DOWN: return "DOWN";
                case UP: return "UP";
            }

            throw new IllegalArgumentException("Direction's value may only be LEFT, RIGHT, DOWN, or UP.");
        }
    }
}
