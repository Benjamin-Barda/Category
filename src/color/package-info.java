/**
 *<p>Provides an implementation of the generic interface {@link categoryBase.Category} instantiated with the class {@link Color},
 *which incorporates the tools to create a category in which objects are colors (of class {@link Color}) and morphisms between two colors are
 *represented by arrows of the average color.&nbsp;<br></p>
 *
 *Class {@link color.ColorUtil} supplies the methods for converting colors between the <b>RGB</b> and <b>YUV</b> encoding systems through the use of
 *these specific formulas:<br>
 *<br>
 *R = Y + V * ((1-Wr)/Vmax);<br>
 *G = Y - U * ((Wb * (1-Wb))/(Umax * Wg)) - v * ((Wr * (1-Wr))/(Vmax*Wg));<br>
 *B = Y + U * ((1 - Wb)/Umax);<br>
 *<br>
 *(where Wr, Wg, Wb, Vmax, Umax are given constants)<br>
 *<br>
 *In both standards a color is described by three values:<br>
 *- In <b>RGB</b> these values encode the amounts of red green and blue in the pixel, and they inherently contain information about the
 *<i>brightness</i> (if we increase any one of them we move closer to white).&nbsp;<br>
 *<br>
 *- In <b>YUV</b> overall <i>brightness</i> (Y component) of each pixel is encoded separately from the color information (U (blue) and V (red) components).<br>
 *A color is its U and V coordinates on the chrominance plane for a given Y.&nbsp;<br>
 *<br>
 *Since human sight is much more sensitive to different levels of <i>brightness</i> than it is to differences in color,
 *reducing the color detail of an image without compromising the brightness can generate an image
 *that looks just as good to the human eye but contains less data.&nbsp;<br>
 *Converting an RGB image to YUV is therefore one of the first steps in JPEG image <i>compression</i>.&nbsp;<br>
 *<br>
 *However, to be displayed on screen the color needs to be converted back to RGB format.
 */

package color;