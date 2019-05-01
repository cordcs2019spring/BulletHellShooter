package com.mystudio.gamename;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.Sprite;

import java.util.ArrayList;
import org.mini2Dx.core.engine.geom.CollisionPoint;


public class MyMini2DxGame extends BasicGame {



    //PROPERTIES
    //the two current experimental enemies
    public MovementBase enemyShip;
    public MovementSerpentine enemySnake;

    //the current eperimental instance of the player
    public PlayerShip playerShip;

    //the array that will contain all enemies
    //static so it can be accessed from anywhere
    public static ArrayList<MovementBase> enemies = new ArrayList<MovementBase>();

    //the actual player object
    public static PlayerShip player;

    public static final String GAME_IDENTIFIER = "com.mystudio.gamename";

    public static int screenHeight;

    public static int screenWidth;
    
    public StartSplashScreen startScreen;
            
    public PauseScreen pauseScreen;
            
    public GameOverScreen gameOverScreen;
    
    //the game score
    public static int score;
    
    private String status;
	//private Texture texture;
    public EnemySpawner basicSpawner;
	
	@Override
    public void initialise() {

	    //texture = new Texture("mini2Dx.png");
       
        playerShip = new PlayerShip("playership.png", screenWidth+1000,screenHeight+700,6,6);
        player = playerShip;
        
        score = 0;

        basicSpawner = new EnemySpawner(0);
        
        screenHeight = getHeight();
        screenWidth = getWidth();
        
        startScreen = new StartSplashScreen("BetterStartScreen.png", 100,100,0,2);        
        status = "START";
        
        pauseScreen = new PauseScreen("BetterPauseScreen.png", 100,100,0,2); 
        
        gameOverScreen = new GameOverScreen("BetterGameOverScreen.png", 100,100,0,2);
    }
    
    @Override
    public void update(float delta) {
        
        if(!status.equals("pause")){
            status = startScreen.update(status);
            status = gameOverScreen.update(status);
            
            for (int i = 0; i < enemies.size();i++) {
            status = enemies.get(i).update(status);
            }         
            status = player.update(status);
            //System.out.println(enemies.size());
        }
        basicSpawner.spawnEnemy();
        status = pauseScreen.update(status);
        
        if(status.equals("GameOver")){    
            /*for (int i = 0; i < enemies.size();i++){
            status = enemies.get(i).update(status);
            }*/
            basicSpawner.SetTimer(0);
            playerShip.SetPoint(new CollisionPoint(screenWidth/2,screenHeight/2));
            
        }

        
    }
    
    @Override
    public void interpolate(float alpha) {
        for (MovementBase enemy: enemies) {
            enemy.interpolate(alpha);
        }

        //enemies.get(0).interpolate(alpha);
        player.interpolate(alpha);
    }
    
    @Override
    public void render(Graphics g) {
	    //Sprite temp = new Sprite(texture);
	    //g.drawSprite(temp);
        
            if (status.equals("START")) {
                startScreen.render(g);
            } else if (status.equals("pause")){
                
                pauseScreen.render(g);
            }
            else if (status.equals("GameOver")){
            
                gameOverScreen.render(g);
               //adds timer to score
               score= basicSpawner.timer + score;
            }
                
            else
            {   
                player.render(g);

                for (MovementBase enemy: enemies) {
                    enemy.render(g);
                }
            }
        //enemies.get(0).render(g);
        //playerShip.render(g);
    }

            
}
