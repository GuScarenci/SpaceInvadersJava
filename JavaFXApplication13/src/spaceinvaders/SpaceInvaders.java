package spaceinvaders;
import elements.*;
import java.io.IOException;
import java.io.InputStream;

import java.util.List;
import java.util.stream.Collectors;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author gustavoscarenci
 */
public class SpaceInvaders extends Application {
    
    final private Pane root  =  new Pane();
    //Label scoreLabel = new Label("This is a label");
    
    int score = 0;
    int spriteAnimationHelper = 1;
    int enemyMovingSide = 1;
    boolean moveDown = false;
    
    private double t = 0;
    final private Cannon cannon = new Cannon(300,550,40,40,"cannon","/spaceinvaders/nave.png");
          
    private Parent createContent(){
        root.setPrefSize(800,600);
        root.setStyle("-fx-background-color: blue");
       
        //scoreLabel.setFont(Font.loadFont("/spaceinvaders/Pixeboy.ttf", 45));
   
        //root.getChildren().add(scoreLabel);        
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
        //set up barriers
        for(int i = 0; i < 5; i++){
            GameObject s = new Barrier(60 + i*160, 470, 80, 40, "barrier", "/spaceinvaders/barrier0.png");
            root.getChildren().add(s);
        }
        
        //set up enemies
        for(int i = 0; i < 11; i++){
            GameObject s = new Enemy(30 + i*40, 30, 40, 30, "enemy", "/spaceinvaders/inimigo3.1.png",3);
            root.getChildren().add(s);
        }
        for(int i = 0; i< 2; i++){
            for(int j = 0; j < 11; j++){
                GameObject s = new Enemy(30 + j*40, 70 + i* 40, 30, 30, "enemy", "/spaceinvaders/inimigo2.1.png",2);
                root.getChildren().add(s);
            }
        }
        for(int i = 0; i< 2; i++){
            for(int j = 0; j < 11; j++){
                GameObject s = new Enemy(30 + j*40, 150 + i* 40, 30, 30, "enemy", "/spaceinvaders/inimigo1.1.png",1);
                root.getChildren().add(s);
            }
        }
    }
    
    private List<GameObject> sprites(){
    return root.getChildren().stream().map(n -> (GameObject)n).collect(Collectors.toList());}
    
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
                        //cannon.dead =  true;
                        //s.dead = true;
                        
                        cannon.reduceLife();
                        s.reduceLife();
                    }
                    
                    sprites().stream().filter(e ->  e.getType().equals("barrier")).forEach(barrier-> {
                    if(s.getBoundsInParent().intersects(barrier.getBoundsInParent())){
                        barrier.reduceLife();
                        s.reduceLife();
                    }});
                    break;
                
                case "cannonbullet":
                    s.moveUp();
                    sprites().stream().filter(e ->  e.getType().equals("enemy")).forEach(enemy-> {
                    if(s.getBoundsInParent().intersects(enemy.getBoundsInParent())){
                        enemy.reduceLife();
                        s.reduceLife();
                        score+=enemy.getCategorie()*10;
                    }});
                    
                    sprites().stream().filter(e ->  e.getType().equals("barrier")).forEach(barrier-> {
                    if(s.getBoundsInParent().intersects(barrier.getBoundsInParent())){
                        barrier.reduceLife();
                        s.reduceLife();
                    }});
                    break;
                    
                case "enemy":
                    if(enemyMovingSide == 1){
                        s.moveRight();
                    }else if(enemyMovingSide == -1){
                        s.moveLeft();
                    }

                    if(t>3){
                        if(Math.random() < 0.001){
                            shoot(s);
                        }
                        String file;
                        if(spriteAnimationHelper == 1){
                            file = "/spaceinvaders/inimigo" + s.getCategorie() + ".1.png";
                        }else{
                            file = "/spaceinvaders/inimigo" + s.getCategorie() + ".2.png";
                        }
                        InputStream is = null;
                        try {
                            is = GameObject.class.getResource(file).openStream();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Image image = new Image(is, 30, 30, false, false);
                        s.setImage(image);
                        spriteAnimationHelper*= -1;
                    }
                    break;
                case "barrier":
                    String file;
                    file = "/spaceinvaders/barrier" + (4 - s.getLife()) + ".png";
                    InputStream is = null;
                    try {
                        is = GameObject.class.getResource(file).openStream();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Image image = new Image(is, 30, 30, false, false);
                    s.setImage(image);          
            }
 
        });
        
        root.getChildren().removeIf(n -> {
            GameObject s = (GameObject) n;
            return (s.getLife() == 0);
        });
        
        if(t > 3){
            t = 0;
        }
    }
    
    void shoot(GameObject shooter){
        if(shooter.getType().equals("cannon")){
            Shot s = new Shot((int)shooter.getTranslateX() + 20,(int) shooter.getTranslateY(), 5, 20, shooter.getType() + "bullet", "/spaceinvaders/shotplayer.png");
            root.getChildren().add(s);
        }else if(shooter.getType().equals("enemy")){
            Shot s = new Shot((int)shooter.getTranslateX() + 20,(int) shooter.getTranslateY(), 5, 20, shooter.getType() + "bullet", "/spaceinvaders/shot.png");
            root.getChildren().add(s);    
        }
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
