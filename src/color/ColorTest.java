package color;

public class ColorTest {

    public static void main(String[] args) throws InvalidColorException {

        Colors c = new Colors();
        c.createCatForImage(20,3);
        System.out.println(c);
    }
}
