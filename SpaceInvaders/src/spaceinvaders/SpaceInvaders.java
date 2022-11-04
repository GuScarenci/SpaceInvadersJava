package spaceinvaders;

import java.util.*; 

public class SpaceInvaders {

    public static void main(String[] args) {

        //Tamanho do mapa
        int frameSizeX = 16;
        int frameSizeY = 26;

        //O Printer é responsável por exibir certas informações na tela.
        //Esqueleto de uma parte gráfica//
        Printer printer = new Printer();
        
        //Limpa o console (não funciona para todas as máquinas).
        printer.Clear();

        printer.PrintStartMenu();
        
        //GameEngine, responsável por armazenar os elementos do jogo e atualizar o jogo como um todo,
        //também é responsável por parte da parte gráfica, imprimindo o mapa e as informações do jogador.
        GameEngine game = new GameEngine(frameSizeX,frameSizeY);
        
        //Scanner para pegar os comandos do usuário
        Scanner sc= new Scanner(System.in);
        String dir = sc.nextLine();

        game.printMap();
        game.printPlayerStatus();

        int finishGame = 0;

        //Enquanto o jogador não morrer ou o usuário não der o comando "x", o jogo continua.
        while(!dir.equals("x") && finishGame == 0){ 

            printer.Clear();

            game.printMap();

            game.printPlayerStatus();
            
            dir = sc.nextLine();

            finishGame = game.UpdateGame(dir);
        }

        printer.Clear();
        printer.PrintEndMenu();

        dir = sc.nextLine();

        sc.close();  
    }
    
}
