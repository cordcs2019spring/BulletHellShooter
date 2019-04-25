package com.mystudio.gamename;
import java.util.Random;
/**
 *
 * @author noah0
 */
public class EnemySpawner {
    
    int yboundary;
    int xboundary;
    int timer = 0;
	
    
    public void spawnEnemy(){
    timer = timer+1;
        System.out.println("The timer is at: "+timer);
    
        if (timer>=100){
            Random roller = new Random();
            int temp = roller.nextInt(75);
            if (temp==0){
                MyMini2DxGame.enemies.add(new MovementBase("Ship1.png", 100,100,0,2));
	
            }
            
        }
        
    }
    
}