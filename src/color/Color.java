package color;

import java.util.List;

public class Color {

    private double r, g, b;

    Color (double r, double g, double b) {

        // Checking precondition
        if (r > 255) r = 255;
        if (g > 255) g = 255;
        if (b > 255) b = 255;

        if (r < 0) r = 0;
        if (g < 0) g = 0;
        if (b < 0) b = 0;
        // \precondition

        this.r = r;
        this.g = g;
        this.b = b;
    }

    Color () {
        this(0,0,0);
    }

    // Getter and Setter
    public void setR(double r){
        this.r = r;
    }

    public void setG(double g) {
        this.g = g;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getR() {
        return r;
    }

    public double getG() {
        return g;
    }

    public double getB() {
        return b;
    }

    public double[] getColor() {
        return new double[] {r,g,b};
    }
    // \Getter and Setter


    public String toString() {
        return "(" + r +", " +g+", "+b+")";
    }


}
