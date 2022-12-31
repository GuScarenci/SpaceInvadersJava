package elements;

import javafx.scene.paint.Color;

/**
 *
 * @author gustavoscarenci
 */
public class Enemy extends GameObject{
   
    int typeEnemy;
    
    public Enemy(int x, int y, int w, int h, String type, String file,int typeEnemy){
        super( x, y, w, h, type, file,1,typeEnemy);
    }
    
    @Override
    public void moveLeft(){
        setTranslateX(getTranslateX()-0.05);
    }
    
    @Override
    public void moveRight(){
        setTranslateX(getTranslateX()+0.05);
    }
    
    @Override
    public void moveDown(){
        setTranslateY(getTranslateY()+40);
    }
    
}
