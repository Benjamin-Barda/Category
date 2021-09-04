package color;

public class Color {

    private double r,g,b;
    private double[] YUV;

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

    Color() throws InvalidColorException{
        this(0,0,0);
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

    public double[] getAll() {return new double[] {r,g,b};}

    public double[] getYUV() {return YUV;}

    public String toString (){
        return " R: " + r + " G: " + g + " B: " + b +"\nY: " + YUV[0] + " U: " + YUV[1] + " V: " + YUV[2] + "\n\n";
    }

    public String getStringForImage() {
        return "[" + r + "," + g + "," + b + "," + YUV[0] + "," + YUV[1] + "," + YUV[2] + "]";
    }
}