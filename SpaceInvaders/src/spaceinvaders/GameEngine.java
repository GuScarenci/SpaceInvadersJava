package spaceinvaders;

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
        this.mapCell = new MapCell[sizeX][sizeY];
        
        for(int y = 0;y<this.sizeY;y++){
            for(int x = 0;x<this.sizeX;x++){
                this.mapCell[x][y] = new MapCell();
            }
        }

        //put barrier
        this.barriers = new Barrier[4][2];
        int barrierCountX = 0;
        int barrierCountY = 0;
        for(int j = sizeY-4;j<sizeY-2;j++){
            barrierCountX = 0;
            for(int i = 0;i<sizeX;i++){
                if(i%4 == 1){
                    this.mapCell[i][j].SetBarrier(true);
                    barriers[barrierCountX][barrierCountY] = new Barrier(i, j);
                    barrierCountX++;
                }
            }
            barrierCountY++;
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
        this.randShot = 1;

        for(int y = 0;y<invadersY;y++){
            for(int x = 0;x<invadersX;x++){
                this.invaders[x][y] = new Invader(x,y);
                this.mapCell[x][y].SetInvader(true);
            }
        }   
        this.invadersWalk = 1;
    }
    
    public int UpdateGame(String dir){

        if(this.cannon.life == 0){
            return 1;
        }

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
                Shot(this.cannon.posX,this.cannon.posY,true);
            }
        }
        if(cannonShotInScreen == true){
            ShotMove();
        }
        this.mapCell[cannon.posX][cannon.posY].SetPlayer(true);

        //NAVES INIMIGAS
        invadersWalk++;
        if(invadersWalk == 3){
            invadersWalk = 0;
            for(int x = 0;x<invadersX;x++)
                for(int y = 0;y<invadersY;y++)
                    this.mapCell[this.invaders[x][y].posX][this.invaders[x][y].posY].SetInvader(false);

            boolean hasToMoveDown = false;
            for(int x = 0;x<invadersX && hasToMoveDown == false ;x++)
                for(int y = 0;y<invadersY && hasToMoveDown == false;y++){ 
                    if(this.invaders[x][y].posX + this.invadersDir > sizeX-1 || this.invaders[x][y].posX + this.invadersDir < 0){
                        hasToMoveDown = true;
                        this.invadersDir *= -1;
                    }
                    if(this.invaders[x][y].posY == sizeY-2)
                        return 1;
                }

            for(int x = 0;x<invadersX;x++)
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
            if(this.randShot == -1 && this.invaders[this.cannon.posX - this.invadersOffsetX][invToShotY] != null){
                invToShotX = this.cannon.posX;
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
                    if(this.barriers[barrierCountX][barrierCountY].life == 0){
                        this.mapCell[i][j].SetBarrier(false);   
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
            this.shotPosX = x;
            this.shotPosY = y;
            this.mapCell[x][y].SetShot(true);
            this.cannonShotInScreen = true;
        }else{
            this.invShotPosX = x;
            this.invShotPosY = y;
            this.mapCell[x][y].SetInvaderShot(true);
            this.invadersShotInScreen = true;
        }
    }

    public void InvaderShotMove(){
        if(this.mapCell[invShotPosX][invShotPosY].GetCellInfo() == 8){
            this.cannon.ReduceLife();
            this.mapCell[invShotPosX][invShotPosY].SetInvaderShot(false);
            invadersShotInScreen = false;
            return;
        }
        if(invShotPosY == sizeY-1){
            this.mapCell[this.invShotPosX][this.invShotPosY].SetInvaderShot(false);
            invadersShotInScreen = false;
            return;
        }
        if(this.mapCell[invShotPosX][invShotPosY].GetCellInfo() == 5){
            this.barriers[invShotPosX/4][invShotPosY%2].ReduceLife();
            this.mapCell[invShotPosX][invShotPosY].SetInvaderShot(false);
            invadersShotInScreen = false;
            return;
        }
        this.mapCell[invShotPosX][invShotPosY].SetInvaderShot(false);
        this.invShotPosY++;
        this.mapCell[invShotPosX][invShotPosY].SetInvaderShot(true);
    }

    public void ShotMove(){
        if(this.mapCell[shotPosX][shotPosY].GetCellInfo() == 6){
            this.mapCell[shotPosX][shotPosY].SetShot(false);
            this.invaders[shotPosX-invadersOffsetX][shotPosY-invadersOffsetY].ReduceLife();
            cannonShotInScreen = false;
            cannon.score += 10;
            return;
        }
        if(shotPosY == 1){
            this.mapCell[shotPosX][shotPosY].SetShot(false);
            cannonShotInScreen = false;
            return;
        }
        if(this.mapCell[shotPosX][shotPosY].GetCellInfo() == 5){
            this.barriers[shotPosX/4][shotPosY%2].ReduceLife();
            this.mapCell[shotPosX][shotPosY].SetShot(false);
            cannonShotInScreen = false;
            return;
        }
        this.mapCell[shotPosX][shotPosY].SetShot(false);
        shotPosY--;
        this.mapCell[shotPosX][shotPosY].SetShot(true);  
    }
    
    public void printMap(){
        for(int y = 0;y<this.sizeY;y++){
            for(int x = 0;x<this.sizeX;x++){  
                switch(this.mapCell[x][y].GetCellInfo()){
                    case 0:
                        System.out.print("   ");
                        break;
                    case 1:
                        System.out.print(" A ");
                        break;
                    case 2:
                        System.out.print(this.invaders[x-invadersOffsetX][y-invadersOffsetY].sprite);
                        break;
                    case 3:
                        switch (this.barriers[x/4][y%2].life){
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
        for(int x = 0;x<this.sizeX*3;x++){
            System.out.print("-");
        }
        System.out.println("-");
    }

    public void printPlayerStatus(){
        System.out.println("SCORE:" + cannon.score);
        System.out.print("LIFES: ");
        System.out.print(cannon.life + " ");
        for(int i = 0;i<cannon.life;i++){
            System.out.print("█ ");
        }
        System.out.println();       
    }
}
