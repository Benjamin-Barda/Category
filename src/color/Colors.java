package color;

import categoryBase.Category;
import categoryBase.IllegalCompositionException;
import java.util.HashMap;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;

/**
 *Implementation of the generic interface {@link Category} instantiated with the class {@link Color}.&nbsp;
 * Provides the tools to create a category in which objects are colors and morphisms between two colors represent the average color.&nbsp;
 */
public class Colors implements Category<Color> {

    private Obj[] objectArray;
    private Morph[] morphArray;

    private HashMap<Integer, Obj> objectList = new HashMap<>();
    private HashMap<Integer, Morph> morphList = new HashMap<>();

    /**
     *
     * @param f the first morphism
     * @param g the second morphism
     * @return a morphism with starting point in the source of the first morphism and ending point in the target of the second
     * @throws IllegalCompositionException If the source of the second morphism doesn't coincide with the target of the first
     */
    @Override
    public Morphism<? extends Object<Color>, ? extends Object<Color>> compose(
            Morphism<? extends Object<Color>, ? extends Object<Color>> f,
            Morphism<? extends Object<Color>, ? extends Object<Color>> g) throws IllegalCompositionException {
        try {
            Morph m = new Morph((Obj)f.getSource(), (Obj)g.getTarget());
            return m;
        } catch(InvalidColorException e) {
            e.printStackTrace();
            return null;
        }

    }



    /**
     * Implementation of interface {@link Category}'s generic interface {@link Object}.&nbsp;
     * Objects are the elements of the category.&nbsp; If the category is viewed as a directed graph, they correspond to the nodes.&nbsp;
     * Both in Java and in category theory, objects are members of a collection.&nbsp; In Java, it is the collection of values
     * associated to a class.
     */
    public class Obj implements Object<Color> {

        Color color;
        Morph identity;
        final int id;

        /**
         * Constructor of the objects {@link Obj} of category {@link Colors}. Requires a color (object of class {@link Color}) and an integer representing the object's identifier.
         * @param color object of class {@link Color}.&nbsp; Its fields include the three RGB values (that are passed to the constructor)
         *              and the YUV tuple (obtained by conversion).
         * @param id identifier of the object.&nbsp; Usually a number not greater than the number of objects in the category.
         * @throws InvalidColorException If the RGB values provided to the Color's constructor do not fall within the allowed range (0-1).
         */
        Obj (Color color, int id) throws InvalidColorException {
            this.color = color;
            this.identity = new Morph(this, this);
            this.id = id;
        }

        /**
         * Returns the field color of this object.&nbsp; color is an object of class {@link Color}.&nbsp; Its fields include the three RGB values (that are passed to the constructor) and the YUV tuple (obtained by conversion).
         * @return the field color of this object.
         */
        @Override
        public Color getObj() {
            return color;
        }

        /** Returns the morphism identity on this object. For every object A there is an arrow which is a unit of composition.&nbsp; This arrow loops from the object to
         * itself.&nbsp; Being a unit of composition means that, when composed with any arrow that either starts at A or ends at
         * A, respectively, it gives back the same arrow.&nbsp; The unit arrow for object A is called <b>identity</b> on A.
         * @return the morphism identity on this object.
         */
        @Override
        public Morph getIdentity() {
            return identity;
        }

        /**
         * Returns the object's identification number.
         * @return the object's identification number.
         */
        public int getId() {
            return id;
        }

        /**
         *Returns a string displaying each value of the RGB and YUV triple of the object's field color one by one.&nbsp; Every value is preceded by the component's name followed by a colon.
         * @return a string displaying each value of the RGB and YUV triple of the object's field color one by one.
         */
        public String toString() {
            return color.toString();
        }

        /**
         *Returns a string displaying each value of the RGB and YUV triple of the object's field color one by one.&nbsp; The values are encapsulated in square
         * brackets and separated by commas.
         * @return a string displaying each value of the RGB and YUV triple of the object's field color one by one.
         */
        public String getStringForImage () {
            return color.getStringForImage();
        }

    }

