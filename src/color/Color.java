package color;

public class Color {

    private int r,g,b;

    Color (int r, int g, int b) throws InvalidColorException {

        if (
                r < 0 || r > 255 ||
                g < 0 || g > 255 ||
                b < 0 || b > 255
        ) throw new InvalidColorException() {};


        this.r = r;
        this.g = g;
        this.b = b;
    }

    Color() throws InvalidColorException{
        this(0,0,0);
    }

    public int getR() {
        return r;
    }

    public int getG() {
        return g;
    }

    public int getB() {
        return b;
    }

    public String toString (){
        return "R: " + r + " G: " + g + " B: " + b;
    }
}