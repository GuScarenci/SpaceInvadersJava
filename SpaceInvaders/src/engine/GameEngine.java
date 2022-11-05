package engine;
import elements.*;

import java.util.Random;

/**Classe GameEngine, responsável por fazer quase tudo em termos de funcionamento
 * do jogo, como criar os elementos, gerar o mapa, chamar os métodos de atualização
 * do estado do jogo, etc. No momento, existem algumas funções de impressão que 
 * futuramente serão passadas para o package graphics.
 * @author Gustavo Moura
 */
public class GameEngine{

    //MapVariables
    private MapCell[][] mapCell;
    private int sizeX,sizeY;

    //Cannon(Player)Variabless
    private Cannon cannon;
    private Shot cannonShot;

    //Cannon(Player)Variables 
    private Barrier[][] barriers;

    //InvaderVariables 
    private Invader[][] invaders;
    private int invadersX,invadersY;
    private int invadersDir;
    private int invadersWalk;
    private int invadersOffsetX, invadersOffsetY;
    private Shot invaderShot;
    
    /**Construtor da GameEngine
     * Cria o mapa, coloca as barreiras, o jogador e os inimigos em suas posições iniciais.
     * @param sizeX tamanho em X (largura) do mapa
     * @param sizeY tamanho em Y (altura) do mapa
     * @author Gustavo Moura
     */
    public GameEngine(int sizeX, int sizeY){
        //Define o tamanho do mapa e o constrói
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        mapCell = new MapCell[sizeX][sizeY];
        
        for(int y = 0;y<this.sizeY;y++){
            for(int x = 0;x<this.sizeX;x++){
                mapCell[x][y] = new MapCell();
            }
        }

        //Coloca barreiras nas suas posições iniciais
        barriers = new Barrier[4][2];
        int barrierCountX = 0;
        int barrierCountY = 0;
        for(int j = sizeY-4;j<sizeY-2;j++){
            barrierCountX = 0;
            for(int i = 0;i<sizeX;i++){
                if(i%4 == 1){
                    mapCell[i][j].setBarrier(true);
                    barriers[barrierCountX][barrierCountY] = new Barrier(i, j);
                    barrierCountX++;
                }
            }
            barrierCountY++;
        }
        
        //Coloca o jogador na sua posição inicial
        cannonShot = new Shot(0,0,true);
        cannon = new Cannon((sizeX-1)/2,sizeY-1);
        mapCell[(sizeX-1)/2][sizeY-1].setPlayer(true);

        //Coloca os inimigos na posição inicial deles
        invadersX = 11;
        invadersY = 5;
        invaders = new Invader[invadersX][invadersY];
        invadersDir = 1;
        invadersOffsetX = 0; 
        invadersOffsetY = 0;
        invaderShot = new Shot(0,0,false);

        for(int y = 0;y<invadersY;y++){
            for(int x = 0;x<invadersX;x++){
                invaders[x][y] = new Invader(x,y);
                mapCell[x][y].setInvader(true);
            }
        }   
        invadersWalk = 0;
    }
    
    
    /**Responsável por atualizar o jogo de um frame para o próximo frame, atualizando as posições e status dos objetos.
     * @param dir String que indica um comando do jogador:
     *  a - Move para esquerda
     *  d - Move para a direita
     *  Espaço - Atira
     *  x - Fecha o jogo 
     * @return Caso 0, o jogo segue normalmente, caso 1, o jogo termina.
     * @author Gustavo Moura
     */
    public int updateGame(String dir){
        if(updatePlayer(dir)==1)
            return 1;
           
        if(updateInvaders() == 1)
            return 1;
        
        updateBarriers();
        return 0;
    }
    
