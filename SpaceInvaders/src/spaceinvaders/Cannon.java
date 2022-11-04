package spaceinvaders;

public class Cannon extends GameObject{

    private int score;

    public Cannon(int posX,int posY){
        super(posX,posY,3);
    }
    
    public void Move(int i){
        SetPosX(GetPosX()+i);
    }
    
    public int GetScore(){
        return score;
    }

    public void SetScore(int i){
        score = i;
    }
}
