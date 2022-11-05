package elements;

/**Classe que representa e guarda algumas informações sobre os tiros no jogo.
 * 
 * @author Gustavo Moura
 */
public class Shot extends GameObject{
    private boolean fromPlayer;
    private boolean isRand;

    /**Construtor de Tiro
     * @param posX Posição em X inicial de tiro, no início é sempre 0.
     * @param posY Posição em Y inicial de tiro, no início é sempre 0.
     * @param fromPlayer Caso true, indica que o tiro é do jogador, caso false, indica que o tiro é do inimigo.
     * @author Gustavo Moura
     */
    public Shot(int posX,int posY,boolean fromPlayer){
        super(posX,posY,0);
        this.fromPlayer = fromPlayer;
        this.isRand = false;
    }

    /**Método responsável por colocar o tiro no lugar certo do mapa.
     * @param posX Posição em X de onde o tiro vai sair 
     * @param posY 
     * @author Gustavo Moura
     */
    public void spawnShot(int posX,int posY){
        SetPosX(posX);
        SetPosY(posY);
        SetLife(1);
    }
    
    /**Método Getter que retorna se o tiro é um tiro do jogador ou de um inimigo.
     * @return Caso true, é um tiro do jogador, caso false, é um tiro de inimigo.
     * @author Gustavo Moura
     */
    public boolean isFromPlayer(){
        return fromPlayer;
    }

    /**Método responsável por mover o tiro.
     * Caso seja do player, se move para cima, caso seja do inimigo, se move para baixo.
     * @author Gustavo Moura
     */
    public void move(){
        if(fromPlayer){
            SetPosY(GetPosY()-1);
        }else{
            SetPosY(GetPosY()+1);
        }
    }

    /**Método responsável trocar entre tiro aleatório ou não.
     * Útil apenas para o tiro do inimigo. Quando true, o tiro será spawnado em uma posição aleatória,
     * caso false, o tiro será spawnado na posição X atual do jogador, como um tiro "mirado".
     * @author Gustavo Moura
     */
    public void switchIsRand(){
        isRand = !isRand;
    }
    
    /**Método Getter para ver se o tiro é aleatório ou não.
     * @return Se é o tiro aleatório ou não.
     * @author Gustavo Moura
     */
    public boolean isRand(){
        return isRand;
    }

    
}
