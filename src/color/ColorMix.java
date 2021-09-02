package color;

public class ColorMix  {

    // Subtractive mixing
    public static Color blend(Color color1, Color color2) {

        Color C3 = new Color();
        C3.setR(255 - Math.sqrt((Math.pow(255 - color1.getR(), 2) + Math.pow(255 - color2.getR(),2)) / 2));
        C3.setG(255 - Math.sqrt((Math.pow(255 - color1.getG(), 2) + Math.pow(255 - color2.getG(),2)) / 2));
        C3.setB(255 - Math.sqrt((Math.pow(255 - color1.getB(), 2) + Math.pow(255 - color2.getB(),2)) / 2));

        return C3;
    }

    // Simple to get // TODO:Check if behave correctly
    public static Color transform(Color source, Color target) {
        Color C3 = new Color();

        C3.setR(source.getR() - target.getR());
        C3.setG(source.getG() - target.getG());
        C3.setB(source.getB() - target.getB());

        return C3;
    }




}
