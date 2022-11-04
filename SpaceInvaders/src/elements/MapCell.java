package elements;

public class MapCell{
    private boolean hasAnInvader;
    private boolean hasAPlayer;
    private boolean hasABarrier;
    private boolean hasAShot;
    private boolean hasAnInvaderShot;
    
    //Constructor
    public MapCell(){
    }
    
    public int GetCellInfo(){
        if(hasAnInvaderShot){
            if(hasABarrier){
                return 5;
            }
            if(hasAPlayer){
                return 8;
            }
            return 7;
        }
        if(hasAShot){
            if(hasAnInvader){
                return 6;
            }
            if(hasABarrier){
                return 5;
            }
            if(hasAShot){
                return 4;
            }
        }
        if(hasABarrier){
            return 3;
        }
        if(hasAPlayer || hasAnInvader){
            if(hasAnInvader){
                return 2;
            }    
            if(hasAPlayer){
                return 1;
            }
        }
        return 0;
    }

    public void SetBarrier(boolean x){
        hasABarrier = x;
    }
    
    public void SetInvader(boolean x){
        hasAnInvader = x;
    }
    
    public void SetPlayer(boolean x){
        hasAPlayer = x;
    }

    public void SetShot(boolean x,boolean fromPlayer){
        if(fromPlayer){
            hasAShot = x;
        }else{
            hasAnInvaderShot = x;
        }
    }
}
