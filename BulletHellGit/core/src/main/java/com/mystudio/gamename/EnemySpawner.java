package com.mystudio.gamename;
import java.util.Random;
/**
 *
 * @author noah0
 */
public class EnemySpawner {
    //initializes the boundaries of the screen and creates a timer variable
    int yboundary;
    int xboundary;
    int timer = 0;
	
    
    public void spawnEnemy(){
    timer = timer+1;
        System.out.println("The timer is at: "+timer);
        //rollers used to determine random values for enemies. Changed every time an enemy is spawned. 
            Random roller = new Random();
            int temp = roller.nextInt(100);
            int temp2 = roller.nextInt(3);
        //rollers for enemy spawn locations; based around screen height and width
            float randx = roller.nextInt(MyMini2DxGame.screenWidth);
            randx = randx-100;
            float randy = roller.nextInt(300);
            randy = randy*-1;
        
            if (timer>=100){
                if (temp<=2){
                    //starts spawning the grunts: basic straight movement ships
                    MyMini2DxGame.enemies.add(new MovementBase("Ship1.png", randx,randy,0,2));
                }
            }
            if (timer>=800){
                if (temp<=1){
                    //starts spawning the striker, a fast moving shooting ship
                    MyMini2DxGame.enemies.add(new MovementSerpentine("striker.png", randx, randy, 2, 3, 40*temp2, -1, 1));
                }
            }
            if (timer>=1500){
                if (temp==0){
                    //starts spawning the slimer: a large slow moving shooting ship
                    MyMini2DxGame.enemies.add(new MovementSerpentine("slimer.png", randx, randy, 1, 1, 400, -1, 3));
                }
            }
            
            
        
        
    }
    
}