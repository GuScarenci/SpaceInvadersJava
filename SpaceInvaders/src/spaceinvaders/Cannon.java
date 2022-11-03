package spaceinvaders;

public class Cannon extends GameObject{

    int score;

    public Cannon(int posX,int posY){
        super(posX,posY,3);
    }
    
    public void Move(int i){
        this.posX += i;
    }
}
