package spaceinvaders;
import engine.*;
import graphics.Printer;

import java.util.*; 

/**Classe que contêm a main e roda o jogo em loop.
 * 
 * @author Gustavo Moura
 */
public class SpaceInvaders {

    /**Exibe a tela incial, recebe um input do usuário e caso diferente de 'x' 
     * roda o jogo, em loop. Após o jogo acabar, exibe a tela final. 
     * @param args 
     */
    public static void main(String[] args) {

        //Tamanho do mapa
        int frameSizeX = 16;
        int frameSizeY = 26;

        //O Printer é responsável por exibir certas informações na tela.
        //Esqueleto de uma parte gráfica//
        Printer printer = new Printer();
        
        //Limpa o console (não funciona para todas as máquinas).
        printer.clear();

        printer.printStartMenu();
        
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

            printer.clear();

            game.printMap();

            game.printPlayerStatus();
            
            dir = sc.nextLine();

            finishGame = game.updateGame(dir);
        }

        printer.clear();
        if(finishGame == 1)
            printer.printBadEnd();
        else if(finishGame == 2)
            printer.printGoodEnd();
        
        dir = sc.nextLine();

        sc.close();  
    }
}
