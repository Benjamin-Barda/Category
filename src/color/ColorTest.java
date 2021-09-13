package color;

import categoryBase.IllegalCompositionException;

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
     * @throws IllegalCompositionException
     */
    public static void main(String[] args) throws InvalidColorException, IllegalCompositionException {

        Colors c = new Colors();

        Colors.Obj o1 = c.new Obj(new Color(0.12, 0.3, 0.0), 0);
        Colors.Obj o2 = c.new Obj(new Color(0.12, 0.3, 0.1), 1);
        Colors.Obj o3 = c.new Obj(new Color(0.12, 0.3, 0.2), 2);


        Colors.Morph m1 = c.new Morph(o1, o2);
        Colors.Morph m2 = c.new Morph(o2, o3);

        c.addObject(o1, o1.getId());
        c.addObject(o2, o2.getId());
        c.addObject(o3, o3.getId());

        c.addMorph(m1, 0);
        c.addMorph(m2, 1);


        Colors.Morph m12 = c.compose(m1, m2);
        c.addMorph(m12, 2);

        //System.out.println(c);

        c.saveToFile("CompositionVisualization");
    }
}
