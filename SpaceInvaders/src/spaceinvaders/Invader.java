package spaceinvaders;

public class Invader {
    public int posX,posY;
    public int dir;

    public Invader(int posX,int posY){
        this.posX = posX;
        this.posY = posY;
        this.dir = 1;

    }
    
    public void Move(int i){
        this.posX += i;

        //System.out.println("M:" + this.posX + "," + this.posY + "," + this.dir);
    }

    public void MoveDown(){
        this.posY++;
        //this.dir *= -1;
        //System.out.println("D:" + this.posX + "," + this.posY+ "," + this.dir);
    }
}
