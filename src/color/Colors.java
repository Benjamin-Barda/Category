package color;

import categoryBase.Category;
import categoryBase.IllegalCompositionException;
import java.util.HashMap;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;


public class Colors implements Category<Color> {

    private Obj[] objectArray;
    private Morph[] morphArray;

    private HashMap<Integer, Obj> objectList = new HashMap<>();
    private HashMap<Integer, Morph> morphList = new HashMap<>();

    // TODO: Need to clean and implement this method now it is not relevant
    @Override
    public Morphism<? extends Object<Color>, ? extends Object<Color>> compose
            (Morphism<? extends Object<Color>, ? extends Object<Color>> f,
             Morphism<? extends Object<Color>, ? extends Object<Color>> g)
            throws IllegalCompositionException {
        return null;
    }

    public class Obj implements Object<Color> {

        Color color;
        Morph identity;
        final int id;

        Obj (Color color, int id) throws InvalidColorException {
            this.color = color;
            this.identity = new Morph(this, this);
            this.id = id;
        }

        @Override
        public Color getObj() {
            return color;
        }

        @Override
        public Morph getIdentity() {
            return identity;
        }

        public int getId() {
            return id;
        }

        public String toString() {
            return color.toString();
        }

        public String getStringForImage () {
            return color.getStringForImage();
        }

    }

    public class Morph implements Morphism<Obj,Obj> {

        Obj source, target;
        int sourceID, targetID;
        Color missing;

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

        @Override
        public Obj getSource() {
            return source;
        }

        @Override
        public Obj getTarget() {
            return target;
        }

        @Override
        public String getId() {
            return source.id + ","  + target.id + missing.getStringForImage();
        }

        public String toString() {
            return " source: " + source.toString() + "target: " + target.toString() + "\n ---" + missing.toString();
        }

        public String getStringForImage () {
            return getId();
        }
    }


    public void addObject (Obj obj, int id) {
        objectList.put(id,obj);
    }

    public void addMorph (Morph morph, int id) {
        morphList.put(id,morph);
    }

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

    public String toString () {
        return objectList.toString() + morphList.toString();
    }

    public void printDEBUG () {
        for (Obj obj : objectList.values()) {
            System.out.println(obj.getStringForImage());
        }

        for (Morph m : morphList.values()) {
            System.out.println(m.getStringForImage());
        }
    }

    public void saveToFile (String path ) {
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
