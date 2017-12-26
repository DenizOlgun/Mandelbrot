package sample;

import java.util.Comparator;
import java.util.function.UnaryOperator;

/**
 * Created by Pat111 on 12/24/2017.
 */
public class Rectangle {
    //Rectangle is a bounding rectangle with a center.  The Rectangle is bounded by two points:  the superior and the inferior corners

    //the Rectangle corner with maximized x and y values
    DirectionalPoint superiorCorner;

    //the Rectangle corner with minimized x and y values
    DirectionalPoint inferiorCorner;

    //the coordinate center of the Rectangle, which is not necessarily at the Rectangle's center of mass.
    Point center;

    //if no center is provided, it defaults to the center of mass of the rectangle.
    public Rectangle(DirectionalPoint firstCorner, DirectionalPoint secondCorner) {

        this(firstCorner, secondCorner, new Point((firstCorner.location.x + secondCorner.location.x) / 2d, (firstCorner.location.y + secondCorner.location.y) / 2d));
    }

    //TODO:  more rigorous testing, to ensure that the constructor works as intended.
    public Rectangle(DirectionalPoint firstCorner, DirectionalPoint secondCorner, Point center) {

        if(firstCorner.horizontallyEquals(secondCorner) || firstCorner.verticallyEquals(secondCorner)) throw new IllegalArgumentException("Neither the Rectangle's x nor y lengths can be 0.");

        //if true, then neither the firstCorner nor the secondCorner can be inferior nor superior;  we switch to the other 2-corner pair of the rectangle
        //this branch also checks that the firstCorner and secondCorner are directionally equal
        if(!(compareHorizontal.compare(firstCorner, secondCorner) > 0 && compareVertical.compare(firstCorner, secondCorner) > 0) ||
           !(compareHorizontal.compare(secondCorner, firstCorner) > 0) && compareVertical.compare(secondCorner, firstCorner) > 0) {

            //stores the firstCorner's location for secondCorner's constructor.
            double temp = firstCorner.location.y;

            firstCorner = new DirectionalPoint(new Point(firstCorner.location.x, secondCorner.location.y), firstCorner);
            secondCorner = new DirectionalPoint(new Point(secondCorner.location.x, temp), secondCorner);
        }

        this.superiorCorner = compareHorizontal.compare(firstCorner, secondCorner) > 0 ? firstCorner : secondCorner;
        this.inferiorCorner = compareHorizontal.compare(firstCorner, secondCorner) < 0 ? firstCorner : secondCorner;

        this.center = center;
    }

    public double horizontalLength() {

        return superiorCorner.location.x - inferiorCorner.location.x;
    }

    public double verticalLength() {

        return superiorCorner.location.y - inferiorCorner.location.y;
    }

    static Comparator<DirectionalPoint> compareHorizontal = (d1, d2) -> {

        if (!d1.directionallyEquals(d2))
            throw new IllegalArgumentException("The arguments are not directionally equal; they can't be compared" + d1 + "\n" + d2);
        return (int) Math.signum(d1.X_INC == DirectionalPoint.Direction.LEFT ? d2.location.x - d1.location.x : d1.location.x - d2.location.x);
    };

    static Comparator<DirectionalPoint> compareVertical = (d1, d2) -> {

        if (!d1.directionallyEquals(d2))
            throw new IllegalArgumentException("The arguments are not directionally equal; they can't be compared");
        return (int) Math.signum(d1.Y_INC == DirectionalPoint.Direction.DOWN ? d2.location.y - d1.location.y : d1.location.x - d2.location.y);
    };

    //returns an UnaryOperator that maps Points from this to r, respecting directions
    public UnaryOperator<Point> getCoordinateMapper(Rectangle r) {

        return p -> {

            //TODO:  provide a functioning implementation
            return p;
        };
    }

    public String toString() {

        return "Superior Corner: " + superiorCorner + "\n" +
                "Inferior Corner: " + inferiorCorner + "\n";
    }
}
