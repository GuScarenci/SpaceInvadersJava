package elements;

/**Classe que representa e guarda algumas informações sobre o objeto barreira no jogo.
 * 
 * @author Gustavo Moura
 */
public class Barrier extends GameObject{
    
    /**Construtor de barreira
    * @param posX Posição em X inicial do elemento
    * @param PosY Posição em Y inicial do elemento
    * @author Gustavo Moura
    */
    public Barrier(int posX,int posY){
        super(posX,posY,4);
    }    

}