    /**
     * Implementation of interface {@link Category}'s generic interface {@link Morphism}.&nbsp;
     * An arrow between two objects, the <b>source</b> and the <b>target</b>.&nbsp; Morphisms are "processes", the possible ways to get from one value to another.&nbsp;
     *They can be composed: one can follow a process from an initial value to an intermediate value, and then follow a second process to a final value, and consider the two
     * processes together as a single composite process.
     */
    public class Morph implements Morphism<Obj,Obj> {

        private Obj source, target;
        private int sourceID, targetID;
        private Color missing;

        /**
         *Class constructor. Takes as parameters two objects of type {@link Obj} and
         * initializes the class fields corresponding to:
         * <ul>
         * <li>the objects themselves
         * <li>their identification numbers
         * <li>the average color between those of the two objects, that is going to be used to color the arrow between the two nodes in the graph representation.
         * </ul>
         * @param source the object where the morphism starts
         * @param target the object where the morphism ends
         * @throws InvalidColorException If the RGB values provided to the Color's constructor do not fall within the allowed range (0-1).
         */
        Morph(Obj source, Obj target) throws InvalidColorException {

            this.source = source;
            this.target = target;
            this.sourceID = source.getId();
            this.targetID = target.getId();

            Color c1 = source.getObj();
            Color c2 = target.getObj();

            double[] YUVc1 = ColorUtil.RGBtoYUV(c1.getR(), c1.getG(), c1.getB());
            double[] YUVc2 = ColorUtil.RGBtoYUV(c2.getR(), c2.getG(), c2.getB());

            double[] missingUVcoordinates = ColorUtil.getMidPoint(YUVc1[1], YUVc1[2], YUVc2[1], YUVc2[2]);
            double missingY = (YUVc1[0] + YUVc2[0]) / 2;

            double[] missingRGB = ColorUtil.YUVtoRGB(missingY, missingUVcoordinates[0], missingUVcoordinates[1]);
            this.missing = new Color(missingRGB[0], missingRGB[1], missingRGB[2]);


        }

        /**
         * Returns the object where the morphism starts.
         * @return the source object of the morphism
         */
        @Override
        public Obj getSource() {
            return source;
        }

        /**
         *Returns the object where the morphism ends.
         * @return the target object of the morphism
         */
        @Override
        public Obj getTarget() {
            return target;
        }

        /**
         *Returns the identification number of the source and target object, and a string displaying each value of the RGB and YUV triple of the average color field one by one (the values are encapsulated in square
         *brackets and separated by commas).
         * @return the identification number of the source and target object, and a string displaying each value of the RGB and YUV triple of the average color field one by one.
         */
        @Override
        public String getId() {
            return source.id + ","  + target.id + missing.getStringForImage();
        }

        /**
         *Returns a string displaying each value of the RGB and YUV triple of the source's and target's field color one by one.&nbsp; Every value is preceded by the component's name followed by a colon.&nbsp;
         * It does the same thing on the next line for the average color field.
         * @return a string displaying each value of the RGB and YUV triple of the source's and target's field color and average color one by one.
         */
        public String toString() {
            return " source: " + source.toString() + "target: " + target.toString() + "\n ---" + missing.toString();
        }

        /**
         *Returns the identification number of the source and target object, and a string displaying each value of the RGB and YUV triple of the average color field one by one (the values are encapsulated in square
         *brackets and separated by commas).
         * @return the identification number of the source and target object, and a string displaying each value of the RGB and YUV triple of the average color field one by one.
         */
        public String getStringForImage () {
            return getId();
        }
    }

