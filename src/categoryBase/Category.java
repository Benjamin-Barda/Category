package categoryBase;

public interface Category<O> {
    
    interface Morphism<S,T> {
        S getSource();
        T getTarget();
        String getId();
    }

    interface Object<O> {
        O getObj();
        Morphism<? extends Object<O>,? extends Object<O>> getIdentity();
    }

    Morphism<? extends Object<O>, ? extends Object<O>> compose
            ( Morphism<? extends Object<O>, ? extends Object<O>> f,
              Morphism<? extends Object<O>, ? extends Object<O>> g)
            throws IllegalCompositionException;

}