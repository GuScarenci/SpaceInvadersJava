package spaceinvaders;

public class Map {
    MapCell[][] mapCell;
    Invader[][] invaders;

    int invadersDir;

    Cannon cannon;

    int sizeX,sizeY;

    int ex,ey;
    
    public Map(int sizeX, int sizeY){
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.mapCell = new MapCell[sizeX][sizeY];
        
        for(int y = 0;y<this.sizeY;y++){
            for(int x = 0;x<this.sizeX;x++){
                this.mapCell[x][y] = new MapCell();
            }
        }
        
        //put player
        this.cannon = new Cannon((sizeX-1)/2,sizeY-1);
        this.mapCell[(sizeX-1)/2][sizeY-1].SetPlayer(true);


        //put enemies
        ex = 6;
        ey = 6;
        this.invaders = new Invader[ex][ey];
        this.invadersDir = 1;


        for(int y = 0;y<ey;y++){
            for(int x = 0;x<ex;x++){
                this.invaders[x][y] = new Invader(x,y);
                this.mapCell[x][y].SetInvader(true);
            }
        }   
    }
    
    public void printGame(){

        for(int y = 0;y<this.sizeY;y++){
            for(int x = 0;x<this.sizeX;x++){
                
                if(this.mapCell[x][y].GetCellInfo() == 0){
                    System.out.print("  "/*"+x+""+y+*/);
                }else if(this.mapCell[x][y].GetCellInfo() == 1){
                    System.out.print("🚀");
                }else if(this.mapCell[x][y].GetCellInfo() == 2){
                    System.out.print("👾");
                }else if(this.mapCell[x][y].GetCellInfo() == 3){
                    System.out.print("[F]");
                }
                
            }
    
            System.out.println("|");
        }
        
        for(int x = 0;x<this.sizeX*2;x++){
            System.out.print("-");
        }
        System.out.println("-");
    }
    
    public void UpdateMap(){

        boolean hasToMoveDown = false;

        for(int x = 0;x<ex;x++){
            for(int y = 0;y<ey;y++){ 
                this.mapCell[this.invaders[x][y].posX][this.invaders[x][y].posY].SetInvader(false);

                if(this.invaders[x][y].posX + this.invadersDir > sizeX-1 || this.invaders[x][y].posX + this.invadersDir < 0){
                    hasToMoveDown = true;
                    this.invadersDir *= -1;
                }
            }
        }

        for(int x = 0;x<ex;x++){
            for(int y = 0;y<ey;y++){ 
                if(hasToMoveDown == true){
                    this.invaders[x][y].MoveDown();
                }else{
                    this.invaders[x][y].Move(this.invadersDir);
                }
                this.mapCell[this.invaders[x][y].posX][this.invaders[x][y].posY].SetInvader(true);
            }
        }
    }
}
