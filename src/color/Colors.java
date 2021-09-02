package color;

import java.util.LinkedList;

import categoryBase.Category;
import categoryBase.IllegalCompositionException;


//TODO: Better the array approach or the list one ???

public class Colors implements Category<Color> {

    Color[] colorsList;

    LinkedList<Obj> objectList = new LinkedList<>();
    LinkedList<Morph> morphismList = new LinkedList<>();

    Colors(Color[] colorsList) {
        for (int i = 0; i < colorsList.length; i++) {
            addObject(new Obj(colorsList[i], "item-" + i));
            // Create the morphisms;
        }
    }


    @Override
    public Morphism<? extends Object<Color>, ? extends Object<Color>> compose
            (Morphism<? extends Object<Color>, ? extends Object<Color>> f,
             Morphism<? extends Object<Color>, ? extends Object<Color>> g) throws IllegalCompositionException {
        if (f.getTarget() != g.getSource()) throw new IllegalCompositionException();
        Morph m = new Morph((Obj) f.getSource(), (Obj) g.getTarget(), f.getId() + "∘" + g.getId());
        morphismList.add(m);
        return m;
    }

    public void addMorphism(Obj source, Obj target, String id) {

        if (!objectList.contains(source)) objectList.add(source);
        if (!objectList.contains(target)) objectList.add(source);


        Morph morph = new Morph(source, target, id);

        source.sourceOf.add(target);
        target.targetOf.add(source);
        morphismList.add(morph);

    }

    public void addObject(Obj object) {
        objectList.add(object);
    }

    public LinkedList<Obj> getObjectList() {
        return objectList;
    }

    public LinkedList<Morph> getMorphismList() {
        return morphismList;
    }

    public String toString() {
        String repr = "Objects :";

        for (Obj obj : objectList) {
            repr += "\n\t" + obj + " -- " + obj.color;
        }

        repr += "\nMorphisms: ";

        for (Morph m : morphismList) {
            repr += "\n\t" + m;
        }


        return repr;
    }

    class Obj implements Object<Color> {

        Color color;
        Morphism<Obj, Obj> identity;
        String id;

        LinkedList<Obj> sourceOf = new LinkedList<>();
        LinkedList<Obj> targetOf = new LinkedList<>();

        Obj(Color color, String id) {
            this.color = color;
            this.identity = new Morph(this, this, "id-" + id);
            this.id = id;
        }

        Obj(String id) {
            this.color = new Color();
            this.id = id;
        }

        @Override
        public Color getObj() {
            return color;
        }

        @Override
        public Morphism<Obj, Obj> getIdentity() {
            return identity;
        }

        public String toString() {
            return this.id;
        }
    }

    class Morph implements Morphism<Obj, Obj> {

        Obj source, target;
        String id;
        Color missing;
        MixingType mixingType;


        Morph(Obj source, Obj target, String id) {
            this.source = source;
            this.target = target;
            this.id = id;

            this.missing = ColorMix.transform(source.color, target.color);
            mixingType = MixingType.TRANSFORM;


        }

        Morph(Obj source, Color color, String id) {
            this.source = source;
            this.id = id;
            this.missing = color;

            Color target = ColorMix.blend(this.source.color, color);
            this.target = new Obj(target, "trgt-of-" + this.id);
            mixingType = MixingType.BLEND;

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
            return id;
        }

        public MixingType getMixingType() {
            return mixingType;
        }

        public Color getMissing() {
            return missing;
        }

        public String toString() {
            return getId() + " source: " + source + " target: " + target + " MixType: " + mixingType;
        }


    }


}
