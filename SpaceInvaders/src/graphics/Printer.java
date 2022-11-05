package graphics;

/**
 *Classe responsável por printar coisas na tela.
 * 
 * 
 * @author Gustavo Moura 
 */
public class Printer {
    
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

    public void PrintEndMenu(){
        PrintSpaceInvadersLogo();
        System.out.println();
        System.out.println("Você perdeu! Tente novamente.");
        System.out.println();
        System.out.println("Mais detalhes no código fonte e no readme enviado no zip junto com esse projeto.");
        System.out.println();
        System.out.println("By: Gustavo Moura Scarenci");
    }

    public void Clear(){
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }
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
