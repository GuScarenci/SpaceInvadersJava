package spaceinvaders;

public class MapCell{
    boolean hasAnInvader;
    boolean hasAPlayer;
    boolean hasABarrier;
    
    public MapCell(){
        this.hasAnInvader = false;
        this.hasAPlayer = false;
    }
    
    public int GetCellInfo(){
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
    
}
