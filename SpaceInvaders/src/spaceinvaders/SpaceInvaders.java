package spaceinvaders;

import java.util.*; 

public class SpaceInvaders {

    public static void main(String[] args) {

        Printer printer = new Printer();
        
        printer.Clear();

        printer.PrintStartMenu();
        
        GameEngine game = new GameEngine(16,26);
        
        Scanner sc= new Scanner(System.in);
        String dir = sc.nextLine();

        game.printMap();
        game.printPlayerStatus();

        int finishGame = 0;

        while(!dir.equals("x") && finishGame == 0){ 

            printer.Clear();
            
            finishGame = game.UpdateGame(dir);

            game.printMap();

            game.printPlayerStatus();
            
            dir = sc.nextLine();
        }

        printer.Clear();
        printer.PrintEndMenu();

        dir = sc.nextLine();

        sc.close();  
    }
    
}
