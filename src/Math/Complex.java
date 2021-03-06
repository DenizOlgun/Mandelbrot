package Math;

/**
 * Created by Pat111 on 12/23/2017.
 */
public class Complex {

    public static final Complex ONE;
    public static final Imaginary I;

    static {

        ONE = new Complex(1);
        I = new Imaginary(1);
    }

    private double re;
    private double im;

    public Complex(double re) {

        this(re, 0);
    }

    public Complex(double re, double im) {

        this.re = re;
        this.im = im;
    }

    //getters
    public double getRe() {return re;}
    public double getIm() {return im;}

    //basic operators

    //returns the sum of two Complex, by adding like terms
    public Complex add(Complex c) {

        return new Complex(
                this.re + c.re,
                this.im + c.im
        );
    }

    //returns the difference of two Complex, by subtracting like terms
    public Complex subtract(Complex c) {

        return new Complex(
                this.re - c.re,
                this.im - c.im
        );
    }

    //returns the product of two Complex, using the Distributive Property
    public Complex multiply(Complex c) {

        return new Complex(
                (this.re*c.re - this.im*c.im),
                (this.im*c.re + this.re*c.im)
        );
    }

    //returns the quotient of a Complex and a double, using the Distributive Property
    public Complex divide(double d) {

        return new Complex(
                this.re/d,
                this.im/d
        );
    }

    //returns the quotient of two Complex by factoring in the divisor's complex conjugate, then dividing through, using the Distributive Property
    public Complex divide(Complex c) {

        return this.multiply(c.conj()).divide(c.multiply(c.conj()).toDouble());
    }

    //returns the absolute value of a Complex by using the Distance Formula (d = sqrt(deltaX^2 + deltaY^2))
    public double abs() {

        return Math.sqrt(re*re + im*im);
    }

    //convenience operators

    //returns the product of a Complex with itself, i.e. the square of that Complex
    public Complex squared() {

        return this.multiply(this);
    }

    //returns the reciprocal of a Complex
    public Complex recip() {

        return ONE.divide(this);
    }

    //returns the Complex conjugate of a Complex
    public Complex conj() {

        return new Complex(
                this.re,
                this.im*-1
        );
    }

    //type converters

    public double toDouble() {

        if(im != 0) throw new IllegalArgumentException("Complex numbers may only be converted to reals if the imaginary part = 0. \nHere, the imaginary value is " + im);
        else return re;
    }

    public int toInt() {

        return (int) toDouble();
    }

    public Imaginary toImaginary() {

        if(re != 0) throw new IllegalArgumentException("Complex numbers may only be converted to imaginary if the real part = 0. \nHere, the real value is " + re);
        else return new Imaginary(im);
    }

    @Override
    public String toString() {

        return re + " + " + im + "i";
    }
}