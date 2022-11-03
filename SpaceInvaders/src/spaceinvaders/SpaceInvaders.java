package spaceinvaders;

import java.util.*; 

public class SpaceInvaders {

    public static void main(String[] args) {

        LimpaConsole.Clear();
        
        Game game = new Game(16,20);

        game.printMap();
        game.printPlayerStatus();
        
        
        Scanner sc= new Scanner(System.in);

        String dir = sc.nextLine();

        while(!dir.equals("x")){ 

            LimpaConsole.Clear();
            
            game.UpdateMap(dir);

            game.printMap();

            game.printPlayerStatus();
            
            dir = sc.nextLine();
        }

        sc.close();
        
    }
    
}
