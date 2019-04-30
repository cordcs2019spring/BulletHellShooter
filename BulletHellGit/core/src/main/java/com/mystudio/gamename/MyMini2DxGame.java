package com.mystudio.gamename;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.Sprite;

import java.util.ArrayList;


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
	//private Texture texture;
    public EnemySpawner basicSpawner = new EnemySpawner();
	
	@Override
    public void initialise() {

	    //texture = new Texture("mini2Dx.png");
        screenHeight = getHeight();
        screenWidth = getWidth();
        playerShip = new PlayerShip("playership.png", screenWidth/2,screenHeight-250,6,6);
        player = playerShip;

        
        
        
    }
    
    @Override
    public void update(float delta) {

        for (int i = 0; i < enemies.size();i++) {
            enemies.get(i).update();
        }

        player.update();
        //System.out.println(enemies.size());
        
        basicSpawner.spawnEnemy();
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
        player.render(g);

        for (MovementBase enemy: enemies) {
            enemy.render(g);
        }
        //enemies.get(0).render(g);
        //playerShip.render(g);
    }
}
