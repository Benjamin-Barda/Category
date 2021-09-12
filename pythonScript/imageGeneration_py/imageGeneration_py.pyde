
def setup(): 
    
    size(2000,2000)
    background(0)
    
    
    nodes = []
    index = 0
    
    fileName = "colorCategory.txt"
    file = open(fileName, "r")
    
    imageName = "Image1.png"
    
    for elem in file:
        if elem[0] == "[": 
            r,g,b = ( float(x) * 255  for x in elem[1:-2].split(","))
            nodes.append(Node(index,r,g,b))
            index += 1
            
        else:
            elem = elem.split()
            source, target = nodes[int(elem[1])], nodes[int(elem[2])]
            r,g,b = (float(x) * 255 for x in elem[3][1:-2].split(","))
            stroke(r,g,b,random(10,50))
            line(source.x, source.y, target.x, target.y)
    for node in nodes: 
        node.drawMe()
    save(imageName)
    print("FINISHED")
            


class Node: 
    
    def __init__(self,id, r, g, b): 
        
        self.id = id 
        self.x = random(0,width)
        self.y = random(0,height)
        
        self.r, self.b, self.g = r, g, b
        
    def drawMe(self): 
        stroke(self.r, self.g, self.b)
        fill(self.r, self.g, self.b)
        circle(self.x, self.y, 3)
        
        
        
        
        
        
        
