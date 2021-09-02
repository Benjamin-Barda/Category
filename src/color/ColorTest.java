package color;

import java.util.LinkedList;

public class ColorTest {
    public static void main(String[] args) {


        Color c1 = new Color(255, 0, 0);
        Color c2 = new Color(255, 255, 255);
        Color c3 = new Color(255, 44, 128);
        Color c4 = new Color(57, 255, 99);
        Color c5 = new Color(45, 76, 88);

        Color[] colorsList = {c1, c2, c3, c4, c5};

        Colors catCol = new Colors(colorsList);

        LinkedList<Colors.Obj> objectList = catCol.getObjectList();

        for (Colors.Obj O1 : objectList) {
            for (Colors.Obj O2 : objectList) {
                if (O1 != O2) catCol.addMorphism(O1, O2, O1 + "-" + O2);
            }
        }

        System.out.println(catCol);


        //System.out.println(c3);


    }
}
