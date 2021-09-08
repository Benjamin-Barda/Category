package color;
/**
 *Provides some useful methods to work with colors and
 * <ul>
 * <li>switch their components between the RGB and the YUV encoding system through the use of specific formulas and constants,
 * <li>find the average between two colors,
 * <li>find a color given another color and their average.
 * </ul>
 *
 * <p>
 *Formulas for the conversion:<br>
 *<br>
 *R = Y + V * ((1-Wr)/Vmax);<br>
 *G = Y - U * ((Wb * (1-Wb))/(Umax * Wg)) - v * ((Wr * (1-Wr))/(Vmax*Wg));<br>
 *B = Y + U * ((1 - Wb)/Umax);<br>
 *<br>
 *(Wr, Wg, Wb, Vmax, Umax constants)
 * </p>
 */
public class ColorUtil {

    //Constant from https://it.wikipedia.org/wiki/YUV

    private final static double Wr = 0.299;
    private final static double Wg = 0.587;
    private final static double Wb = 0.114;

    private final static double Umax = 0.436;
    private final static double Vmax = 0.615;


    /**
     *Converts the color's RGB components to YUV components through the use of specific formulas and constants.
     * @param R the red component of the color's RGB triple.
     * @param G the green component of the color's RGB triple.
     * @param B the blue component of the color's RGB triple.
     * @return the array of YUV components.
     */
    public static double[] RGBtoYUV (double R, double G, double B) {


        double Y = (Wr*R) + (Wg*G) + (Wb*B);
        double U = Umax * ((B - Y)/(1 - Wb));
        double V = Vmax * ((R - Y)/(1 - Wr));

        return new double[]{Y, U, V};

    }

    /**
     *Converts the color's YUV components to RGB components through the use of specific formulas and constants.
     * @param y the luminance (Y) component of the color's YUV triple.
     * @param u the blueness (U) component of the color's YUV triple.
     * @param v the redness (V) component of the color's YUV triple.
     * @return the array of RGB components.
     */
    public static double[] YUVtoRGB (double y, double u, double v) {

        double R = y + v * ((1-Wr)/Vmax);
        double G = y - u * ((Wb * (1-Wb))/(Umax * Wg)) - v * ((Wr * (1-Wr))/(Vmax*Wg));
        double B = y + u * ((1 - Wb)/Umax);

        return new double[] {R, G, B};

    }

    /**
     *Finds the middle point between two points on the chrominance plane UV given the coordinates.
     * @param u1 blue (U) component of the first point in the chrominance plane.
     * @param v1 red (V) component of the first point in the chrominance plane.
     * @param u2 blue (U) component of the second point in the chrominance plane.
     * @param v2 red (V) component of the second point in the chrominance plane.
     * @return the array with blue (U) and red (V) components of the middle point between two points on the chrominance plane UV.
     */
    public static double[] getMidPoint (double u1, double v1, double u2, double v2) {

        double u3 = (u1 + u2) / 2;
        double v3 = (v1 + v2) / 2;
         return new double[] {u3,v3};

    }

    /**
     *Finds the endpoint given a starting point and the middle point between them on the chrominance plane UV.
     * @param uX blue (U) component of the starting point in the chrominance plane.
     * @param vX red (V) component of the starting point in the chrominance plane.
     * @param u3 blue (U) component of the middle point in the chrominance plane.
     * @param v3 red (V) component of the middle point in the chrominance plane.
     * @return the array with blue (U) and red (V) components of the endpoint given a point and the middle point between them on the chrominance plane UV.
     */
    public static double[] getEndPoint (double uX, double vX, double u3, double v3 ) {

        double uY = (2 * u3) - uX;
        double vY = (2 * v3) - vX;
        return new double[] {uY, vY};

    }

}
