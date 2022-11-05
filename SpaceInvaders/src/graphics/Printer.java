package graphics;

/**
 *Classe responsável por imprimir coisas na tela.
 * 
 * @author Gustavo Moura 
 */
public class Printer {
    
    /**Responsável por imprimir a tela inicial.
     * @author Gustavo Moura
     */
    public void PrintStartMenu(){
        PrintSpaceInvadersLogo();
        System.out.println();
        System.out.println("Pressione \"Enter\" para jogar!");
        System.out.println();
        System.out.println("Para que o jogo passe para o próximo frame,\né necessário pressionar \"Enter\".");
        System.out.println();
        System.out.println("Os comandos são dados antes de se pressionar \"Enter\".\nEsses comandos são:");
        System.out.println();
        System.out.println(" \"a\" : Move a nave para a esquerda");
        System.out.println(" \"d\" : Move a nave para a direita");
        System.out.println(" \"Espaço\" : Atira");
        System.out.println(" \"x\" : Encerra o jogo");
        System.out.println();
        System.out.println("Para qualquer coisa diferente dos comandos acima,\no jogo apenas passará um framge, sem qualquer comando a nave.");
        System.out.println();
        System.out.println("Mais detalhes no código fonte e no readme enviado no zip junto com esse projeto.");
        System.out.println();
        System.out.println("By: Gustavo Moura Scarenci");
    }

    /**Responsável por imprimir a tela final.
     * @author Gustavo Moura
     */
    public void PrintEndMenu(){
        PrintSpaceInvadersLogo();
        System.out.println();
        System.out.println("Você perdeu! Tente novamente.");
        System.out.println();
        System.out.println("Mais detalhes no código fonte e no readme enviado no zip junto com esse projeto.");
        System.out.println();
        System.out.println("By: Gustavo Moura Scarenci");
    }

    /**Responsável por limpar o console.
     * NÃO FUNCIONA SEMPRE, DEPENDENDO DA SUA MÁQUINA!
     * @author Gustavo Moura
     */
    public void Clear(){
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }
    
    /**Responsável por printar a "logo" do Space Invaders, chamado nas telas de 
     * início e fim para fins estéticos.
     * @author Gustavo Moura
     */
    public void PrintSpaceInvadersLogo(){
        System.out.println("*   ███* ███  ███  ███  ███ ");
        System.out.println("  * █    █ █  █ █ *█    █   ");
        System.out.println("    ███  ███  █*█  █  * ███* ");
        System.out.println(" *    █* █    ███  █    █   ");
        System.out.println("    ███  █  * █ █  ███ *███ ");
        System.out.println();
        System.out.println("*█  █  █  █ █  ███  ██  *███  ███  ███");
        System.out.println(" █  ██ █  █ █  █ █  █ █  █    █ █ *█  ");
        System.out.println(" █  ████* █ █  █ █  █ █  ███  ██   ███");
        System.out.println(" █  █ ██  ███  ███  █ █  █  * █ █    █");
        System.out.println(" █* █  █   █   █ █  ██*  ███  █ █  ███");
        System.out.println();
    }
}
