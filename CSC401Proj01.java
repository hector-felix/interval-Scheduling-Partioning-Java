/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csc401proj01;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Hector Felix
 */
public class CSC401Proj01 {

    /**
     * @param args the command line arguments
     */
public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
      
       System.out.print("Enter n : ");
       int n = sc.nextInt();

       System.out.print("Enter s : ");
       int s = sc.nextInt();

       System.out.print("Enter t : ");
       int t = sc.nextInt();

       Job[] jobs = new Job[n];
       Random rand = new Random();
       for(int i = 0; i < n; i++){
            // Generate random integers in range s to t
            int st = s + rand.nextInt(t - s);
            int en = st + rand.nextInt(t - st + 1);
           // subjects.add(new Job(st, en));
           jobs[i] = new Job(st, en, String.valueOf(i + 1));
           System.out.println("Job" + (i + 1) + " Added Interval (" + st + ", " + en + ")");
       }
       IntervalScheduling.findOptimalJobScheule(jobs);
   }

    
}
