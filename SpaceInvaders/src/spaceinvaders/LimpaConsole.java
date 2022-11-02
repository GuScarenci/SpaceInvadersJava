package spaceinvaders;

import java.io.IOException;

public class LimpaConsole {
    
    public static void Clear(){
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }
}