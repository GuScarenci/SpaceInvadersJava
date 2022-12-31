package elements;

import javafx.scene.paint.Color;

/**
 *
 * @author gustavoscarenci
 */
public class Cannon extends GameObject{
    
    public Cannon(int x, int y, int w, int h, String type, String file){
        super( x, y, w, h, type, file);
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
