package elements;

public abstract class GameObject {
    private int posX,posY;
    private int life;
    
    public GameObject(int posX,int posY,int life){
        this.posX = posX;
        this.posY = posY;
        this.life = life;
    }

    public void ReduceLife(){
        life--;
    }
    
    public int GetPosX(){
        return posX;
    }

    public int GetPosY(){
        return posY;
    }

    public int GetLife(){
        return life;
    }

    public void SetPosX(int posX){
        this.posX = posX;
    }

    public void SetPosY(int posY){
        this.posY = posY;
    }
}
