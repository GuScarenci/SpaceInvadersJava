package elements;

/**Classe que representa e guarda algumas informações sobre as células que compõem o mapa no jogo.
 * Essas células acabam por funcionar como um sistema de coordenadas no jogo.
 * @author Gustavo Moura
 */
public class MapCell{
    private boolean hasAnInvader;
    private boolean hasAPlayer;
    private boolean hasABarrier;
    private boolean hasAShot;
    private boolean hasAnInvaderShot;
    
    /**Método Getter da informação da célula.
    *A informação é um número retornado que varia conforme o que existe na célula, por exemplo:
    *   0 - Indica uma célula vazia.
    *   1 - Indica que nessa célula está apenas o jogador.
    *   2 - Indica que nessa célula está apenas um inimigo. 
    *   etc...
    *@return Informação da célula do mapa
    *@author Gustavo Moura
    */
    public int GetCellInfo(){
        if(hasAnInvaderShot){
            if(hasABarrier){
                return 5;
            }
            if(hasAPlayer){
                return 8;
            }
            return 7;
        }
        if(hasAShot){
            if(hasAnInvader){
                return 6;
            }
            if(hasABarrier){
                return 5;
            }
            if(hasAShot){
                return 4;
            }
        }
        if(hasABarrier){
            return 3;
        }
        if(hasAPlayer || hasAnInvader){
            if(hasAnInvader){
                return 2;
            }    
            if(hasAPlayer){
                return 1;
            }
        }
        return 0;
    }

    /**Método Setter para falar que nessa célula há uma barreira ou não.
    *@param x Caso true, indica que há uma barreira nessa célula e caso falso, indica que não.
    *@author Gustavo Moura
    */
    public void SetBarrier(boolean x){
        hasABarrier = x;
    }
    
    /**Método Setter para falar que nessa célula há um inimigo ou não.
    *@param x Caso true, indica que há um inimigo nessa célula e caso falso, indica que não.
    *@author Gustavo Moura
    */
    public void SetInvader(boolean x){
        hasAnInvader = x;
    }
    
    /**Método Setter para falar que nessa célula há o jogador ou não.
    *@param x Caso true, indica que há o jogador nessa célula e caso falso, indica que não.
    *@author Gustavo Moura
    */
    public void SetPlayer(boolean x){
        hasAPlayer = x;
    }

    /**Método Setter para falar que nessa célula há tiros ou não.
    *@param x Caso true, indica que há tiros nessa célula e caso falso, indica que não.
    *@param fromPlayer Caso true, se refere ao tiro do jogador, caso false,se refere ao tiro de um inimigo.
    *@author Gustavo Moura
    */
    public void SetShot(boolean x,boolean fromPlayer){
        if(fromPlayer){
            hasAShot = x;
        }else{
            hasAnInvaderShot = x;
        }
    }
}