    /**Responsável por atualizar o jogador de um frame para o próximo frame, atualizando a posição e status dele.
     * @param dir String que indica um comando do jogador:
     *  a - Move para esquerda
     *  d - Move para a direita
     *  Espaço - Atira
     *  x - Fecha o jogo 
     *  @return 0 indica que o jogo continua normalmente, 1 indica que o jogo deve acabar.
     * @author Gustavo Moura
     */
    private int updatePlayer(String dir){
        
        if(cannon.getLife() == 0)
            return 1;
         
        mapCell[cannon.getPosX()][cannon.getPosY()].setPlayer(false);
        if(dir.equals("a")){
            if(cannon.getPosX() > 0)
                cannon.move(-1);
        }else if(dir.equals("d")){
            if(cannon.getPosX() < sizeX - 1)
                cannon.move(1);
        }else if(dir.equals(" ")){
            if(cannonShot.getLife() == 0){
                shot(cannon.getPosX(),cannon.getPosY(),true);
            }
        }
        if(cannonShot.getLife() == 1){
            shotMove(cannonShot);
        }
        mapCell[cannon.getPosX()][cannon.getPosY()].setPlayer(true);  
        return 0;
    }

    /**Responsável por atualizar os inimigos de um frame para o próximo frame, atualizando a posição e status deles.
     * Além dos inimigos em si, atualiza o tiro deles.
     * @return 0 indica que o jogo continua normalmente, 1 indica que o jogo deve acabar.
     * @author Gustavo Moura
     */
    private int updateInvaders(){
        invadersWalk++;
        if(invadersWalk == 1){
            invadersWalk = 0;
            for(int x = 0;x<invadersX;x++)
                for(int y = 0;y<invadersY;y++)
                    mapCell[invaders[x][y].getPosX()][invaders[x][y].getPosY()].setInvader(false);

            boolean hasToMoveDown = false;
            
            for(int x = 0;x<invadersX && hasToMoveDown == false ;x++)
                for(int y = 0;y<invadersY && hasToMoveDown == false;y++){ 
                    if(invaders[x][y].getPosX() + invadersDir > sizeX-1 || invaders[x][y].getPosX() + invadersDir < 0){
                        hasToMoveDown = true;
                        invadersDir *= -1;
                    }
                    
                    if(invaders[x][y].getPosY() == sizeY-2)
                        return 1;
                }

            for(int x = 0;x<invadersX;x++)
                for(int y = 0;y<invadersY;y++){ 
                    if(hasToMoveDown == true){
                        invaders[x][y].moveDown();
                    }else{
                        invaders[x][y].move(invadersDir);
                    }
                    if(invaders[x][y].getLife() > 0){
                        mapCell[invaders[x][y].getPosX()][invaders[x][y].getPosY()].setInvader(true);
                    }
                }

            if(hasToMoveDown == true){
                invadersOffsetY++;
            }else{
                invadersOffsetX+= invadersDir;
            }
        }
        
        //INIMIGOS ATIRAM
        if(invaderShot.getLife() == 0){

            invaderShot.switchIsRand();
            int invToShotX;
            int invToShotY;

            Random rn = new Random();
            invToShotY = rn.nextInt(invadersY - 0) + 0;
            if(!invaderShot.isRand() && cannon.getPosX() - invadersOffsetX < invadersX){
                invToShotX = cannon.getPosX();
                shot(invToShotX,invToShotY+invadersOffsetY,false);
            }else{
                invToShotX = rn.nextInt(invadersX - 0) + 0;
                shot(invToShotX + invadersOffsetX,invToShotY+invadersOffsetY,false);
            }

        }else{
            shotMove(invaderShot);
        }
        
        return 0;
    }
    
    /**Responsável por atualizar as barreiras de um frame para o próximo frame, atualizando o status delas.
     * @author Gustavo Moura
     */
    private void updateBarriers(){
        int barrierCountX = 0;
        int barrierCountY = 0;
        for(int j = sizeY-4;j<sizeY-2;j++){
            barrierCountX = 0;
            for(int i = 0;i<sizeX;i++){
                if(i%4 == 1){
                    if(barriers[barrierCountX][barrierCountY].getLife() == 0){
                        mapCell[i][j].setBarrier(false);   
                    }
                    barrierCountX++;
                }
            }
            barrierCountY++;
        }  
    }
    
    /**Responsável por chamar o método de "spawnar" o tiro quando o jogador dá o comando para tal ou quanndo um inimigo precisa atirar.
     * @param dir String que indica um comando do jogador:
     * @param x Posição em X inicial do tiro.
     * @param y Posição em Y inicial do tiro.
     * @param fromPlayer Indica se o tiro é de um jogador (true) ou inimigo (false).
     * @author Gustavo Moura
     */
    private void shot(int x,int y,boolean fromPlayer){
            if(fromPlayer){
                cannonShot.spawnShot(x, y);
            }else{
                invaderShot.spawnShot(x, y);
            }
            mapCell[x][y].setShot(true,fromPlayer);
    }

