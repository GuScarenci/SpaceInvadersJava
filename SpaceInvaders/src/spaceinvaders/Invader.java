package spaceinvaders;

public class Invader extends GameObject{
    int type;
    String sprite;

    public Invader(int posX,int posY){
        super(posX,posY,1);

        if(GetPosY() < 2){
            this.type = 2;
            this.sprite = " k ";
        }else if(GetPosY() >= 2 && GetPosY() < 4){
            this.type = 1;
            this.sprite = " w ";
        }else if(GetPosY() >= 4){
            this.type = 0;
            this.sprite = " x ";
        }
    }
    
    public void Move(int i){
        SetPosX(GetPosX()+i);
    }

    public void MoveDown(){
        SetPosY(GetPosY()+1);
    }

    public String GetSprite(){
        return this.sprite;
    }
}
