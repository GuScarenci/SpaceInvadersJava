package elements;

import javafx.scene.paint.Color;

/**
 *
 * @author gustavoscarenci
 */
public class Cannon extends Sprite{
    
    public Cannon(int x, int y, int w, int h, String type, Color color){
        super( x, y, w, h, type, color);
    }
    
    @Override
    public void moveLeft(){
        setTranslateX(getTranslateX()-5);
    }
    
    @Override
    public void moveRight(){
        setTranslateX(getTranslateX()+5);
    }
}
