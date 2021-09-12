package color;

class ImageFileGenerator {


/** 
 * @param args
 * @throws InvalidColorException
 */
public static void main(String[] args) throws InvalidColorException {

    Colors category = new Colors();
    
    int numberOfObjects = 40; 
    int numberOfConnections = 10;  

    category.createCatForImage(numberOfObjects, numberOfConnections);
    category.saveToFile("colorCategory");
    }
}