package clienttcp;

import java.io.*;
import java.net.*;
import java.util.Scanner;
/**
 *
 * @author Walter Orando
 */
public class ClientTcp {

    public static void main(String[] args) throws Exception{
        
        Socket Csocket = new Socket("localhost",6789);
        int i=0;
        int j =0;
        
        
        
        Scanner input = new Scanner(System.in);
        System.out.println("Enter size of square matrix");
        int n =input.nextInt();
        
        System.out.println("Enter Elements of the matrix");
        double [][] mat = new double[n][n];
        for(i = 0; i<n; i++)
        {
        for(j = 0; j<n; j++)
        {
        mat[i][j] = input.nextDouble();
        }
        }
     
        
        PrintWriter serverOut = new PrintWriter(Csocket.getOutputStream(), true);
        
        String matrixString = matrixToString(mat);
        
        serverOut.println(matrixString);
        
        

        BufferedReader serverIn = new BufferedReader(new InputStreamReader(Csocket.getInputStream()));
       
       String inData = serverIn.readLine();
      System.out.println("From Server " + inData);
        
        Csocket.close();
    }
    
    
    public static String matrixToString(double[][] matrix){
        String matrixString = "";
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix.length; j++){
                matrixString = matrixString.concat(matrix[i][j] + ",");
            }
        }
        
        System.out.println("Matrix String : " + matrixString);
        
        return matrixString;
    }
    
}
