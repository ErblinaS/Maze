/*Klasa Map modelon harten e labirintit */
public class Map{
    int[] s_point,e_point;
    int[][] array;
    int x_pos,y_pos;
    //Konstruktori Map i cili inicializon pozitat x dhe y, piken fillestare dhe thirre metoden obstacles
    public Map(){
        x_pos = 68;
        y_pos = 65;
        s_point = new int[]{62,24};
        obstacles();
    }
    //Metoda obstacles i modelon pengesat
    public void obstacles(){
            array = new int[x_pos][y_pos];
            
            //pengesa horizontale
            for(int i = 33; i >= 0; i--){
                array[i][45] = 1;
            }
            
            //pengesa vertikale
            for(int i =0; i < 23; i++){        
                array[33][i] = 1;               
            }
            
            //pengesa        //
                           //
                         //
                           //
                             // 
            for(int i = -1; i < 2; i+=2){ 
                for(int j = 0; j < 10; j++){             
                 array[42+j][23+(j*i)] = 1;            
                }                                     
            }                                          
                    
             //pengesat e vogla                            
             array[47][50] = 2;
             array[44][42] = 2;
             array[52][35] = 2;
             array[52][29] = 2;
             array[46][23] = 2;
             array[52][15] = 2;
             array[52][9] = 2;
             array[43][9] = 2;
             array[36][16] = 2;
             array[34][30] = 2;
             array[10][36] = 2;
             array[6][38] = 2;
             array[4][29] = 2;
            
            e_point = new int[]{10,25};
        
    }
    //metoda watch_obstacles e cila ka si parameter lokacionin
    public boolean watch_obstacles(int[] loc){
        if(loc[0] < 1 || loc[1] < 1 || loc[0] >= x_pos-1 || loc[1] >= y_pos-1){//shikon pengesat ne skaje pra nese x<1 y<1 ose x>x_pos y>x_pos-1 
            return true;
            }
        else if(array[loc[0]][loc[1]] == 1 || array[loc[0]][loc[1]] == 2){
            return true;
        }
        return false;
    }
}