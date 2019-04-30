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
    
    private String status;
	//private Texture texture;
	
	@Override
    public void initialise() {

	    //texture = new Texture("mini2Dx.png");
        enemyShip = new MovementBase("Ship1.png", 100,100,0,2);
        enemySnake = new MovementSerpentine("Ship1.png", 200,500,2,2,20,20);
        enemies.add(enemyShip);
        enemies.add(enemySnake);
        playerShip = new PlayerShip("playership.png", 300,300,6,6);
        player = playerShip;

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
            status = pauseScreen.update(status);
        
        if(status.equals("GameOver")){    
            for (int i = 0; i < enemies.size();i++){
                enemyShip.SetPoint(new CollisionPoint(100,100));
                enemySnake.SetPoint(new CollisionPoint(200,500));
                enemies.get(i).update(status);
            }
            playerShip.SetPoint(new CollisionPoint(300,300));
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
