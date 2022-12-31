package elements;

import javafx.scene.paint.Color;

/**
 *
 * @author gustavoscarenci
 */
public class Shot extends GameObject{
    
    public Shot(int x, int y, int w, int h, String type, String file){
        super( x, y, w, h, type, file,1);
    }
}
