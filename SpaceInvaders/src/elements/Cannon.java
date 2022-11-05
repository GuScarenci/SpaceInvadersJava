package elements;

/**Classe que representa e guarda algumas informações sobre o canhão (jogador) no jogo.
 * @author Gustavo Moura
 */
public class Cannon extends GameObject{

    private int score;
    
    /**Construtor do canhão(jogador)
    * @param posX Posição em X inicial do elemento
    * @param PosY Posição em Y inicial do elemento
    * @author Gustavo Moura
    */
    public Cannon(int posX,int posY){
        super(posX,posY,3);
    }
    
    /**Método responsável por mover o canhão. O canhão se move apenas na horizontal.
    * @param i Paramêtro que indica para onde a nave se moverá em X. 
    * @author Gustavo Moura
    */
    public void Move(int i){
        SetPosX(GetPosX()+i);
    }
    
    /**Método Getter da pontuação do jogador.
    * @return Pontuação do jogador
    * @author Gustavo Moura
    */
    public int GetScore(){
        return score;
    }
    
    /**Método Setter da pontuação do jogador.
    * @param i Valor para o qual a pontuação será definida. 
    * @author Gustavo Moura
    */
    public void SetScore(int i){
        score = i;
    }
}
