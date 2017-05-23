package tcpserver;

import java.net.*;
import java.io.*;
/**
 *
 * @author Walter Orando
 */
public class TcpServer {

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
    public static void main(String[] args) throws Exception{
        ServerSocket Nsocket = new ServerSocket(6789);
        double[][]mat;
        String Sn;
        String detm;
        while(true)
            {
                Socket connSocket = Nsocket.accept();
                
                BufferedReader Clientdata = new BufferedReader(new InputStreamReader(connSocket.getInputStream()));
                
                PrintWriter ClientOutput = new PrintWriter(connSocket.getOutputStream(), true);
                
                Sn = Clientdata.readLine();
                System.out.println(Sn);
                
                mat = stringToMatrix(Sn);
                //determinant(mat,mat.length);
                //System.out.println(determinant(mat, mat.length));
                double m = determinant(mat, mat.length);
                
               
                
                ClientOutput.println("the determinant = "+m);
                
            }
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
  
    
}
