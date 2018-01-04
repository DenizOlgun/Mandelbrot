package sample;

import java.util.function.*;

import static sample.DirectionalPoint.*;
import static java.lang.Math.*;

/**
 * Created by Pat111 on 12/24/2017.
 */
public class Rectangle {
    //Rectangle is a bounding rectangle with a center.  The Rectangle is bounded by two points:  the superior and the inferior corners

    //the Rectangle corner with maximized x and y values
    private DirectionalPoint superiorCorner;

    //the Rectangle corner with minimized x and y values
    private DirectionalPoint inferiorCorner;

    //the coordinate center of the Rectangle, which is not necessarily at the Rectangle's center of mass.
    private Point center;

    //if no center is provided, it defaults to the center of mass of the rectangle.
    public Rectangle(DirectionalPoint firstCorner, DirectionalPoint secondCorner) {

        this(firstCorner, secondCorner, new Point((firstCorner.getX() + secondCorner.getX()) / 2d, (firstCorner.getY() + secondCorner.getY()) / 2d));
    }

    //TODO:  more rigorous testing, to ensure that the constructor works as intended.
    public Rectangle(DirectionalPoint firstCorner, DirectionalPoint secondCorner, Point center) {

        if(firstCorner.getX() == secondCorner.getX() || firstCorner.getY() == secondCorner.getY()) throw new IllegalArgumentException("Neither the Rectangle's x nor y lengths can be 0.");

        //if true, then neither the firstCorner nor the secondCorner can be inferior nor superior;  we switch to the other 2-corner pair of the rectangle
        //this branch also checks that the firstCorner and secondCorner are directionally equal
        if(!(compareHorizontal.compare(firstCorner, secondCorner) > 0 && compareVertical.compare(firstCorner, secondCorner) > 0) ||
           !(compareHorizontal.compare(secondCorner, firstCorner) > 0) && compareVertical.compare(secondCorner, firstCorner) > 0) {

            //stores the firstCorner's location for secondCorner's constructor.
            double temp = firstCorner.getY();

            firstCorner = new DirectionalPoint(new Point(firstCorner.getX(), secondCorner.getY()), firstCorner);
            secondCorner = new DirectionalPoint(new Point(secondCorner.getX(), temp), secondCorner);
        }

        this.superiorCorner = compareHorizontal.compare(firstCorner, secondCorner) > 0 ? firstCorner : secondCorner;
        this.inferiorCorner = compareHorizontal.compare(firstCorner, secondCorner) < 0 ? firstCorner : secondCorner;

        this.center = center;
    }

    //returns the centroid of this
    public Point trueCenter() {

        return new Point((superiorCorner.getX() + inferiorCorner.getX())/2d, (superiorCorner.getY() + inferiorCorner.getY())/2d);
    }

    public double horizontalLength() {

        return abs(superiorCorner.getX() - inferiorCorner.getX());
    }

    public double verticalLength() {

        return abs(superiorCorner.getY() - inferiorCorner.getY());
    }

    //getters
    public DirectionalPoint getSuperiorCorner() {

        return superiorCorner;
    }

    public DirectionalPoint getInferiorCorner() {

        return inferiorCorner;
    }

    public Point getCenter() {

        return center;
    }

    //returns an UnaryOperator that maps Points from this to r, respecting directions
    public UnaryOperator<Point> scale(Rectangle r) {


        //TODO:  make compatible with X_INC == LEFT and Y_INC == DOWN; right now, it only works if they're changed to RIGHT and UP, respectively
        return p -> {

            //accounting for differences in the INC
            if(!superiorCorner.getX_INC().equals(r.superiorCorner.getX_INC())) p.reflectX(this.trueCenter());
            if(!superiorCorner.getY_INC().equals(r.superiorCorner.getY_INC())) p.reflectY(this.trueCenter());

            //accounting for differences in dimensions
            p = p.setX((p.getX() - center.getX()) * r.horizontalLength()/horizontalLength());
            p = p.setY((p.getY() - center.getY()) * r.verticalLength()/verticalLength());

            //accounting for offsets of the center
            p = p.setX((p.getX() - r.center.getX()));
            p = p.setY((p.getY() - r.center.getY()));

            return p;
        };
    }

    public String toString() {

        return "Superior Corner: " + superiorCorner + "\n" +
                "Inferior Corner: " + inferiorCorner + "\n";
    }
}
