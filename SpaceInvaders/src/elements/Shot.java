package elements;

/**Classe que representa e guarda algumas informações sobre os tiros no jogo.
 * 
 * @author Gustavo Moura
 */
public class Shot extends GameObject{
    private boolean fromPlayer;
    private boolean isRand;

    public Shot(int posX,int posY,boolean fromPlayer){
        super(posX,posY,0);
        this.fromPlayer = fromPlayer;
        this.isRand = false;
    }

    public void SpawnShot(int posX,int posY){
        SetPosX(posX);
        SetPosY(posY);
        SetLife(1);
    }

    public boolean IsFromPlayer(){
        return fromPlayer;
    }

    public void Move(){
        if(fromPlayer){
            SetPosY(GetPosY()-1);
        }else{
            SetPosY(GetPosY()+1);
        }
    }

    public void SwitchIsRand(){
        isRand = !isRand;
    }

    public boolean IsRand(){
        return isRand;
    }

    
}
