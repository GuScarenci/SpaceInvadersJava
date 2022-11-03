
package spaceinvaders;

public abstract class GameObject {
    int posX,posY;
    int life;
    
    public GameObject(int posX,int posY,int life){
        this.posX = posX;
        this.posY = posY;
        this.life = life;
    }
    
}
