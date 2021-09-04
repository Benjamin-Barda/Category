package color;

public class ColorUtil {

    //Constant from https://it.wikipedia.org/wiki/YUV

    private final static double Wr = 0.299;
    private final static double Wg = 0.587;
    private final static double Wb = 0.114;

    private final static double Umax = 0.436;
    private final static double Vmax = 0.615;

    public static double[] RGBtoYUV (double R, double G, double B) {


        double Y = (Wr*R) + (Wg*G) + (Wb*B);
        double U = Umax * ((B - Y)/(1 - Wb));
        double V = Vmax * ((R - Y)/(1 - Wr));

        return new double[]{Y, U, V};

    }

    public static double[] YUVtoRGB (double y, double u, double v) {

        double R = y + v * ((1-Wr)/Vmax);
        double G = y - u * ((Wb * (1-Wb))/(Umax * Wg)) - v * ((Wr * (1-Wr))/(Vmax*Wg));
        double B = y + u * ((1 - Wb)/Umax);

        return new double[] {R, G, B};

    }

    public static double[] getMidPoint (double u1, double v1, double u2, double v2) {

        double u3 = (u1 + u2) / 2;
        double v3 = (v1 + v2) / 2;
         return new double[] {u3,v3};

    }


    public static double[] getEndPoint (double uX, double vX, double u3, double v3 ) {

        double uY = (2 * u3) - uX;
        double vY = (2 * v3) - vX;
        return new double[] {uY, vY};

    }

}
