package engine;
import elements.*;

import java.util.Random;

/**
 * 
 * @author Gustavo Moura
 */
public class GameEngine{

    //MapVariables
    MapCell[][] mapCell;
    int sizeX,sizeY;

    //Cannon(Player)Variabless
    Cannon cannon;
    Shot cannonShot;

    //Cannon(Player)Variables 
    Barrier[][] barriers;

    //InvaderVariables 
    Invader[][] invaders;
    int invadersX,invadersY;
    int invadersDir;
    int invadersWalk;
    int invadersOffsetX, invadersOffsetY;
    Shot invaderShot;
    
    //Constructor 
    public GameEngine(int sizeX, int sizeY){
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        mapCell = new MapCell[sizeX][sizeY];
        
        for(int y = 0;y<this.sizeY;y++){
            for(int x = 0;x<this.sizeX;x++){
                mapCell[x][y] = new MapCell();
            }
        }

        //put barrier
        barriers = new Barrier[4][2];
        int barrierCountX = 0;
        int barrierCountY = 0;
        for(int j = sizeY-4;j<sizeY-2;j++){
            barrierCountX = 0;
            for(int i = 0;i<sizeX;i++){
                if(i%4 == 1){
                    mapCell[i][j].SetBarrier(true);
                    barriers[barrierCountX][barrierCountY] = new Barrier(i, j);
                    barrierCountX++;
                }
            }
            barrierCountY++;
        }
        
        //put player
        cannonShot = new Shot(0,0,true);

        cannon = new Cannon((sizeX-1)/2,sizeY-1);
        mapCell[(sizeX-1)/2][sizeY-1].SetPlayer(true);

        //put enemies
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
                mapCell[x][y].SetInvader(true);
            }
        }   
        invadersWalk = 0;
    }
    
    public int UpdateGame(String dir){

        if(cannon.GetLife() == 0){
            return 1;
        }

        //PLAYER
        mapCell[cannon.GetPosX()][cannon.GetPosY()].SetPlayer(false);
        if(dir.equals("a")){
            if(cannon.GetPosX() > 0)
                cannon.Move(-1);
        }else if(dir.equals("d")){
            if(cannon.GetPosX() < sizeX - 1)
                cannon.Move(1);
        }else if(dir.equals(" ")){
            if(cannonShot.GetLife() == 0){
                Shot(cannon.GetPosX(),cannon.GetPosY(),true);
            }
        }

        if(cannonShot.GetLife() == 1){
            ShotMove(cannonShot);
        }else{
        }

        mapCell[cannon.GetPosX()][cannon.GetPosY()].SetPlayer(true);

        //NAVES INIMIGAS
        invadersWalk++;
        if(invadersWalk == 1){
            invadersWalk = 0;
            for(int x = 0;x<invadersX;x++)
                for(int y = 0;y<invadersY;y++)
                    mapCell[invaders[x][y].GetPosX()][invaders[x][y].GetPosY()].SetInvader(false);

            boolean hasToMoveDown = false;
            for(int x = 0;x<invadersX && hasToMoveDown == false ;x++)
                for(int y = 0;y<invadersY && hasToMoveDown == false;y++){ 
                    if(invaders[x][y].GetPosX() + invadersDir > sizeX-1 || invaders[x][y].GetPosX() + invadersDir < 0){
                        hasToMoveDown = true;
                        invadersDir *= -1;
                    }
                    if(invaders[x][y].GetPosY() == sizeY-2)
                        return 1;
                }

            for(int x = 0;x<invadersX;x++)
                for(int y = 0;y<invadersY;y++){ 
                    if(hasToMoveDown == true){
                        invaders[x][y].MoveDown();
                    }else{
                        invaders[x][y].Move(invadersDir);
                    }
                    if(invaders[x][y].GetLife() > 0){
                        mapCell[invaders[x][y].GetPosX()][invaders[x][y].GetPosY()].SetInvader(true);
                    }
                }

            if(hasToMoveDown == true){
                invadersOffsetY++;
            }else{
                invadersOffsetX+= invadersDir;
            }
        }

        //INIMIGOS ATIRAM

        if(invaderShot.GetLife() == 0){

            invaderShot.SwitchIsRand();
            int invToShotX;
            int invToShotY;

            Random rn = new Random();
            invToShotY = rn.nextInt(invadersY - 0) + 0;
            if(!invaderShot.IsRand() && cannon.GetPosX() - invadersOffsetX < invadersX){
                invToShotX = cannon.GetPosX();
                Shot(invToShotX,invToShotY+invadersOffsetY,false);
            }else{
                invToShotX = rn.nextInt(invadersX - 0) + 0;
                Shot(invToShotX + invadersOffsetX,invToShotY+invadersOffsetY,false);
            }

        }else{
            ShotMove(invaderShot);
        }
        
        //BARREIRAS
        int barrierCountX = 0;
        int barrierCountY = 0;
        for(int j = sizeY-4;j<sizeY-2;j++){
            barrierCountX = 0;
            for(int i = 0;i<sizeX;i++){
                if(i%4 == 1){
                    if(barriers[barrierCountX][barrierCountY].GetLife() == 0){
                        mapCell[i][j].SetBarrier(false);   
                    }
                    barrierCountX++;
                }
            }
            barrierCountY++;
        }
        return 0;
    }

    public void Shot(int x,int y,boolean fromPlayer){
            if(fromPlayer){
                cannonShot.SpawnShot(x, y);
            }else{
                invaderShot.SpawnShot(x, y);
            }
            mapCell[x][y].SetShot(true,fromPlayer);
    }

    public void ShotMove(Shot shot){

        if(!shot.IsFromPlayer()){
            if(mapCell[shot.GetPosX()][shot.GetPosY()].GetCellInfo()==8){
                cannon.ReduceLife();
                mapCell[shot.GetPosX()][shot.GetPosY()].SetShot(false,false);
                shot.ReduceLife();
                return;
            }

            if(shot.GetPosY() == sizeY-1){
                mapCell[shot.GetPosX()][shot.GetPosY()].SetShot(false,false);
                shot.ReduceLife();
                return;
            }
        }

        if(mapCell[shot.GetPosX()][shot.GetPosY()].GetCellInfo()==5){
            barriers[shot.GetPosX()/4][shot.GetPosY()%2].ReduceLife();
            mapCell[shot.GetPosX()][shot.GetPosY()].SetShot(false,shot.IsFromPlayer());
            shot.ReduceLife();
            return;
        }

        if(shot.IsFromPlayer()){

            if(mapCell[shot.GetPosX()][shot.GetPosY()].GetCellInfo() == 6){
                mapCell[shot.GetPosX()][shot.GetPosY()].SetShot(false,true);
                invaders[shot.GetPosX()-invadersOffsetX][shot.GetPosY()-invadersOffsetY].ReduceLife();
                shot.ReduceLife();
                cannon.SetScore(cannon.GetScore()+10);
                return;
            }

            if(shot.GetPosY() == 1){
                mapCell[shot.GetPosX()][shot.GetPosY()].SetShot(false,false);
                shot.ReduceLife();
                return;
            }
        }

        mapCell[shot.GetPosX()][shot.GetPosY()].SetShot(false,shot.IsFromPlayer());
        shot.Move();
        mapCell[shot.GetPosX()][shot.GetPosY()].SetShot(true,shot.IsFromPlayer());
    }
    
    public void printMap(){
        for(int y = 0;y<sizeY;y++){
            for(int x = 0;x<sizeX;x++){  
                switch(mapCell[x][y].GetCellInfo()){
                    case 0:
                        System.out.print("   ");
                        break;
                    case 1:
                        System.out.print(" ▲ ");
                        break;
                    case 2:
                        System.out.print(invaders[x-invadersOffsetX][y-invadersOffsetY].GetSprite());
                        break;
                    case 3:
                        switch (barriers[x/4][y%2].GetLife()){
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

    public void printPlayerStatus(){
        System.out.println("SCORE:" + cannon.GetScore());
        System.out.print("LIFES: ");
        System.out.print(cannon.GetLife() + " ");
        for(int i = 0;i<cannon.GetLife();i++){
            System.out.print("❤ ");
        }
        System.out.println();       
    }
}
