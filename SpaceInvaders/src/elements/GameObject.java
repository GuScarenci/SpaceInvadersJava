package elements;

/**Classe mãe de vários objetos no jogo.
 * 
 * @author Gustavo Moura
 */
public abstract class GameObject {
    private int posX,posY;
    private int life;
    
    /**Construtor do GameObject, chamado por todas as suas classes filhas.
    * @param intPosX Posição em X inicial do objeto.
    * @param intPoY Posição em Y inicial do objeto. 
    * @param life Vida do objeto, pré-determinada para cada objeto. 
    * @author Gustavo Moura
    */
    public GameObject(int posX,int posY,int life){
        this.posX = posX;
        this.posY = posY;
        this.life = life;
    }
    
    /**Método responsável por reduzir a vida do objeto em uma unidade.
    * @author Gustavo Moura
    */
    public void reduceLife(){
        life--;
    }
    
    /**Método Getter da posição em X do objeto.
    * @return Posição em X do objeto
    * @author Gustavo Moura
    */
    public int getPosX(){
        return posX;
    }
    
    /**Método Getter da posição em Y do objeto.
    * @return Posição em Y do objeto
    * @author Gustavo Moura
    */
    public int getPosY(){
        return posY;
    }

    /**Método Getter da vida do objeto.
    * @return Vida do objeto
    * @author Gustavo Moura
    */
    public int getLife(){
        return life;
    }

    /**Método Setter da vida do objeto.
    * @param i Valor para o qual a vida do objeto será definida.
    * @author Gustavo Moura
    */
    public void setLife(int i){
        this.life = i;
    }

    /**Método Setter da posição em X do objeto.
    * @param i Valor para o qual a posição em X será definida.
    * @author Gustavo Moura
    */
    public void setPosX(int posX){
        this.posX = posX;
    }
    
    /**Método Setter da posição em Y do objeto.
    * @param i Valor para o qual a posição em Y será definida.
    * @author Gustavo Moura
    */
    public void setPosY(int posY){
        this.posY = posY;
    }
}
