package spaceinvaders;

import java.nio.file.FileAlreadyExistsException;

public class Game {
    MapCell[][] mapCell;
    Invader[][] invaders;


    Cannon cannon;
    boolean cannonShotInScreen;
    int shotPosX,shotPosY;

    int sizeX,sizeY;

    int invadersX,invadersY;

    int invadersDir;
    int invadersWalk;

    int invadersOffsetX, invadersOffsetY;

    public Game(int sizeX, int sizeY){
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.mapCell = new MapCell[sizeX][sizeY];
        
        for(int y = 0;y<this.sizeY;y++){
            for(int x = 0;x<this.sizeX;x++){
                this.mapCell[x][y] = new MapCell();
            }
        }

        //put barrier
        for(int i = 0;i<sizeX;i++){
            if(i%4 == 1){
                this.mapCell[i][17].SetBarrier(true);
                this.mapCell[i][16].SetBarrier(true);
            }
        }
        
        //put player
        this.shotPosY = 0;
        this.shotPosX = 0;
        this.cannon = new Cannon((sizeX-1)/2,sizeY-1);
        this.mapCell[(sizeX-1)/2][sizeY-1].SetPlayer(true);
        this.cannonShotInScreen = false;


        //put enemies
        invadersX = 11;
        invadersY = 5;
        this.invaders = new Invader[invadersX][invadersY];
        this.invadersDir = 1;
        this.invadersOffsetX = 0; 
        this.invadersOffsetY = 0;


        for(int y = 0;y<invadersY;y++){
            for(int x = 0;x<invadersX;x++){
                this.invaders[x][y] = new Invader(x,y);
                this.mapCell[x][y].SetInvader(true);
            }
        }   
        this.invadersWalk = 1;
    }
    
    public void printMap(){

        for(int y = 0;y<this.sizeY;y++){
            for(int x = 0;x<this.sizeX;x++){
                
                if(this.mapCell[x][y].GetCellInfo() == 0){
                    System.out.print("   ");
                }else if(this.mapCell[x][y].GetCellInfo() == 1){
                    System.out.print(" A ");
                }else if(this.mapCell[x][y].GetCellInfo() == 2){
                    System.out.print(this.invaders[x-invadersOffsetX][y-invadersOffsetY].sprite);
                }else if(this.mapCell[x][y].GetCellInfo() == 3){
                    System.out.print("[F]");
                }else if(this.mapCell[x][y].GetCellInfo() == 4){
                    System.out.print("^^^");
                }else if(this.mapCell[x][y].GetCellInfo() == 5){
                    System.out.print(" | ");
                }else if(this.mapCell[x][y].GetCellInfo() == 6){
                    System.out.print(" # ");
                }else if(this.mapCell[x][y].GetCellInfo() == 7){
                    System.out.print(" # ");
                }  

            }
    
            System.out.println("|");
        }
        
        for(int x = 0;x<this.sizeX*3;x++){
            System.out.print("-");
        }
        System.out.println("-");
    }
    
    public int UpdateMap(String dir){

        //PLAYER
        this.mapCell[cannon.posX][cannon.posY].SetPlayer(false);
        if(dir.equals("a")){
            if(cannon.posX > 0)
            this.cannon.Move(-1);
        }else if(dir.equals("d")){
            if(cannon.posX < sizeX - 1)
            this.cannon.Move(1);
        }else if(dir.equals(" ")){
            if(cannonShotInScreen == false){
                Shot();
            }
        }
        if(cannonShotInScreen == true){
            ShotMove();
        }
        this.mapCell[cannon.posX][cannon.posY].SetPlayer(true);

        //NAVES INIMIGAS
        invadersWalk *= 1;
        if(invadersWalk == 1){
            boolean hasToMoveDown = false;

            for(int x = 0;x<invadersX;x++){
                for(int y = 0;y<invadersY;y++){ 
                    this.mapCell[this.invaders[x][y].posX][this.invaders[x][y].posY].SetInvader(false);

                    if(this.invaders[x][y].posX + this.invadersDir > sizeX-1 || this.invaders[x][y].posX + this.invadersDir < 0){
                        hasToMoveDown = true;
                        this.invadersDir *= -1;
                    }
                    if(this.invaders[x][y].posY == sizeY-1){
                        return 1;
                    }
                }
            }

            for(int x = 0;x<invadersX;x++){
                for(int y = 0;y<invadersY;y++){ 
                    if(hasToMoveDown == true){
                        this.invaders[x][y].MoveDown();
                    }else{
                        this.invaders[x][y].Move(this.invadersDir);
                    }

                    if(this.invaders[x][y].life > 0){
                        this.mapCell[this.invaders[x][y].posX][this.invaders[x][y].posY].SetInvader(true);
                    }
                }
            }

            if(hasToMoveDown == true){
                invadersOffsetY++;
            }else{
                invadersOffsetX+= invadersDir;
            }
        }
        return 0;
    }

    /*public void CheckAllInvaders(){
        for(int x = 0;x<invadersX;x++){
            for(int y = 0;y<invadersY;y++){

            }
        }
    }*/

    public void Shot(){
        this.shotPosX = cannon.posX;
        this.shotPosY = cannon.posY;
        this.mapCell[shotPosX][shotPosY].SetShot(true);
        this.cannonShotInScreen = true;   
    }

    public void ShotMove(){

        if(shotPosY == 1){
            this.mapCell[shotPosX][shotPosY].SetShot(false);
            cannonShotInScreen = false;
            return;
        }

        if(this.mapCell[shotPosX][shotPosY].GetCellInfo() == 7){

            this.mapCell[shotPosX][shotPosY].SetShot(false);
            this.invaders[shotPosX-invadersOffsetX][shotPosY-invadersOffsetY].ReduceLife();

            cannonShotInScreen = false;
            cannon.score += 10;
            return;
        }

        this.mapCell[shotPosX][shotPosY].SetShot(false);
        shotPosY--;
        this.mapCell[shotPosX][shotPosY].SetShot(true);  
    }
    
    public void printPlayerStatus(){
        System.out.println("SCORE:" + cannon.score);
        System.out.print("LIFES: ");
        for(int i =0;i<cannon.life;i++){
            System.out.print("[] ");
        }
        System.out.println();       
    }
}
