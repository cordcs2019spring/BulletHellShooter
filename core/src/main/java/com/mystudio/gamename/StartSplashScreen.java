package com.mystudio.gamename;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.engine.geom.CollisionPoint;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.Sprite;

/*
    This class does the bare basic movement functionality of moving and collision detection.
 */
public class StartSplashScreen // implements ApplicationListener
{

    //PROPERTIES, GETTERS SETTERS

    //This is the graphical asset that the user sees
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
    public StartSplashScreen(String texturePath, float x, float y, int xSpeed, int ySpeed){

        sprite = new Sprite(new Texture(Gdx.files.internal(texturePath)));

    }

    //METHODS

    /*
        Update increments all the game logic loop type stuff.
     */
    public String update(String currStat) {

        String stat = currStat;
//        System.out.println("hi");
        //Moves to the left
        if (stat.equals("START")) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {

                stat = "PLAY";

             } else {
                 System.out.println("nothing to do here");            
             }
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
        g.drawSprite(sprite,900,500);
        g.drawString("",32,32);
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