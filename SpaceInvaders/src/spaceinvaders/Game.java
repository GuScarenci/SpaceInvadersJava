package spaceinvaders;

public class Game {
    MapCell[][] mapCell;
    Invader[][] invaders;


    Cannon cannon;

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
        this.cannon = new Cannon((sizeX-1)/2,sizeY-1);
        this.mapCell[(sizeX-1)/2][sizeY-1].SetPlayer(true);


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
                }          
            }
    
            System.out.println("|");
        }
        
        for(int x = 0;x<this.sizeX*3;x++){
            System.out.print("-");
        }
        System.out.println("-");
    }
    
    public void UpdateMap(String dir){

        //PLAYER
        this.mapCell[cannon.posX][cannon.posY].SetPlayer(false);
        if(dir.equals("a")){
            if(cannon.posX > 0)
            this.cannon.Move(-1);
        }else if(dir.equals("d")){
            if(cannon.posX < sizeX - 1)
            this.cannon.Move(1);
        }else if(dir.equals(" ")){
            System.out.println("TIRO");
        }
        this.mapCell[cannon.posX][cannon.posY].SetPlayer(true);

        //NAVES INIMIGAS
        invadersWalk *= -1;
        if(invadersWalk == 1){
            boolean hasToMoveDown = false;

            for(int x = 0;x<invadersX;x++){
                for(int y = 0;y<invadersY;y++){ 
                    this.mapCell[this.invaders[x][y].posX][this.invaders[x][y].posY].SetInvader(false);

                    if(this.invaders[x][y].posX + this.invadersDir > sizeX-1 || this.invaders[x][y].posX + this.invadersDir < 0){
                        hasToMoveDown = true;
                        this.invadersDir *= -1;
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
                    this.mapCell[this.invaders[x][y].posX][this.invaders[x][y].posY].SetInvader(true);
                }
            }

            if(hasToMoveDown == true){
                invadersOffsetY++;
            }else{
                invadersOffsetX+= invadersDir;
            }
        }
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
