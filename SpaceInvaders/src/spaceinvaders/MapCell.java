package spaceinvaders;

public class MapCell{
    boolean hasAnInvader;
    boolean hasAPlayer;
    boolean hasABarrier;
    boolean hasAShot;
    boolean hasAnInvaderShot;
    
    //Constructor
    public MapCell(){
    }
    
    public int GetCellInfo(){
        if(this.hasAnInvaderShot){
            if(this.hasABarrier){
                return 5;
            }
            if(this.hasAPlayer){
                return 8;
            }
            return 7;
        }
        if(this.hasAShot){
            if(this.hasAnInvader){
                return 6;
            }
            if(this.hasABarrier){
                return 5;
            }
            if(this.hasAShot){
                return 4;
            }
        }
        if(this.hasABarrier){
            return 3;
        }
        if(this.hasAPlayer || this.hasAnInvader){
            if(this.hasAnInvader){
                return 2;
            }    
            if(this.hasAPlayer){
                return 1;
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
    public void SetInvaderShot(boolean x){
        this.hasAnInvaderShot = x;
    }
    
}
