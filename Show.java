import java.awt.*;
import javax.swing.*;
import java.util.*;
//Klasa Show merret shfaqjen e hartes ne ekran
public class Show extends JComponent{
    int sc;
    Map map;
    ArrayList<ArrayList<Population>> in;
    int m1;
    double m2;
    //konstruktori Show i cili merr si parametra harten dhe individet
    public Show(Map m,ArrayList<ArrayList<Population>> i){
        map = m;
        sc = 10;
        in = i;
        m1=0;
    }
   
    public void draw1(int gen_num,double distance){
    m1=gen_num;
    m2=distance;
    }
     public void draw(){
     super.repaint();
    }
    /*metoda paintComponent e cila permes pendes grafike bene vizatimin e pengesave,
      pikes fillestare-perfundimtare dhe individet*/
     public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        g.setColor(Color.BLACK);
        g.fillRect(0,0,map.x_pos*sc+2,map.y_pos*sc+1);
        g.setColor(Color.WHITE);
        g.fillRect(7,7,665,635);
  
        for(int i = 0; i < map.array.length; i++){
            for(int j = 0; j < map.array[i].length; j++){
                if(map.array[i][j] == 1){ //pengesat
                g.setColor(Color.BLACK);
                g.fillRect(i*sc,j*sc,sc,sc);
                }
                if(map.array[i][j]==2){ //pengesat e vogla 
                g.setColor(new Color(0,153,0));
                g.fillOval(i*sc,j*sc,sc+4,sc+4);}
            }
        }
        g.setColor(Color.BLACK);
        g.drawString("Start",map.s_point[0]*sc-5,map.s_point[1]*sc-20);  
        g.drawString("End",map.e_point[0]*sc-10,map.e_point[1]*sc-20); 
          
        g.setColor(Color.RED);
        g.fillOval(map.s_point[0]*sc,map.s_point[1]*sc,sc+5,sc+5);//pika startuse ku fillojne te gjenerohen individet
        g.setColor(new Color(204,0,0));
        g.fillOval(map.e_point[0]*sc,map.e_point[1]*sc,sc+8,sc+8); //pika perfundimtare ku duhet te shkojne idividet
        
        //vizatimi i individeve
        for(int a = 0; a < in.size(); a++){
            g.setColor(in.get(a).get(0).col);
            for(int b = 0; b < in.get(a).size(); b++){
                g.fillOval(in.get(a).get(b).location[0]*sc,in.get(a).get(b).location[1]*sc,sc,sc);
            }
        }
        g.setColor(Color.BLACK);
        g.drawString("Generation number: "+m1,525,600);
        g.drawString("Distance from start to end: "+m2,490,620);
    }
}