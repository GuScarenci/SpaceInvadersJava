/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elements;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author gustavoscarenci
 */
public class Sprite extends Rectangle{
    public boolean dead = false;
    final String type;
    
    Sprite(int x, int y, int w, int h, String type, Color color){
        super(w,h,color);
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
