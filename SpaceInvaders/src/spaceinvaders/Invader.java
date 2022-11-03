package spaceinvaders;

public class Invader {
    public int posX,posY;
    int type;
    String sprite;

    public Invader(int posX,int posY){
        this.posX = posX;
        this.posY = posY;

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
