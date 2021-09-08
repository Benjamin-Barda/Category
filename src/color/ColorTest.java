package color;

/**
 *Creates a random {@link Colors} category, prints every object's and morphism's YUV and RGB component and saves it in a file
 * using methods provided by class Colors.
 */
public class ColorTest {

    /**
     *Creates a random {@link Colors} category, prints:
     *<ul>
     *<li>a string displaying each value of the RGB and YUV triple of the field color of each object of the category,
     *<li>the identification number of the source and target of each morphism of the category,
     *<li>a string displaying each value of the RGB and YUV triple of the average color field of each morphism of the category
     *</ul>
     * (one by one - the values are encapsulated in square brackets and separated by commas) and saves it in a file, using methods provided by class Colors.
     * @param args
     * @throws InvalidColorException If the RGB values provided to the Color's constructor do not fall within the allowed range (0-1).
     */
    public static void main(String[] args) throws InvalidColorException {

        Colors c = new Colors();
        c.createCatForImage(20,3);
        c.printDEBUG();
        c.saveToFile("prova");
    }
}
