/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csc401proj01;

/**
 *
 * @author Hector Felix
 */
//IntervalScheduling
import java.util.*;

//Basically a struct container type
class Job implements Comparable<Job>{
   public int start;
   public int finish;
   public String name;

   public Job(int start, int finish, String name){
       this.start=start;
       this.finish=finish;
       this.name = name;
   }

   //Compare jobs by finish time
   @Override
   public int compareTo(Job job) {
       return this.finish - job.finish;
   }

   @Override
   public String toString(){
       return "Job " + name;
   }
}

public class IntervalScheduling {
   public static void findOptimalJobScheule(Job[] jobs){
       Arrays.sort(jobs);       //Sort jobs by finish time

       LinkedList<Job> jobsSelected = new LinkedList<Job>();
       jobsSelected.add(jobs[0]);       //add 1st job
       Job lastJobAdded = jobs[0];

       for(int i=1; i<jobs.length; i++){
           if(jobs[i].start >= lastJobAdded.finish){       //check if job is compatible (starts after or at the time time as the last job finishes)
               jobsSelected.add(jobs[i]);
               lastJobAdded = jobs[i];       //update for the job that was just added
           }
       }

       System.out.println("\nSelected " + jobsSelected.size() + " Jobs: ");
       for(Job job : jobsSelected){
           System.out.println(job);
       }
   }
}

