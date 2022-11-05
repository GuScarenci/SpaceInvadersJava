package elements;

/**Classe que representa e guarda algumas informações sobre os inimigos no jogo.
 * 
 * @author Gustavo Moura
 */
public class Invader extends GameObject{
    private int type;
    private String sprite;

   
    /**Construtor do Invasor.
    * Baseado na posição em Y inicial do inimigo, o tipo do inimigo será definido, definindo sua sprite.
    * @param intPosX Posição em X inicial do inimigo.
    * @param intPoY Posição em Y inicial do inimigo. 
    * @author Gustavo Moura
    */
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
    
    /**Método responsável por mover o inimigo em X.
    * @param i Paramêtro que indica para onde o inimigo se moverá em X. 
    * @author Gustavo Moura
    */
    public void Move(int i){
        SetPosX(GetPosX()+i);
    }
    
    /**Método responsável por mover o inimigo em Y em uma unidade.
    * @author Gustavo Moura
    */
    public void MoveDown(){
        SetPosY(GetPosY()+1);
    }
    
    /**Método Getter da sprite do inimigo.
    * @author Gustavo Moura
    */
    public String GetSprite(){
        return sprite;
    }
}
