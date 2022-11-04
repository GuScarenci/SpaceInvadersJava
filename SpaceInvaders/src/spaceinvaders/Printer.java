package spaceinvaders;

public class Printer {
    
    public void PrintStartMenu(){
        System.out.println("SPACE INVADERS");
        System.out.println("Pressione \"Enter\" para jogar! =D ");
    }

    public void PrintEndMenu(){
        System.out.println("SPACE INVADERS");
        System.out.println("Você perdeu... =(");
    }

    public void Clear(){
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }
    
}
