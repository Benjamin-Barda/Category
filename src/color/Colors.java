package color;

import categoryBase.Category;
import categoryBase.IllegalCompositionException;
import netscape.javascript.JSObject;

import java.io.File;
import java.util.HashMap;
import java.util.Random;
import java.util.stream.DoubleStream;

public class Colors implements Category<Color> {

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

        Obj (Color color) throws InvalidColorException {
            this.color = color;
            this.identity = new Morph(this, this);
        }

        @Override
        public Color getObj() {
            return color;
        }

        @Override
        public Morph getIdentity() {
            return identity;
        }

        public String toString() {
            return color.toString();
        }

    }

    public class Morph implements Morphism<Obj,Obj> {

        Obj source, target;
        Color missing;

        Morph(Obj source, Obj target) throws InvalidColorException {
            this.source = source;
            this.target = target;

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
            return null;
        }

        public String toString() {
            return " source: " + source.toString() + "target: " + target.toString() + "\n ---" + missing.toString();
        }
    }


    public void addObject (Obj obj, int id) {
        objectList.put(id,obj);
    }

    public void addMorph (Morph morph, int id) {
        morphList.put(id,morph);
    }

    public void createCatForImage (long objNumber, long numberOfConnections) throws InvalidColorException {


        Random rand = new Random();
        int N = 0;

        for (int i = 0; i < objNumber; i++) {

            double r = rand.nextDouble();
            double g = rand.nextDouble();
            double b = rand.nextDouble();

            objectList.put(i, new Obj(new Color(r,g,b)));
        }

        for (Obj obj : objectList.values()) {
            for (int i = 0; i < numberOfConnections; i++) {
               Morph morph = new Morph(obj, objectList.get(rand.nextInt((int) objNumber)));
               morphList.put(N++, morph);
            }

        }

    }

    public String toString () {
        return objectList.toString() + morphList.toString();
    }







}
