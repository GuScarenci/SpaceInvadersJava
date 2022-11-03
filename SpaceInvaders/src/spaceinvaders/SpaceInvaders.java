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

        int finishGame = 0;

        while(!dir.equals("x") && finishGame == 0){ 

            LimpaConsole.Clear();
            
            finishGame = game.UpdateGame(dir);

            game.printMap();

            game.printPlayerStatus();
            
            dir = sc.nextLine();
        }

        sc.close();
        
    }
    
}
