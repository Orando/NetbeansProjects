/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perceptron;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.time;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Walter Orando
 */
public class Perceptron {

    static double L_rate = 0.1;
    static double M_Itaration = 100;

    private static void srand(String time) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
 private List<Integer> list = new ArrayList<Integer>();
public static int SIZE = 30;
public static int MAXINT = 89; //random number between 0 - 89;

private void getRandomNumber() {
    for(int i=0;i<SIZE;i++){
        list.add((int)(Math.random()* MAXINT));
    }
}
    
    int calculateOutput(float weights[], float x, float y) 
 { 
     float sum = x * weights[0] + y * weights[1] + weights[2]; 
     return (sum >= 0) ? 1 : -1; 
} 

    
    public static void main(String[] args) throws ParseException {
       
        srand(time(null));
        
        float x[], y[], weights[], localError, globalError;
     int outputs[], patternCount, i, p, iteration, output;
        

    }
    
}
