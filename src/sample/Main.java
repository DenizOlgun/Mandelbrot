package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;
import javafx.scene.image.WritableImage;

import static java.lang.Math.*;

import java.util.function.*;

public class Main extends Application {

    static double width;
    static double height;

    @Override
    public void start(Stage primaryStage) {
        // Create ImageView
        ImageView imageView = new ImageView();

        // Create WritableImage
        WritableImage wImage = new WritableImage(1500, 1000);
        PixelWriter pixelWriter = wImage.getPixelWriter();
        width = wImage.getWidth();
        height = wImage.getHeight();

        //TODO: Change the methodology to use a pixel, and directly output its color.  Store all of these pairs in a Map.  Then, color the WritableImage using this Map.
        for(int readX=0; readX<wImage.getWidth(); readX++) {

            for(int readY=0; readY<wImage.getHeight(); readY++) {

                Point<Double> scaledPoint = coordinateMapper.apply(new Point<>(readX, readY));
                pixelWriter.setColor(readX, readY,
                        getColor(Color.BLACK,
                                mandelbrot.apply(coordinateMapper.apply(new Point<Integer>(readX, readY))),
                                (Integer iterations) -> iterations == 0,
                                (Integer iterations) -> new Color(((double) iterations/MAX_ITERATIONS), 0, 0, 0.75),
                                (Color c) -> new Color(c.getRed(), c.getBlue(), c.getGreen(), c.getOpacity())));
            }
        }

        // Display image on screen
        imageView.setImage(wImage);
        StackPane root = new StackPane();
        root.getChildren().add(imageView);
        Scene scene = new Scene(root, wImage.getWidth(), wImage.getHeight());
        primaryStage.setTitle("Mandelbrot Set");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    static Function<Point<Integer>, Point<Double>> coordinateMapper = point -> new Point<>(

            (point.x - (2d/3)*width)*3/width,
            (point.y - (1d/2)*(height))*-2/height
    );


    static final int MAX_ITERATIONS = 100;

    static Function<Point<Double>, Integer> mandelbrot =
    //returns 0 if the given points are within the mandelbrot set
    //if not, returns the number of steps required to prove its exclusion

    point ->
        //maps x and y coordinates onto the imaginary plane to form a Complex number
        //and checks if this Complex lies withing the mandelbrot set
    {

        Complex c = new Complex(point.x, point.y);
        Complex determinant = new Complex(0, 0 );
        //Tests with the classic mandelbrot conditions, that iterating det^2 + c -> det does not diverge,
        //the process ends if c takes more than MAX_ITERATIONS iterations to be proven divergent

        for(int iterations = 0; iterations <= MAX_ITERATIONS; iterations++) {

            if(determinant.abs() > 2) return iterations;
            determinant = determinant.squared().add(c);
        }

        return 0;
    };

    //the colorSupplier outputs a value based on the determinant
    public static <T extends Number> Color getColor(T determinant, Function<T, Color> colorSupplier) {

        return colorSupplier.apply(determinant);
    }

    //supplies a default value for the colorPostProcessor -- the identity function
    public static <T extends Number> Color getColor(Color standard, T determinant, Predicate<T> condition, Function<T, Color> colorSupplier) {

        return getColor(standard, determinant, condition, colorSupplier, UnaryOperator.identity());
    }

    //checks whether the determinant meets the set condition.
    //if it doesn't, the default value, standard, is returned
    //if it does, the colorSupplier supplies an initial value, based on the determinant, and this is further modified by the colorPostProcessor
    public static <T extends Number> Color getColor(Color standard, T determinant, Predicate<T> condition, Function<T, Color> colorSupplier, UnaryOperator<Color> colorPostProcessor) {

        return condition.test(determinant) ?

                standard
                :
                colorPostProcessor.apply(colorSupplier.apply(determinant));
    }

    public static void main(String[] args) {

        launch(args);
    }
}