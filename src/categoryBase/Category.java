package categoryBase;

import color.Color;

/**
 * Consists of a collection of <b>objects</b> and for each pair of objects A,B a set of <b>morphisms</b> hom(A,B) between them such
 * that:
 * <ul>
 * <li>for each object X the set hom(X,X) contains an identity morphism,
 * <li>for each triple of objects X, Y, Z and pair of morphisms f &#8712; hom(X,Y) and g &#8712; hom(Y,Z), there is a composite morphism (g&#8728;f) &#8712; hom(X,Z),
 * <li>for each pair of objects X, Y and morphism f &#8712; hom(X,Y), the identity morphisms are left and right units for composition: 1_Y&#8728;f = f = f&#8728;1_X, and
 * <li>for each 4-tuple of objects X, Y, Z, W and triple of morphisms f &#8712; hom(X,Y), g &#8712; hom(Y,Z), h &#8712; hom(Z,W) composition is associative: h&#8728;(g&#8728;f) = (h&#8728;g)&#8728;f.
 * </ul>
 * @param <O> the object with which generic interface Category is instantiated
 */
public interface Category<O> {
    /**
     * An arrow between two objects, the <b>source</b> and the <b>target</b>.&nbsp; Morphisms are "processes", the possible ways to get from one value to another.&nbsp;
     *They can be composed: one can follow a process from an initial value to an intermediate value, and then follow a second process to a final value, and consider the two
     * processes together as a single composite process.
     * @param <S> source object
     * @param <T> target object
     */
    interface Morphism<S,T> {
        /**
         *Meant to return the object where this morphism starts.
         * @return the source object
         */
        S getSource();
        /**
         *Meant to return the object where this morphism ends.
         * @return the target object
         */
        T getTarget();
        /**
         *Meant to return the identifier of the source and target object of this morphism.
         * @return the identifier of source and target of this morphism
         */
        String getId();
    }

    /**
     * Elements of the category.&nbsp; If the category is viewed as a directed graph, they correspond to the nodes.&nbsp;
     * Both in Java and in category theory, objects are members of a collection.&nbsp; In Java, it is the collection of values
     * associated to a class.
     * @param <O>
     */
    interface Object<O> {
        /**
         *Meant to return a field of Object of the same type as Object's type parameter.
         * @return a field of Object of the same type as Object's type parameter
         */
        O getObj();

        /**
         *Meant to return the identity morphism on this object.&nbsp; For every object A there is an arrow which is a unit of composition.&nbsp; This arrow loops from the object to
         *itself.&nbsp; Being a unit of composition means that, when composed with any arrow that either starts at A or ends at
         *A, respectively, it gives back the same arrow.&nbsp; The unit arrow for object A is called <b>identity</b> on A.
         * @return the identity morphism on this object
         */
        Morphism<? extends Object<O>,? extends Object<O>> getIdentity();
    }

    /**
     *Composes two adjacent morphisms provided that the source of the second function coincides with the target of the first (that is, their objects match end-to-end).&nbsp;<br>
     *There are two properties that the composition in any category must satisfy:<br>
     *- Composition is <b>associative</b>.&nbsp; If you have three morphisms, f, g, and h, that can be composed, you don&#39;t need parentheses to compose them.&nbsp;<br>
     * - For every object A there is an arrow which is a unit of composition.&nbsp; This arrow loops from the object to
     * itself.&nbsp; Being a unit of composition means that, when composed with any arrow that either starts at A or ends at
     * A, respectively, it gives back the same arrow.&nbsp; The unit arrow for object A is called <b>identity</b> on A.
     * @param f the first morphism
     * @param g the second morphism
     * @return a morphism with starting point in the source of the first morphism and ending point in the target of the second
     * @throws IllegalCompositionException If the source of the second morphism doesn't coincide with the target of the first
     */
     Morphism<? extends Object<O>, ? extends Object<O>> compose
            ( Morphism<? extends Object<O>, ? extends Object<O>> f,
              Morphism<? extends Object<O>, ? extends Object<O>> g)
            throws IllegalCompositionException; 
    


            

}