    /**Responsável por chamar o método de mover o tiro e verifica se o tiro bateu algo, como em uma barreira,
     * no jogador, caso o tiro seja do inimigo ou em um inimigo, caso o tiro seja do jogador, também se o tiro saiu do mapa.
     * @param shot O tiro a ser movido
     * @author Gustavo Moura
     */
    private void shotMove(Shot shot){

        if(!shot.isFromPlayer()){
            if(mapCell[shot.getPosX()][shot.getPosY()].getCellInfo()==8){
                cannon.reduceLife();
                mapCell[shot.getPosX()][shot.getPosY()].setShot(false,false);
                shot.reduceLife();
                return;
            }
            if(shot.getPosY() == sizeY-1){
                mapCell[shot.getPosX()][shot.getPosY()].setShot(false,false);
                shot.reduceLife();
                return;
            }
        }
        if(mapCell[shot.getPosX()][shot.getPosY()].getCellInfo()==5){
            barriers[shot.getPosX()/4][shot.getPosY()%2].reduceLife();
            mapCell[shot.getPosX()][shot.getPosY()].setShot(false,shot.isFromPlayer());
            shot.reduceLife();
            return;
        }

        if(shot.isFromPlayer()){
            if(mapCell[shot.getPosX()][shot.getPosY()].getCellInfo() == 6){
                mapCell[shot.getPosX()][shot.getPosY()].setShot(false,true);
                invaders[shot.getPosX()-invadersOffsetX][shot.getPosY()-invadersOffsetY].reduceLife();
                shot.reduceLife();
                cannon.setScore(cannon.getScore()+10);
                return;
            }
            if(shot.getPosY() == 1){
                mapCell[shot.getPosX()][shot.getPosY()].setShot(false,false);
                shot.reduceLife();
                return;
            }
        }

        mapCell[shot.getPosX()][shot.getPosY()].setShot(false,shot.isFromPlayer());
        shot.move();
        mapCell[shot.getPosX()][shot.getPosY()].setShot(true,shot.isFromPlayer());
    }
    
    /**Percorre as células (Sistema de coordenadas) do mapa e baseado na informação coletada de cada uma (o que tem em cada uma), printa um caracter
     * imprime um caracter referente ao estado. Por exemplo:
     *   ▲ -  Caso nessa célula haja o jogador.
     *  ███ -  Caso nessa célula haja um barreira.
     *  etc.
     * @author Gustavo Moura
     */
    public void printMap(){
        for(int y = 0;y<sizeY;y++){
            for(int x = 0;x<sizeX;x++){  
                switch(mapCell[x][y].getCellInfo()){
                    case 0:
                        System.out.print("   ");
                        break;
                    case 1:
                        System.out.print(" ▲ ");
                        break;
                    case 2:
                        System.out.print(invaders[x-invadersOffsetX][y-invadersOffsetY].getSprite());
                        break;
                    case 3:
                        switch (barriers[x/4][y%2].getLife()){
                            case 4:
                                System.out.print("███");
                                break;
                            case 3:
                                System.out.print("▙▙▜");
                                break;
                            case 2:
                                System.out.print("▚▝▚");
                                break;
                            case 1:
                                System.out.print("▗▝▖");
                                break;
                        }
                        break;
                    case 4:
                        System.out.print(" ▴ ");
                        break;
                    case 5: case 6: case 8:
                        System.out.print("░░░");
                        break;
                    case 7:
                        System.out.print(" ▿ ");
                        break;
                }
            }
            System.out.println("|");
        }   
        for(int x = 0;x<sizeX*3;x++){
            System.out.print("-");
        }
        System.out.println("-");
    }
    
    /**Método responsável por imprimir a pontuação e a vida do jogador.
     * 
     * @author Gustavo Moura
     */
    public void printPlayerStatus(){
        System.out.println("SCORE:" + cannon.getScore());
        System.out.print("LIFES: ");
        System.out.print(cannon.getLife() + " ");
        for(int i = 0;i<cannon.getLife();i++){
            System.out.print("❤ ");
        }
        System.out.println();       
    }
}
