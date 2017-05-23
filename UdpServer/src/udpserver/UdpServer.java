package udpserver;

import java.io.*;
import java.net.*;
/**
 *
 * @author Walter Orando
 */
public class UdpServer {
    
    public static double determinant(double A[][], int N)
        {
            double det = 0;
            if (N==1)
                {
                    det = A[0][0];
                }else if(N==2)
                {
                 det = A[0][0]*A[1][1] - A[1][0]*A[0][1];   
                }else
                {
                det = 0;
                for(int j=0;j<N;j++)
                {
                double[][]m = new double[N-1][];
                for(int k=0;k<(N-1);k++)
                {
                m[k] = new double[N-1];
                }
                for(int i=1;i<N;i++)
                {
                int j1 =0;
                for(int j2=0;j2<N;j2++)
                {
                if(j2==j1)
                    continue;
                m[i-1][j1] = A[i][j2];
                j1++;
                }
                
                }
                det +=Math.pow(-1.0,1.0+j+1.0)*A[0][j]*determinant(m,N-1);
                }
                }
        
            return det;
        } 
    
     public static double[][] stringToMatrix(String string){
        
        String[] array = string.split(",");
        int n = (int) Math.sqrt(array.length);
        double [][] mat = new double[n][n];
        
        int counter = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                mat[i][j] = Double.parseDouble(array[counter]);
                counter++;
            }
        }
        
        //print matrix
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                System.out.print(mat[i][j] + "  ");
            }
            System.out.println("");
        }
                
        
        return mat;
    }
     public static String doubleToString(double matrix){
         String doubletostring = "";
         doubletostring = doubletostring.concat("the determinant is " +matrix+ ".");
     
         return doubletostring;
     }

    public static void main(String[] args) throws Exception{
        
        DatagramSocket socket = new DatagramSocket(9876);
        
        byte[] recievedata = new byte[1024];
        byte[] sendData;
        double[][]mat;
        
        while(true)
        {
            DatagramPacket recieveP = new DatagramPacket(recievedata, recievedata.length);
            
            socket.receive(recieveP);
            
            String matrix = new String(recieveP.getData(),0,recieveP.getLength());
            mat = stringToMatrix(matrix);
            
            double m = determinant(mat, mat.length);
            
            
            InetAddress Addrs  = recieveP.getAddress();
            
            int port = recieveP.getPort();
            
            //sendData initialization;
            String newM = doubleToString(m);
            
            sendData = newM.getBytes();
            
            
            DatagramPacket sendP = new DatagramPacket(sendData, sendData.length, Addrs,port);
            
            socket.send(sendP);
        }
    }
    
}
