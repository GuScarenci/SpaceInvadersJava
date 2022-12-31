package elements;

import javafx.scene.paint.Color;

/**
 *
 * @author gustavoscarenci
 */
public class Enemy extends Sprite{
    
    public Enemy(int x, int y, int w, int h, String type, Color color){
        super( x, y, w, h, type, color);
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
