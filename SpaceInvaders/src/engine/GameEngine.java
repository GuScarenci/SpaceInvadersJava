package engine;
import elements.*;

import java.util.Random;

public class GameEngine{

    //MapVariables
    MapCell[][] mapCell;
    int sizeX,sizeY;

    //Cannon(Player)Variabless
    Cannon cannon;
    boolean cannonShotInScreen;
    int shotPosX,shotPosY;

    //Cannon(Player)Variables 
    Barrier[][] barriers;

    //InvaderVariables 
    Invader[][] invaders;
    int invadersX,invadersY;
    int invadersDir;
    int invadersWalk;
    int invadersOffsetX, invadersOffsetY;
    boolean invadersShotInScreen;
    int invShotPosX,invShotPosY;
    int randShot;
    
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
        shotPosY = 0;
        shotPosX = 0;
        cannon = new Cannon((sizeX-1)/2,sizeY-1);
        mapCell[(sizeX-1)/2][sizeY-1].SetPlayer(true);
        cannonShotInScreen = false;

        //put enemies
        invadersX = 11;
        invadersY = 5;
        invaders = new Invader[invadersX][invadersY];
        invadersDir = 1;
        invadersOffsetX = 0; 
        invadersOffsetY = 0;
        randShot = 1;

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
            if(cannonShotInScreen == false){
                Shot(cannon.GetPosX(),cannon.GetPosY(),true);
            }
        }
        if(cannonShotInScreen == true){
            ShotMove();
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
        if(invadersShotInScreen == false){
            randShot *= -1;
            int invToShotX;
            int invToShotY;
            Random rn = new Random();

            invToShotY = rn.nextInt(invadersY - 0) + 0;
            /**/if(randShot == -1 && cannon.GetPosX() - invadersOffsetX < invadersX){
                invToShotX = cannon.GetPosX();
                Shot(invToShotX,invToShotY+invadersOffsetY,false);
            }else{
                invToShotX = rn.nextInt(invadersX - 0) + 0;
                Shot(invToShotX + invadersOffsetX,invToShotY+invadersOffsetY,false);
            }
        }else{
            InvaderShotMove();
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
            shotPosX = x;
            shotPosY = y;
            mapCell[x][y].SetShot(true);
            cannonShotInScreen = true;
        }else{
            invShotPosX = x;
            invShotPosY = y;
            mapCell[x][y].SetInvaderShot(true);
            invadersShotInScreen = true;
        }
    }

    public void InvaderShotMove(){
        if(mapCell[invShotPosX][invShotPosY].GetCellInfo() == 8){
            cannon.ReduceLife();
            mapCell[invShotPosX][invShotPosY].SetInvaderShot(false);
            invadersShotInScreen = false;
            return;
        }
        if(invShotPosY == sizeY-1){
            mapCell[invShotPosX][invShotPosY].SetInvaderShot(false);
            invadersShotInScreen = false;
            return;
        }
        if(mapCell[invShotPosX][invShotPosY].GetCellInfo() == 5){
            barriers[invShotPosX/4][invShotPosY%2].ReduceLife();
            mapCell[invShotPosX][invShotPosY].SetInvaderShot(false);
            invadersShotInScreen = false;
            return;
        }
        mapCell[invShotPosX][invShotPosY].SetInvaderShot(false);
        invShotPosY++;
        mapCell[invShotPosX][invShotPosY].SetInvaderShot(true);
    }

    public void ShotMove(){
        if(mapCell[shotPosX][shotPosY].GetCellInfo() == 6){
            mapCell[shotPosX][shotPosY].SetShot(false);
            invaders[shotPosX-invadersOffsetX][shotPosY-invadersOffsetY].ReduceLife();
            cannonShotInScreen = false;
            cannon.SetScore(cannon.GetScore()+10);
            return;
        }
        if(shotPosY == 1){
            mapCell[shotPosX][shotPosY].SetShot(false);
            cannonShotInScreen = false;
            return;
        }
        if(mapCell[shotPosX][shotPosY].GetCellInfo() == 5){
            barriers[shotPosX/4][shotPosY%2].ReduceLife();
            mapCell[shotPosX][shotPosY].SetShot(false);
            cannonShotInScreen = false;
            return;
        }
        mapCell[shotPosX][shotPosY].SetShot(false);
        shotPosY--;
        mapCell[shotPosX][shotPosY].SetShot(true);  
    }
    
    public void printMap(){
        for(int y = 0;y<sizeY;y++){
            for(int x = 0;x<sizeX;x++){  
                switch(mapCell[x][y].GetCellInfo()){
                    case 0:
                        System.out.print("   ");
                        break;
                    case 1:
                        System.out.print(" A ");
                        break;
                    case 2:
                        System.out.print(invaders[x-invadersOffsetX][y-invadersOffsetY].GetSprite());
                        break;
                    case 3:
                        switch (barriers[x/4][y%2].GetLife()){
                            case 4:
                                System.out.print("000");
                                break;
                            case 3:
                                System.out.print("OOO");
                                break;
                            case 2:
                                System.out.print("ooo");
                                break;
                            case 1:
                                System.out.print("ooo");
                                break;
                        }
                        break;
                    case 4:
                        System.out.print(" ^ ");
                        break;
                    case 5: case 6: case 8:
                        System.out.print(" # ");
                        break;
                    case 7:
                        System.out.print(" Y ");
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
            System.out.print("█ ");
        }
        System.out.println();       
    }
}
