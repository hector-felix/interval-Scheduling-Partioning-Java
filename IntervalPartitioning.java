package csc401proj01;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hector Felix
 */
//IntervalPartitioning
import java.io.*;
import java.util.*;

public class IntervalPartitioning {
   ArrayList<Subject> subjects = new ArrayList<Subject>();  

   public static void main(String[] args){
       Scanner sc = new Scanner(System.in);
      
       System.out.print("Enter n : ");
       int n = sc.nextInt();

       System.out.print("Enter s : ");
       int s = sc.nextInt();

       System.out.print("Enter t : ");
       int t = sc.nextInt();

       IntervalPartitioning program = new IntervalPartitioning(n, s, t);
       program.intervalPartition();
   }

   public IntervalPartitioning(int n, int s, int t){
       Random rand = new Random();
       for(int i = 0; i < n; i++){
            // Generate random integers in range s to t
            int st = s + rand.nextInt(t - s);
            int en = st + rand.nextInt(t - st + 1);
           subjects.add(new Subject(st, en));
           System.out.println("Added Interval (" + st + ", " + en + ")");
       }
   }

   public void intervalPartition(){
       Collections.sort(subjects);       //sort subjects/jobs by start time

       ArrayList<ClassRoom> allClassRooms = new ArrayList<ClassRoom>();   //list of all rooms & their respective subjects
       PriorityQueue<ClassRoom> roomsQueue = new PriorityQueue<ClassRoom>();       //priority Queue holds ClassRooms, ordered by the finish time of the last subject added


       ClassRoom firstClassRoom = new ClassRoom(subjects.get(0));       //Create 1st room & add 1st subject (earliest start time)
       int roomCount =1;   //running total of how many rooms exist
       roomsQueue.add(firstClassRoom);   //add to queue
       allClassRooms.add(firstClassRoom);   //add to list of rooms & their contents

       for(int i=1; i<subjects.size(); i++){       //loop over all subjects (skipping 1st since already added)
           ClassRoom currentClassRoom = roomsQueue.peek();       //get temporary room (earliest finishTime from top of Priority Queue)
           Subject currentSubject = subjects.get(i);   //current subject from the position in the loop

           if(currentSubject.getStartTime() >= currentClassRoom.getLastFinished()){       //Compatible if it starts AFTER or @ the same time as the old subject Finished the last thing finished. MUST BE >= OR JOBS CAN'T BE SCHEDULED RIGHT AFTER EACH OTHER (h after e)
               currentClassRoom.addSubject(currentSubject);
               roomsQueue.remove();
               roomsQueue.add(currentClassRoom);   //looks redundant, but actually forces the heap to reorder with a new lastFinished for the same room
           }
           else{
               ClassRoom newClassRoom = new ClassRoom(currentSubject);   //create a new room
               roomsQueue.add(newClassRoom);   //add to queue
               allClassRooms.add(newClassRoom);       //add new room to the list of all rooms
               ++roomCount;
           }
       }
       System.out.println("Total rooms needed : " + String.valueOf(roomCount));
   }


   private class ClassRoom implements Comparable<ClassRoom>{
       private LinkedList<Subject> subjects = new LinkedList<Subject>();   //subjects that the room holds
       private int lastFinished;   //time that the last subject currently in the room finishes

       public ClassRoom(Subject subject){addSubject(subject); }
       public int getLastFinished(){return lastFinished; }

       //Add subject to the room & update lastFinished for the room to match the new subject added
       public void addSubject(Subject subject){
           subjects.add(subject);
           lastFinished = (subject.getFinishTime());   //very important to update, or the room won't update
       }

       @Override
       //Compare based on finish time (for the priority queue)
       public int compareTo(ClassRoom otherClassRoom) {return this.getLastFinished() - otherClassRoom.getLastFinished(); }
   }


   private class Subject implements Comparable<Subject>{
       private int startTime;
       private int finishTime;

       public Subject(int startTime, int finishTime){
           this.startTime = startTime;
           this.finishTime = finishTime;
       }
       public int getStartTime(){return startTime; }
       public int getFinishTime(){return finishTime; }
      
       @Override
       //Compare based on finish time (for initial sorting)
       public int compareTo(Subject otherSubject) {
           return this.getFinishTime() - otherSubject.getFinishTime();
       }

   }

}

