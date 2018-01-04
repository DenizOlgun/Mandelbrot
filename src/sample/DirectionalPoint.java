package sample;

import java.util.Comparator;

import static java.util.Comparator.*;
import static sample.DirectionalPoint.Direction.*;

/**
 * Created by Pat111 on 12/24/2017.
 */
public class DirectionalPoint extends Point {
//DirectionalPoint represents a location in 2D space, with directional heading

    private Point location;

    //The directions in which the X and Y values increase, respectively
    private Direction X_INC;
    private Direction Y_INC;

    DirectionalPoint(Point location) {

        this(location, Direction.RIGHT, Direction.UP);
    }

    DirectionalPoint(Point location, Direction X_INC, Direction Y_INC) {

        super(location.getX(), location.getY());

        if(X_INC == Direction.DOWN || X_INC == Direction.UP) throw new IllegalArgumentException("The X axis is horizontal; it may not increase in the downwards or upwards directions.");
        if(Y_INC == Direction.LEFT || Y_INC == Direction.RIGHT) throw new IllegalArgumentException("The Y axis is vertical; it may not increase in the leftwards or rightwards directions.");

        this.X_INC = X_INC;
        this.Y_INC = Y_INC;

    }

    DirectionalPoint(Point newLocation, DirectionalPoint d) {

        super(newLocation.getX(), newLocation.getY());

        this.X_INC = d.X_INC;
        this.Y_INC = d.Y_INC;
    }

    //getters
    public Point getLocation() {

        return location;
    }

    public Direction getX_INC() {

        return X_INC;
    }

    public Direction getY_INC() {

        return Y_INC;
    }

    //Two DirectionalPoints are directionally equal iff their X_INC and Y_INC are equivalent
    public boolean directionallyEquals(DirectionalPoint d) {

        return X_INC == d.X_INC && Y_INC == d.Y_INC;
    }

    private static Comparator<DirectionalPoint> compareLeft = comparing(DirectionalPoint::getX, reverseOrder());
    private static Comparator<DirectionalPoint> compareRight = comparing(DirectionalPoint::getX, naturalOrder());
    private static Comparator<DirectionalPoint> compareDown = comparing(DirectionalPoint::getY, reverseOrder());
    private static Comparator<DirectionalPoint> compareUp = comparing(DirectionalPoint::getY, naturalOrder());

    static Comparator<DirectionalPoint> compareHorizontal = (d1, d2) -> {

        assert d1.directionallyEquals(d2) : "The points are not directionally equal";
        return d1.getX_INC() == LEFT ? compareLeft.compare(d1, d2) : compareRight.compare(d1, d2);
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
