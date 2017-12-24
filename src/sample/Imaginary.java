package sample;

/**
 * Created by Pat111 on 12/23/2017.
 */
public class Imaginary extends Complex {

    Imaginary(double im) {

        super(0, im);
    }

    public Complex toComplex() {

        return new Complex(0, getIm());
    }
}
