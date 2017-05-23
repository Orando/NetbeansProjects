package kmeansclustering;

/**
 *
 * @author Walter Orando
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class KMeans 
{
    ArrayList<Double> records = new ArrayList<Double>();
    
    public static String[] readFile(String filename)throws Exception{
        
         String path;
         path = filename;
         
        
    FileReader fr2 = new FileReader(path);
       
    BufferedReader reader2 = new BufferedReader(fr2);
    
    String line;
   int n=0;
    while((line = reader2.readLine()) !=null){
          n++;
      }
    reader2.close();
  
       
       
       
       FileReader fr = new FileReader(path);
       
       BufferedReader reader = new BufferedReader(fr);
       
       int noLines = n;
       
       String[] centroids = new String[noLines];      
       
       
       int i;
       for(i=0; i<noLines; i++)
        {
             centroids[i] =reader.readLine();
           
        }
       reader.close();
       
       return centroids;
          
    }
    
    public static double distance(double[] pointA,double[] pointB){
    double distance = 0.0;
    for(int i = 0; i<pointA.length; i++){
    double dist = pointA[i] - pointB[i];
    distance +=dist*dist; 
    }
        return Math.sqrt(distance);
    }
            
  public static void main(String[] args) throws Exception{
      String file_name = "C:/Users/Walter Orando/Documents/heart.txt";
      int n;
      Scanner input = new Scanner(System.in);
      System.out.println("Enter size of centroids");
      n = input.nextInt();
     try{
     //readFile(file_name);
     String[]centroid = readFile(file_name);
     String[] newCentroid = readFile(file_name); 
     
    System.out.println("**************************Original Centroid******************************");
     int i;
     for(i=0; i<centroid.length; i++)
     {
         System.out.println(centroid[i]);
          
     }
     
     System.out.println("************************new Clusters**********************************");
     int j;
     for(j=0; j<n; j++)
     {
         System.out.println(newCentroid[j]);
          
     }
     }catch(IOException e){
     System.out.println(e.getMessage());
     }
     
  }
}


