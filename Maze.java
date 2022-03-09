import javax.swing.*;
import java.awt.*;
import java.util.*;
public class Maze{
//metoda startIndivids eshte per fillimin e levizjes se individeve prej pikes fillestare
   public static void startIndivids(ArrayList<ArrayList<Population>> in,int[] s){
      for(int a = 0; a < in.size(); a++){
         for(int b = 0; b < in.get(a).size(); b++){
            in.get(a).get(b).start(s);      
         }  
      }  
   }
   //metoda moveIndivids eshte per levizjen e individeve
   public static void moveIndivids(ArrayList<ArrayList<Population>> in,Map h){
      for(int a = 0; a < in.size(); a++){
         for(int b = 0; b < in.get(a).size(); b++){
            if(in.get(a).get(b).alive){
               in.get(a).get(b).move();
               if(h.watch_obstacles(in.get(a).get(b).location)){
                  in.get(a).get(b).alive = false;
               }
            }
         }
      }
   }
   
//metoda per kalkulimin e fitnesit
   public static double calcFitness(ArrayList<ArrayList<Population>> i, Map h) {
      double fitness = 0;
      for(int a = 0; a < i.size(); a++){
         for(int b = 0; b < i.get(a).size(); b++){
            fitness+=1/(1+getDistance(i.get(a).get(b).location,h.e_point)); 
         }
      }
      return fitness;
   }
   //merr individet me te afte
     public static ArrayList<ArrayList<Population>> getFittest(ArrayList<ArrayList<Population>> i, Map h){
    
      ArrayList<ArrayList<Population>> individs = new ArrayList<ArrayList<Population>>();
      for(int a = 0; a < i.size(); a++){
         individs.add(new ArrayList<Population>());
         individs.get(a).add(i.get(a).get(0).copy());
         for(int b = 1; b < i.get(a).size(); b++){   
            int count = 0;
            boolean ok = true;
            while(count < individs.get(a).size() && ok){
               if(getDistance(i.get(a).get(b).location,h.e_point) < getDistance(individs.get(a).get(count).location, h.e_point)){
                  individs.get(a).add(count,i.get(a).get(b).copy());
                  ok = false;
               }
               count++;
            }
            if(ok){
               individs.get(a).add(i.get(a).get(b).copy());
            }
         
         
         }
         
      }
         
      return individs;
   }
   
   //metoda newGeneration eshte per gjeneraten e re
   public static ArrayList<ArrayList<Population>> newGeneration(ArrayList<ArrayList<Population>> c,Map h){
      double fit=calcFitness(c,h);
      print(fit);
      ArrayList<ArrayList<Population>> gen = new ArrayList<ArrayList<Population>>();
      c = getFittest(c,h);
      for(int a = 0; a < c.size(); a++){
         ArrayList<Population> individs = new ArrayList<Population>();
         for(int b = 0; b < c.get(a).size(); b++){
         String s1=c.get(a).get((int)(c.get(a).size()*(1/fit)*Math.random())).genetic_code;
         String s2=c.get(a).get((int)(c.get(a).size()*(1/fit)*Math.random())).genetic_code;
         String cross=crossover(s1,s2);
            individs.add(new Population(cross,c.get(a).get(b).col));
         
         }
         gen.add(individs);
      }
      return gen;
   }
   
  
   //kryqezimi(rikombinimi) kombinon informacionin gjenetik te dy prinderve per pasardhesit e rinj
   public static String crossover(String parent1, String parent2){
      String child = "";
      for(int i = 0; i < parent1.length(); i++){
         if(Math.random() < 0.008){ //mutacioni
            child += (int)(Math.random()*4);
         }
         else{
            if(Math.random() < 0.5){
               child += parent1.substring(i,i+1);
            }
            else{
               child += parent2.substring(i,i+1);
            }
         }
      }
      return child;
   }
   
   //checkTheEnd shikon nese individet kane arritur tek pika perfundimtare
   public static boolean checkTheEnd(ArrayList<ArrayList<Population>> in, Map h){
      int score=0;
      for(ArrayList<Population> a: in){
         for(Population i: a){
            if(i.location[0] == h.e_point[0] && i.location[1] == h.e_point[1]){
                     return true;  
                  }
               }      
            
            
      }
      return false;
   }
   public static double getDistance(int[] p1,int[] p2){
      return Math.sqrt((p1[0]-p2[0])*(p1[0]-p2[0]) + (p1[1]-p2[1])*(p1[1]-p2[1]));
   }
   public static void print(double f){
   System.out.println(f/10+"%");
   }
   
   public static double distance(Map h) {
      return Math.round(getDistance(h.s_point,h.e_point));
   }
   
   public static void main(String[] args){
      ArrayList<ArrayList<Population>> individs = new ArrayList<ArrayList<Population>>();
      int ind_num = 500 ;
      int g=2;
      //ky cikel eshte per vendosjen e ngjyrave te individit
      for(int a = 0; a < g; a++){
         individs.add(new ArrayList<Population>());
         Color color = new Color((int)(255*Math.random()),(int)(255*Math.random()),(int)(255*Math.random()));
         for(int b = 0; b < ind_num; b++){
            individs.get(a).add(new Population(color));
         }
      }
      JFrame frame = new JFrame("Maze");
      Map map=new Map();
      Show display = new Show(map,individs);
      frame.add(display);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(735,735);
      frame.setVisible(true);
      int n=0;
      startIndivids(individs,map.s_point);
      display.draw1(++n,distance(map));
      while(true){
         for(int a = 0; a < 700;a++){
            display.draw();
            moveIndivids(individs,map);
            
            if(checkTheEnd(individs, map)){
            break;
            }
            
            try{
               Thread.sleep(1);
            }
            catch(Exception e){
               System.out.println(e);
            }
            
         }
         individs = newGeneration(individs,map);
         startIndivids(individs,map.s_point);
         display.in = individs;
         display.draw1(++n,distance(map));
      
      }
   }
   
   
}