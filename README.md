# Category Theory 

## Authors: Giorgia Barboni, Benjamin Barda

### Artifact

Our Goal with this project was to visualize large categories in an eye appealing way. This prompted us to reason about the possibility of a category of colors. 
At first glance the task seemed quite easy but we quicly realized that color mixing is a very large field of studies and that implementing an algorithm that would mimic pigment mmixing was too big of a task for the two of us. 

After a bit of research we found out about color spaces in particular YUV, a way of encoding images used mostly in the color-image-Pipeline. 


The idea is simple. we get a bunch of points in the color space each to which we can associate an RGB value in addition the YUV coordinates of the color space. 
Then we arbitrarly connect points with each other. In connecting them we can find a middle point. We can then associate that middle point as a morphism. 

We then plotted the whole thing using a simple Processing.py sketch to get some good looking rappresentation of the category itself. 

The possibility to expand the project are many and space from just diffrent visual rappresentation ( Also 3d with the lumincnace giving us the Z coordinates) to visualizing in real time the making of various composition, the removal of a color and so on. 

Following is a proposal of some sample images we generated, playing with number of nodes and connections and with diffrent setting in the visualizing sketch.

# Images 
![Image_1](https://user-images.githubusercontent.com/80880329/132997486-294f7730-7dcf-41a5-9b34-79cc573c5ce1.png)
![Image_2](https://user-images.githubusercontent.com/80880329/132997489-2cd33d6d-5fd5-425f-90a5-9c6a12d4b0ac.jpeg)
![Image_3](https://user-images.githubusercontent.com/80880329/132997492-74b713df-bab9-4aa8-bcbf-b872b7be0ddd.jpeg)
![Image_4](https://user-images.githubusercontent.com/80880329/132997495-bb529177-b6a7-424e-87d6-60b1c930e1c5.jpeg)
![Image_5](https://user-images.githubusercontent.com/80880329/132997496-4d25e402-ad37-4871-a1ad-d87a88517827.jpeg)



# Comments 
We are very happy with the final result as we beleive that we have found a diffrent approach to the task proposed than most. Moreover the intense use of generics in the project gave us a better understanding of this topic. What we mostly understood is how vast and complicated it is and how a good planning before-hand of the work makes half of the work done. 


# References
* https://en.wikipedia.org/wiki/YUV
* https://reperiendi.wordpress.com/2007/11/03/category-theory-for-the-java-programmer/
* https://py.processing.org/reference/
