package spaceinvaders;

import java.util.*; 

public class SpaceInvaders {

    public static void main(String[] args) {

        LimpaConsole.Clear();
        
        Map map = new Map(10,10);

        map.printGame();
        
        Scanner sc= new Scanner(System.in);

        String dir = sc.nextLine();

        while(!dir.equals("x")){ 

            LimpaConsole.Clear();
            
            map.UpdateMap();

            map.printGame();
            
            dir = sc.nextLine();
        }

        sc.close();
        
    }
    
}
