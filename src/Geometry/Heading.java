package Geometry;

import Geometry.DirectionalPoint.Direction;

/**
 * Created by Pat111 on 1/4/2018.
 */
public class Heading {

    private Direction X_INC;
    private Direction Y_INC;

    public Heading(Direction X_INC, Direction Y_INC) {

        this.X_INC = X_INC;
        this.Y_INC = Y_INC;
    }

    //getters
    public Direction getX_INC() {

        return X_INC;
    }

    public Direction getY_INC() {

        return Y_INC;
    }
}
