package Geometry;

import java.util.Comparator;

import static java.util.Comparator.*;

import static Geometry.DirectionalPoint.Direction.*;

/**
 * Created by Pat111 on 12/24/2017.
 */
public class DirectionalPoint extends Point {
//DirectionalPoint represents a location in 2D space, with directional heading

    //The directions in which the X and Y values increase, respectively
    private Heading heading;

    public DirectionalPoint(Point location) {

        this(location, new Heading(RIGHT, UP));
    }

    public DirectionalPoint(Point location, Heading heading) {

        super(location.getX(), location.getY());

        if(heading.getX_INC() == Direction.DOWN || heading.getX_INC() == Direction.UP) throw new IllegalArgumentException("The X axis is horizontal; it may not increase in the downwards or upwards directions.");
        if(heading.getY_INC() == Direction.LEFT || heading.getY_INC() == Direction.RIGHT) throw new IllegalArgumentException("The Y axis is vertical; it may not increase in the leftwards or rightwards directions.");

        this.heading = heading;
    }

    DirectionalPoint(Point newLocation, DirectionalPoint d) {

        super(newLocation.getX(), newLocation.getY());

        this.heading = d.heading;
    }

    //getters
    public Point getLocation() {

        return new Point(super.getX(), super.getY());
    }

    public Direction getX_INC() {

        return heading.getX_INC();
    }

    public Direction getY_INC() {

        return heading.getY_INC();
    }

    //Two DirectionalPoints are directionally equal iff their getX_INC() and heading.getY_INC are equivalent
    public boolean directionallyEquals(DirectionalPoint d) {

        return heading.equals(d.heading);
    }

    private static Comparator<DirectionalPoint> compareLeft = comparing(DirectionalPoint::getX, reverseOrder());
    private static Comparator<DirectionalPoint> compareRight = comparing(DirectionalPoint::getX, naturalOrder());
    private static Comparator<DirectionalPoint> compareDown = comparing(DirectionalPoint::getY, reverseOrder());
    private static Comparator<DirectionalPoint> compareUp = comparing(DirectionalPoint::getY, naturalOrder());

    public static Comparator<DirectionalPoint> compareHorizontal = (d1, d2) -> {

        assert d1.directionallyEquals(d2) : "The points are not directionally equal";
        return d1.getX_INC() == LEFT ? compareLeft.compare(d1, d2) : compareRight.compare(d1, d2);
    };

    public static Comparator<DirectionalPoint> compareVertical = (d1, d2) -> {

        assert d1.directionallyEquals(d2) : "The points are not directionally equal";
        return d1.getY_INC() == DOWN ? compareDown.compare(d1, d2) : compareUp.compare(d1, d2);
    };

    @Override
    public String toString() {

        return "Location: " + new Point(getX(), getY()) + "\n" +
               "X_INC(): " + heading.getX_INC() + "\n" +
               "heading.getY_INC: " + heading.getY_INC();
    }

    public enum Direction {

        LEFT,
        RIGHT,
        DOWN,
        UP;

        @Override
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