    /**
     *Enables to add a new object of type {@link Obj} to the category {@link Colors}, provided the object and its identification number.&nbsp;
     * The object is added to the category's field objectList, a hash map.
     * @param obj the object to be added to the category's field objectList.
     * @param id the identification number of the object to be added to the category's field objectList.
     */
    public void addObject (Obj obj, int id) {
        objectList.put(id,obj);
    }

    /**
     *Enables to add a new morphism of type {@link Morph} to the category {@link Colors}, provided the morphism and its identification number.&nbsp;
     *The morphism is added to the category's field morphList, a hash map.
     *  @param morph the morphism to be added to the category's field morphList.
     * @param id the identification number of the morphism to be added to the category's field morphList.
     */
    public void addMorph (Morph morph, int id) {
        morphList.put(id,morph);
    }

    /**
     *Creates a whole new category with a prefixed number of objects and morphisms. The field color of each object and
     * the connections between objects are going to be determined in a random way.
     * @param objNumber total number of objects the category contains
     * @param numberOfConnections total number of morphisms that start from each object
     * @throws InvalidColorException If the RGB values provided to the Color's constructor do not fall within the allowed range (0-1).
     */
    public void createCatForImage (int objNumber, int numberOfConnections) throws InvalidColorException {

        objectArray = new Obj[objNumber];
        morphArray = new Morph[objNumber * numberOfConnections];

        Random rand = new Random();
        int N = 0;

        for (int i = 0; i < objNumber; i++) {

            double r = rand.nextDouble();
            double g = rand.nextDouble();
            double b = rand.nextDouble();

            Obj obj = new Obj(new Color(r,g,b),i);
            //objectList.put(i,obj);
            objectArray[i] = obj;

        }

        for (Obj obj : objectArray) {
            for (int i = 0; i < numberOfConnections; i++) {
               Morph morph = new Morph(obj, objectArray[rand.nextInt(objNumber)]);
               
               morphArray[N++] = morph;
               //System.out.println(N);
               //morphList.put(N++, morph);
               //System.out.println(morph.getId());
            }
        

        }

    }

    /**
     *Returns a string displaying each value of the RGB and YUV triple of the field color of each object of {@link Colors}' field objectList
     * and of each source and target object of each morphism of {@link Colors}' field morphList one by one.&nbsp; Every value is preceded by the component's name followed by a colon.
     * @return a string displaying each value of the RGB and YUV triple of the field color of each object of Colors' field objectList
     * and of each source and target object of each morphism of Color's field morphList one by one.
     */
    public String toString () {
        return objectList.toString() + morphList.toString();
    }

    /**
     *Prints a string displaying each value of the RGB and YUV triple of the field color of each object of {@link Colors}' field objectList, and
     *the identification number of the source and target and a string displaying each value of the RGB and YUV triple of the average color field of each morphism of {@link Colors}' field morphList
     * one by one (the values are encapsulated in square brackets and separated by commas).
     */
    public void printDEBUG () {
        for (Obj obj : objectList.values()) {
            System.out.println(obj.getStringForImage());
        }

        for (Morph m : morphList.values()) {
            System.out.println(m.getStringForImage());
        }
    }

    /**
     *Writes in a new line of the file to be saved in the path specified:
     *<ul>
     *<li>for every object of {@link Colors}' field objectArray: a string displaying each value of the RGB and YUV triple of the field color.&nbsp;
     *<li>for every morphism of {@link Colors}' field morphArray: the identification number of the source and target and a string displaying each value of the RGB and YUV triple of the average color field.&nbsp;
     *</ul>
     *The values are encapsulated in square brackets and separated by commas.
     * @param path the path where you want to save the category file
     */
    public void saveToFile (String path) {
        try{
            FileWriter writer = new FileWriter("prova.txt");
            for (Obj obj : objectArray) {
                writer.write(obj.getStringForImage() + "\n");
            }

            //System.out.println("Finished Nodes");

            for (Morph m : morphArray) {
                writer.write("M:"+ m.getStringForImage() + "\n");
            }

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
