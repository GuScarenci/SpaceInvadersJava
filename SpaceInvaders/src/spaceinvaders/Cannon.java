package spaceinvaders;

public class Cannon {
    int posX,posY;

    int life;

    public Cannon(int posX,int posY){
        this.posX = posX;
        this.posY = posY;
        this.life = 3;
    }
    
    public void Move(int i){
        this.posX += i;
    }
}
