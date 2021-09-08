package color;

/**
 * Color is a class for creating color objects, whose fields include the three RGB values (that are passed to the constructor)
 * and the YUV tuple (obtained by conversion).
 */
public class Color {

    private double r,g,b;
    private double[] YUV;

    /**
     *Class constructor specifying the RGB triple values.
     * @param r the red component of the RGB triple.&nbsp; Its value should be comprised bewteen 0 and 1.
     * @param g the green component of the RGB triple.&nbsp; Its value should be comprised bewteen 0 and 1.
     * @param b the blue component of the RGB triple.&nbsp; Its value should be comprised bewteen 0 and 1.
     * @throws InvalidColorException If the RGB values provided to the Color's constructor do not fall within the allowed range (0-1).
     */
    Color (double r, double g, double b) throws InvalidColorException {

        if (
                r < 0 || r > 1 ||
                g < 0 || g > 1 ||
                b < 0 || b > 1
        ) throw new InvalidColorException() {};
        this.r = r;
        this.g = g;
        this.b = b;

        this.YUV = ColorUtil.RGBtoYUV(this.r, this.g, this.b);

    }

    /**
     * Class constructor without parameters initializing the RGB triple to (0,0,0).
     * @throws InvalidColorException If the RGB values provided to the Color's constructor do not fall within the allowed range (0-1).
     */
    Color() throws InvalidColorException {
        this(0,0,0);
    }

    /**
     * Return the value of the red component of the RGB triple.
     * @return the value of the red component of the RGB triple.
     */
    public double getR() {
        return r;
    }

    /**
     *Return the value of the green component of the RGB triple.
     * @return the value of the green component of the RGB triple.
     */
    public double getG() {
        return g;
    }

    /**
     *Return the value of the blue component of the RGB triple.
     * @return the value of the blue component of the RGB triple.
     */
    public double getB() {
        return b;
    }

    /**
     *Return the value of all the components of the RGB triple.
     * @return the value of all the components of the RGB triple.
     */
    public double[] getAll() {return new double[] {r,g,b};}

    /**
     *Return the value of all the components of the YUV triple.
     * @return the value of all the components of the YUV triple.
     */
    public double[] getYUV() {return YUV;}

    /**
     * Returns a string displaying each value of the RGB and YUV triple one by one.&nbsp; Every value is preceded by the component's name followed by a colon.
     * @return a string displaying each value of the RGB and YUV triple one by one.
     */
    public String toString (){
        return " R: " + r + " G: " + g + " B: " + b +"\nY: " + YUV[0] + " U: " + YUV[1] + " V: " + YUV[2] + "\n\n";
    }

    /**
     *Returns a string displaying each value of the RGB and YUV triple one by one.&nbsp; The values are encapsulated in square
     * brackets and separated by commas.
     * @return a string displaying each value of the RGB and YUV triple one by one.
     */
    public String getStringForImage() {
        return "[" + r + "," + g + "," + b + "," + YUV[0] + "," + YUV[1] + "," + YUV[2] + "]";
    }
}