package udpclient;


import java.io.*;
import java.net.*;
import java.util.Scanner;
/**
 *
 * @author Walter Orando
 */
public class UdpClient {
    
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
    

    public static void main(String[] args) throws Exception{
       
       // BufferedReader userData = new BufferedReader(new InputStreamReader(System.in));
        DatagramSocket clientS = new DatagramSocket();
        
        InetAddress Addrs = InetAddress.getByName("localhost");//FQDN
        
        byte[] sendData;
        byte[] recieveData = new byte[1024];
        
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
        
       String matrixstring = matrixToString(mat);
       
       sendData = matrixstring.getBytes();
        
        DatagramPacket sendP = new DatagramPacket(sendData, sendData.length, Addrs, 9876);
        
        clientS.send(sendP);
        
        DatagramPacket recieveP = new DatagramPacket(recieveData, recieveData.length);
        
        clientS.receive(recieveP);
        
        String deterM = new String(recieveP.getData(), 0, recieveP.getLength());
        
        System.out.println("From server " + deterM);
        
        clientS.close();
    }
    
}
