import java.awt.*;
/*Klasa Population eshte per hapin e 1 ne procesin e algoritmit gjenetik,
 ku  gjenerata e pare krijohet ne menyre te 
 rastesishme*/
public class Population{
    int index;
    String genetic_code;
    int[] location;
    boolean alive;
    Color col;
 
    //konstruktori i cili e merr si parameter ngjyren e individit
    public Population(Color n){
        col = n;
        genetic_code = genetic_m(600);
        start(new int[2]);
    }
     //metoda genetic_m e cila kthen String 
    private String genetic_m(int a){
        String s = "";
        for(int b = 0; b < a; b++){
            s +=(int)(Math.random()*4);
        }
        return s;
    }
    //metoda start ku si parameter e merr piken fillestare
    public void start(int[] s_point){
        location = new int[]{s_point[0],s_point[1]};
        alive = true;
        index = 0;
    }
    //Metoda move eshte per kahjen e levizjes se individeve.
    public void move(){
        int temp = Integer.parseInt(genetic_code.substring(index%200,index%200+1));
        if(temp == 0){ //nalte,y zvogelohet
            location[1] --;
        }
        else if(temp == 1){ //djathtas,x rritet
            location[0] ++;
        }
        else if(temp == 2){ //poshte,y rritet
            location[1] ++;
        }
        else{ //majtas
            location[0] --; //x zvogelohet
        }
        index++;
    }
    //konstruktori Population i cili merre si parameter kodin gjenetik dhe ngjyren
    public Population(String g, Color n){
        genetic_code = g;
        col = n;
    }
    //konstruktori Population i cili merr si parameter kodin gjenetik,ngjyren dhe lokacionin e individit
    public Population(String g, Color n, int[] l){
        genetic_code = g;
        col=n;
        location=l;
    }
   
    //metoda copy e cila kthen Individin
    public Population copy(){
        return new Population(genetic_code,col,location);
    }
}