package com.mystudio.gamename;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import java.util.Collections;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.engine.geom.CollisionPoint;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.Sprite;

/*
    This class does the bare basic movement functionality of moving and collision detection.
 */
public class GameOverScreen // implements ApplicationListener
{

    //PROPERTIES, GETTERS SETTERS

    //This is the graphical asset that the user sees
    private boolean hasStored = false;
    private String[] scores;
    private boolean hasSortedScores = false;
    
    public MyMini2DxGame myMini2DXGame;
    
    private Sprite sprite;

    public Sprite GetSprite() {
        return sprite;
    }

    public void SetSprite(Sprite sprite){
        this.sprite = sprite;
    }

    //CONSTRUCTORS
    /*
        This is the base constructor. The
        @param texture path, The file name within the assest folder. E.G Ship1.png
        @param x, the x coordinate of the spirte. Origin is in the top left corner
        @param y, the y value of the sprite, orgin is the top left corner
     */
    public GameOverScreen(String texturePath, float x, float y, int xSpeed, int ySpeed){

        sprite = new Sprite(new Texture(Gdx.files.internal(texturePath)));

    }

    //METHODS

    /*
        Update increments all the game logic loop type stuff.
     */
    public String update(String currStat) {

        String stat = currStat;

        if (stat.equals("GameOver")) {
            stat = "GameOver";
            if(!hasStored){
                //this loads current scores, then appends the latest score. We set hasStored to true, so that we don't do this operation again
                StorageHandler.Load();
                StorageHandler.AppendScore("", MyMini2DxGame.score);
                //System.out.println(StorageHandler.GetPlayerData().RetrieveScores()[0].GetPlayerScore());
                //System.out.println(StorageHandler.GiveScoresSortedByHighest()[0].GetPlayerScore());
                hasStored = true;
            }
            if(Gdx.input.isKeyPressed(Input.Keys.X)){
                StorageHandler.Store();
                hasStored = false;
                hasSortedScores = false;
                MyMini2DxGame.score = 0;
                
                stat="START";
            }    
        }
        
        else {
                 System.out.println("nothing to do here");            
        }
        return stat;
    }
    

    //This is another magic function. I'm not exactly sure why it stops things from breaking, but it does. That's why I'm wrapping it
    //So the rest of the group doesn't have to deal with it's nonsense
    public void interpolate(float alpha){

    }


    /*
        This function takes the game logic, and it turns it into graphics on the screen.
        Most (all) of the heavy lifting for this function is taken care of by the engine.
        @param Graphics g, the thing that does all the drawing

     */
    public void render(Graphics g) {
        //sprite.setPosition(point.getRenderX(),point.getRenderY());
        //g.drawTexture(sprite.getTexture(),point.getRenderX(),point.getRenderY());
        
        //Collections.sort(MyMini2DxGame.scores, Collections.reverseOrder());
        g.drawSprite(sprite,800,400);
        g.drawString("Game Over! Your Score is: " + MyMini2DxGame.score,200,200);
        if(!hasSortedScores) {
            //Gives is a sorted array of ints for the score. Boolean ensures we don't perform this 
            //action every time the function is called.
            PlayerScore[] temp = StorageHandler.GiveScoresSortedByHighest();
            scores = new String[temp.length];
            for(int i = 0; i < temp.length;i++){
                //by all rights, this should be in a trie catch block. But, this would already have thrown an
                //exeption if there was an issue.
                scores[i] = Integer.toString(temp[i].GetPlayerScore());
                //System.out.println("Scores: " + scores[i]);
                //System.out.println("Player Score: " + temp[i].GetPlayerScore());
                hasSortedScores = true;
            }
            
        }
        
        /*try{
            g.drawString("These are the high scores " + scores,200,225);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("You've gone too far!");
        }*/
        
        //Yes, it's just some ints for positioning. While not ideal, it's just going to have to do for now.
        g.drawString("Here are the previous high scores: ", 200, 225);
        for(int i = 0; i < scores.length;i++){
            g.drawString(scores[i], 450, 225 + 20*i);
        }

    }
    
    /*
    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }
    
    @Override
    public void dispose() {
    }
*/
}