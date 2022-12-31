/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elements;

import com.sun.javafx.iio.ImageStorage;
import java.io.IOException;
import java.io.InputStream;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author gustavoscarenci
 */
public class GameObject extends ImageView{
    public boolean dead = false;
    final String type;
    
    /*GameObject(int x, int y, int w, int h, String type, Color color){
        super(w,h,color);
        this.type = type;
        setTranslateX(x);
        setTranslateY(y);
    }*/

    public GameObject(int x, int y, int w, int h, String type, String file) {
        InputStream is = null;
        try {
            is = GameObject.class.getResource(file).openStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image image = new Image(is, w, h, false, false);
        setImage(image);
        
        this.type = type;
        setTranslateX(x);
        setTranslateY(y);
    }
    
    
    public void moveLeft(){
        setTranslateX(getTranslateX()-5);
    }
    
    public void moveRight(){
        setTranslateX(getTranslateX()+5);
    }
    
    public void moveUp(){
        setTranslateY(getTranslateY()-0.2);
    }
    
    public void moveDown(){
        setTranslateY(getTranslateY()+0.2);
    }
    
    public String getType(){
        return type;
    }
    
}
