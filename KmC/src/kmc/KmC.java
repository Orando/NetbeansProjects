/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kmc;

/**
 *
 * @author Walter Orando
 */
import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
public class KmC {

    int k;
    int no_Items;
    ArrayList<Integer> data_Items;
    ArrayList<Integer> cz;
    ArrayList<Integer> old_cz;
    ArrayList<Integer> row;
    ArrayList<ArrayList<Integer>> groups;
    
     Scanner input;

    public KmC(int k, int no_Items) {
        this.k = k;
        this.no_Items = no_Items;
        data_Items = new ArrayList<>();
        cz = new ArrayList<>();
        old_cz = new ArrayList<>();
        row = new ArrayList<>();
        groups = new ArrayList<>();
        input = new Scanner(System.in);

        for (int i = 0; i < k; i++) {
            groups.add(new ArrayList<>());
        }

        for (int i = 0; i < no_Items; i++) {
            System.out.println("Enter Value for: " + (i + 1) + " item");
            data_Items.add(input.nextInt());
            if (i < k) {
                cz.add(data_Items.get(i));
                System.out.println("C" + (i + 1) + " is " + cz.get(i));
            }
        }
        int iter = 1;
        do {
            for (int aItem : data_Items) {
                for (int c : cz) {
                    row.add(abs(c - aItem));
                }
                groups.get(row.indexOf(Collections.min(row))).add(aItem);
                row.removeAll(row);
            }
            for (int i = 0; i < k; i++) {
                if (iter == 1) {
                    old_cz.add(cz.get(i));
                } else {
                    old_cz.set(i, cz.get(i));
                }
                if (!groups.get(i).isEmpty()) {
                    cz.set(i, average(groups.get(i)));
                }
            }
            if (!cz.equals(old_cz)) {
                for (int i = 0; i < groups.size(); i++) {
                    groups.get(i).removeAll(groups.get(i));
                }
            }
            iter++;
        } while (!cz.equals(old_cz));
        for (int i = 0; i < cz.size(); i++) {
            System.out.println("New C" + (i + 1) + " " + cz.get(i));
        }
        for (int i = 0; i < groups.size(); i++) {
            System.out.println("Group " + (i + 1));
            System.out.println(groups.get(i).toString());
        }
        System.out.println("Number of Itrations: " + iter);
    }
    
    
    public static void main(String[] args) {
 Scanner input = new Scanner(System.in);
        System.out.println("Enter Value of K");
        int k = input.nextInt();
        System.out.println("Enter No of Data Items");
        int no_Items = input.nextInt();
        new KmC(k, no_Items);
    }
     public static int average(ArrayList<Integer> list) {
        int sum = 0;
        for (Integer value : list) {
            sum = sum + value;
        }
        return sum / list.size();
    }
}
