package javafxapplication13;
import elements.*;

import java.util.List;
import java.util.stream.Collectors;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author gustavoscarenci
 */
public class JavaFXApplication13 extends Application {
    
    final private Pane root  =  new Pane();
    
    int enemyMovingSide = 1;
    boolean moveDown = false;
    
    private double t = 0;
    
    final private Cannon cannon = new Cannon(300,550,40,40,"cannon",Color.BLUE);
   
    private Parent createContent(){
        root.setPrefSize(800,600);
        
        root.getChildren().add(cannon);
        
        AnimationTimer timer = new AnimationTimer(){
            @Override
            public void handle(long now){
                update();
            }
        };
        
        timer.start();
        
        nextLevel();
        
        return root;
    }
    
    private void nextLevel(){
        
        for(int i = 0; i < 11; i++){
            Sprite s = new Enemy(30 + i*40, 40, 30, 30, "enemy", Color.RED);
            root.getChildren().add(s);
        }
        
        for(int i = 0; i< 2; i++){
            for(int j = 0; j < 11; j++){
                Sprite s = new Enemy(30 + j*40, 80 + i* 40, 30, 30, "enemy", Color.YELLOW);
                root.getChildren().add(s);
            }
        }
        
        for(int i = 0; i< 2; i++){
            for(int j = 0; j < 11; j++){
                Sprite s = new Enemy(30 + j*40, 160 + i* 40, 30, 30, "enemy", Color.GREEN);
                root.getChildren().add(s);
            }
        }
    }
    
    private List<Sprite> sprites(){
    return root.getChildren().stream().map(n -> (Sprite)n).collect(Collectors.toList());}
    
    private void update(){
        
        t+=0.016;
        
        sprites().forEach(s -> {
            if(s.getType().equals("enemy")){
                if(s.getTranslateX() <= 0){
                    enemyMovingSide = 1;
                    moveDown = true;
                }else if(s.getTranslateX() >= 800){
                    enemyMovingSide = -1;
                    moveDown = true;
                }
            }
        });
        
        sprites().forEach(s -> {
            if(s.getType().equals("enemy")){
                if(moveDown == true){
                    s.moveDown();
                }
            }
        });
        moveDown = false;
        
        sprites().forEach(s -> {
            switch (s.getType()) {
                
                case "enemybullet":
                    s.moveDown();
                    if(s.getBoundsInParent().intersects(cannon.getBoundsInParent())){
                        cannon.dead =  true;
                        s.dead = true;
                    }                    
                    break;
                
                case "cannonbullet":
                    s.moveUp();
                    sprites().stream().filter(e ->  e.getType().equals("enemy")).forEach(enemy-> {
                    if(s.getBoundsInParent().intersects(enemy.getBoundsInParent())){
                        enemy.dead = true;
                        s.dead =  true;
                    }
                    });
                    break;
                    
                case "enemy":
                    if(enemyMovingSide == 1){
                        s.moveRight();
                    }else if(enemyMovingSide == -1){
                        s.moveLeft();
                    }

                    if(t>2){
                        if(Math.random() < 0.001){
                            shoot(s);
                        }
                    }
                    
                    break;
            }
 
        });
        
        root.getChildren().removeIf(n -> {
            Sprite s = (Sprite) n;
            return s.dead;
        });
        
        if(t > 2){
            t = 0;
        }
    }
    
    void shoot(Sprite shooter){
        Shot s = new Shot((int)shooter.getTranslateX() + 20,(int) shooter.getTranslateY(), 5, 20, shooter.getType() + "bullet", Color.BLACK);
        root.getChildren().add(s);
    }
    
    @Override
    public void start(Stage stage) throws Exception{
        Scene scene = new Scene(createContent());
        scene.setOnKeyPressed(e ->{
            switch(e.getCode()){
                case A:
                    cannon.moveLeft();
                    break;
                case D:
                    cannon.moveRight();
                    break;
                case SPACE:
                    shoot(cannon);
                    break;
            }
        });
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String args){
        launch(args);
    }
}
