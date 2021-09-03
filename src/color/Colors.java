package color;

import categoryBase.Category;
import categoryBase.IllegalCompositionException;

public class Colors implements Category<Color> {


    @Override
    public Morphism<? extends Object<Color>, ? extends Object<Color>> compose
            (Morphism<? extends Object<Color>, ? extends Object<Color>> f,
             Morphism<? extends Object<Color>, ? extends Object<Color>> g)
            throws IllegalCompositionException {
        return null;
    }

    public class Obj implements Object<Color> {

        @Override
        public Color getObj() {
            return null;
        }

        @Override
        public Morphism<? extends Object<Color>, ? extends Object<Color>> getIdentity() {
            return null;
        }
    }

    public class Morph implements Morphism<Obj,Obj> {

        @Override
        public Obj getSource() {
            return null;
        }

        @Override
        public Obj getTarget() {
            return null;
        }

        @Override
        public String getId() {
            return null;
        }
    }


}
