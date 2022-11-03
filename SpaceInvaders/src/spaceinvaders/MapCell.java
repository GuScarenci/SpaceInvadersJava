package spaceinvaders;

public class MapCell{
    boolean hasAnInvader;
    boolean hasAPlayer;
    boolean hasABarrier;
    boolean hasAShot;
    String sprite;
    
    public MapCell(){
        this.hasAnInvader = false;
        this.hasAPlayer = false;
        this.hasABarrier = false;
        this.hasAShot = false;
    }
    
    public int GetCellInfo(){
        if(this.hasAShot){
            if(this.hasABarrier){
                return 6;
            }
            if(this.hasAnInvader){
                return 7;
            }
            return 5;
        }
        if(this.hasABarrier){
            return 4;
        }
        if(this.hasAPlayer && this.hasAnInvader){
            return 3;
        }else if(this.hasAPlayer || this.hasAnInvader){
            if(this.hasAPlayer){
                return 1;
            }
            if(this.hasAnInvader){
                return 2;
            }    
        }
        return 0;
        
    }

    public void SetBarrier(boolean x){
        this.hasABarrier = x;
    }
    
    public void SetInvader(boolean x){
        this.hasAnInvader = x;
    }
    
    public void SetPlayer(boolean x){
        this.hasAPlayer = x;
    }

    public void SetShot(boolean x){
        this.hasAShot = x;
    }
    
}
