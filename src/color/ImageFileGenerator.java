package color;

import categoryBase.Category;

class ImageFileGenerator {

public static void main(String[] args) throws InvalidColorException {

    Colors category = new Colors();

    // DO not Run on laptoP !!!!!!!!

    int numberOfObjects = 40000; 
    int numberOfConnections = 100;  

    category.createCatForImage(numberOfObjects, numberOfConnections);
    category.saveToFile("colorCategory.txt");
    }
}