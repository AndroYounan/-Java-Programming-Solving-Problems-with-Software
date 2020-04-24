import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Put code here
        int NumPoints=0;
        for (Point currpt : s.getPoints()){
            NumPoints +=1;}
        return NumPoints;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        double totalPerim = getPerimeter(s);
        double NumPoints = (double) getNumPoints(s);
        double AvgLength = totalPerim / NumPoints; 
        return AvgLength;
    }

    public double getLargestSide(Shape s) {
        // Put code here
        double LargestSide = 0.0; 
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) { 
            double currDist = prevPt.distance(currPt);
            if (currDist > LargestSide){
            LargestSide = currDist;}
            prevPt = currPt;
        }
        return LargestSide;
    }
    
    public double getLargestX(Shape s) {
        // Put code here
        Point PrevPt = s.getLastPoint();
        double PrevPtX= PrevPt.getX();
        double LargestX=PrevPtX; 
        
        for (Point currPt : s.getPoints()) { 
            double newX = currPt.getX();
            if (newX > LargestX){
            LargestX = newX;}
        }
        return LargestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        DirectoryResource dr = new DirectoryResource();
        double largestPerim = 0.0;
         for(File f : dr.selectedFiles()){
            FileResource file = new FileResource(f);
            Shape shape = new Shape(file);
            double perim = getPerimeter(shape);
            if(perim > largestPerim) {
                largestPerim = perim;}
            }
        return largestPerim;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        DirectoryResource dr = new DirectoryResource();
        double largestPerim = 0.0;
        File largestFile = null;
         for(File f : dr.selectedFiles()){
            FileResource file = new FileResource(f);
            Shape shape = new Shape(file);
            double perim = getPerimeter(shape);
            if(perim > largestPerim) {
                largestPerim = perim;
                largestFile = f;}
            }  // replace this code
        return largestFile.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        int NumPoints = getNumPoints (s);
        double AvgLength = getAverageLength(s);
        double LargestSide = getLargestSide(s);
        double LargestX = getLargestX(s);  
        
        System.out.println("perimeter = " + length);
        System.out.println("Number of Point = " + NumPoints);
        System.out.println("Average Lenth = "+ AvgLength);
        System.out.println("Largest Side = "+ LargestSide);
        System.out.println("Largest X : " + LargestX); 
    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
        double LargestPerimeterMultipleFiles= getLargestPerimeterMultipleFiles();
        
        System.out.println("Largest Perimeter : " + LargestPerimeterMultipleFiles);
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        String FileWithLargestPerimeter= getFileWithLargestPerimeter();
        
        System.out.println("File With Largest Perimeter : "+FileWithLargestPerimeter);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
        pr.testPerimeterMultipleFiles();
        pr.testFileWithLargestPerimeter();
    }
}
