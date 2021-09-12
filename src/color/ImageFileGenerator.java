package color;

import categoryBase.Category;

class ImageFileGenerator {


/** 
 * @param args
 * @throws InvalidColorException
 */
public static void main(String[] args) throws InvalidColorException {

    Colors category = new Colors();
    
    int numberOfObjects = 40000; 
    int numberOfConnections = 100;  

    category.createCatForImage(numberOfObjects, numberOfConnections);
    category.saveToFile("colorCategory.txt");
    }
}