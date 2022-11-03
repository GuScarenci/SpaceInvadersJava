package spaceinvaders;

public class Invader extends GameObject{
    int type;
    String sprite;

    public Invader(int posX,int posY){
        super(posX,posY,1);

        if(this.posY < 2){
            this.type = 2;
            this.sprite = " k ";
        }else if(this.posY >= 2 && this.posY < 4){
            this.type = 1;
            this.sprite = " w ";
        }else if(this.posY >= 4){
            this.type = 0;
            this.sprite = " x ";
        }
    }
    
    public void Move(int i){
        this.posX += i;
    }

    public void MoveDown(){
        this.posY++;
    }

    public String GetSprite(){
        return this.sprite;
    }
}
