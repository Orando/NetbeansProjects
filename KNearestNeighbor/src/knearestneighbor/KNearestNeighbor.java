/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knearestneighbor;

import java.util.*;

/**
 *
 * @author Walter Orando
 */
public class KNearestNeighbor {

   // the data
	static double[][] instances = {
		{0.35,0.91,0.86,0.42,0.71},
		{0.21,0.12,0.76,0.22,0.92},
		{0.41,0.58,0.73,0.21,0.09},
		{0.71,0.34,0.55,0.19,0.80},
		{0.79,0.45,0.79,0.21,0.44},
		{0.61,0.37,0.34,0.81,0.42},
		{0.78,0.12,0.31,0.83,0.87},
		{0.52,0.23,0.73,0.45,0.78},
		{0.53,0.17,0.63,0.29,0.72},
	};
        
        	/**
	 * Returns the majority value in an array of strings
	 * majority value is the most frequent value (the mode)
	 * handles multiple majority values (ties broken at random)
	 *
	 * @param  array an array of strings
	 * @return  the most frequent string in the array
	 */ 
	private static String findMajorityClass(String[] array)
	{
		//add the String array to a HashSet to get unique String values
		Set<String> h = new HashSet<String>(Arrays.asList(array));
		//convert the HashSet back to array
		String[] uniqueValues = h.toArray(new String[0]);
		//counts for unique strings
		int[] counts = new int[uniqueValues.length];
		// loop thru unique strings and count how many times they appear in origianl array   
		for (int i = 0; i < uniqueValues.length; i++) {
			for (int j = 0; j < array.length; j++) {
				if(array[j].equals(uniqueValues[i])){
					counts[i]++;
				}
			}        
		}

		for (int i = 0; i < uniqueValues.length; i++)
			System.out.println(uniqueValues[i]);
		for (int i = 0; i < counts.length; i++)
			System.out.println(counts[i]);


		int max = counts[0];
		for (int counter = 1; counter < counts.length; counter++) {
			if (counts[counter] > max) {
				max = counts[counter];
			}
		}
		System.out.println("max # of occurences: "+max);

		// how many times max appears
		//we know that max will appear at least once in counts
		//so the value of freq will be 1 at minimum after this loop
		int freq = 0;
		for (int counter = 0; counter < counts.length; counter++) {
			if (counts[counter] == max) {
				freq++;
			}
		}

		//index of most freq value if we have only one mode
		int index = -1;
		if(freq==1){
			for (int counter = 0; counter < counts.length; counter++) {
				if (counts[counter] == max) {
					index = counter;
					break;
				}
			}
			//System.out.println("one majority class, index is: "+index);
			return uniqueValues[index];
		} else{//we have multiple modes
			int[] ix = new int[freq];//array of indices of modes
			System.out.println("multiple majority classes: "+freq+" classes");
			int ixi = 0;
			for (int counter = 0; counter < counts.length; counter++) {
				if (counts[counter] == max) {
					ix[ixi] = counter;//save index of each max count value
					ixi++; // increase index of ix array
				}
			}

			for (int counter = 0; counter < ix.length; counter++)         
				System.out.println("class index: "+ix[counter]);       

			//now choose one at random
			Random generator = new Random();        
			//get random number 0 <= rIndex < size of ix
			int rIndex = generator.nextInt(ix.length);
			System.out.println("random index: "+rIndex);
			int nIndex = ix[rIndex];
			//return unique value at that index 
			return uniqueValues[nIndex];
		}

	}
        	/**
	 * Returns the mean (average) of values in an array of doubles
	 * sums elements and then divides the sum by num of elements
	 *
	 * @param  array an array of doubles
	 * @return  the mean
	 */ 
	private static double meanOfArray(double[] m) {
		double sum = 0.0;
		for (int j = 0; j < m.length; j++){
			sum += m[j];
		}
		return sum/m.length;
	}
        
        static class City {
        double [] cityAttributes;
        String cityName;
        public City(double [] cityAttributes, String cityName){
        this.cityAttributes = cityAttributes;
        this.cityName = cityName;
        }
        }
        
        static class Result{
            double distance;
            String cityName;
            public Result(double distance, String cityName){
            this.cityName=cityName;
            this.distance=distance;
            }
        }
        
        static class DistanceComparator implements Comparator<Result>{

        @Override
        public int compare(Result a, Result b) {
           return a.distance < b.distance ? -1 : a.distance == b.distance ? 0 : 1;
        }
        
        }

    public static void main(String[] args) {
        int n = 6;
        List<City> cityList = new ArrayList<City>();
        List<Result> resultList = new ArrayList<Result>();
        
        cityList.add(new City(instances [0], "Nairobi"));
        cityList.add(new City(instances [1], "Kisumu"));
        cityList.add(new City(instances [2], "Mombasa"));
        cityList.add(new City(instances [3], "Nairobi"));
        cityList.add(new City(instances [4], "Mombasa"));
        cityList.add(new City(instances [5], "Kisumu"));
        cityList.add(new City(instances [6], "Mombasa"));
        cityList.add(new City(instances [7], "Kisumu"));
        cityList.add(new City(instances [8], "Nairobi"));
        
        
        double [] query = {0.65,0.78,0.21,0.29,0.58};
        
        //find distance
        for (City city : cityList){
        double dist = 0.0;
        for(int j=0; j < city.cityAttributes.length; j++){
        dist+=Math.pow(city.cityAttributes[j] - query[j], 2);
        }
        double distance = Math.sqrt(dist);
        resultList.add(new Result(distance,city.cityName));
        }
        Collections.sort(resultList,new DistanceComparator());
        String [] ss = new String[n];
        for(int x=0; x<n; x++){
        System.out.println(resultList.get(x).cityName+"....."+resultList.get(x).distance);
        
        ss[x] = resultList.get(x).cityName;
  
        }
        String majClass = findMajorityClass(ss);
        System.out.println("New Class instance is: "+majClass);
        
    }
    
}
