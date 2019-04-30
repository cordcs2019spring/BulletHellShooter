package com.mystudio.gamename;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.engine.geom.CollisionPoint;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.Sprite;

/*
    This class does the bare basic movement functionality of moving and collision detection.
 */
public class MovementBase {

    //PROPERTIES, GETTERS SETTERS

    //This is the graphical asset that the user sees
    private Sprite sprite;

    //these two are fairly self explanatory, speed in pixels across the screen
    private int xSpeed;
    private int ySpeed;


    //This tracks the top left of the sprite, and lets us move things around
    //with out crashing the game. To quote Todd Howard, it just works.
    private CollisionPoint point;

    //Every ship is wrapped in a rectangle. If you hit the rectangle, you hit the ship.
    private Rectangle hitbox;

    public Sprite GetSprite() {
        return sprite;
    }

    public void SetSprite(Sprite sprite){
        this.sprite = sprite;
    }

    public int GetXSpeed(){
        return xSpeed;
    }

    public void SetXSpeed(int speed) {
        xSpeed = speed;
    }

    public int GetYSpeed() {
        return ySpeed;
    }

    public void SetYSpeed(int speed) {
        ySpeed = speed;
    }

    public CollisionPoint GetPoint() {
        return point;
    }

    public void SetPoint(CollisionPoint point) {
        this.point = point;
    }

    public Rectangle GetHitBox(){
        return hitbox;
    }

    public void SetHitBox(Rectangle hitbox){
        this.hitbox = hitbox;
    }

    //CONSTRUCTORS
    /*
        This is the base constructor.
        @param texture path, The file name within the assets folder. E.G Ship1.png
        @param x, the x coordinate of the sprite. Origin is in the top left corner
        @param y, the y value of the sprite, origin is the top left corner
     */
    public MovementBase(String texturePath, float x, float y, int xSpeed, int ySpeed){

        sprite = new Sprite(new Texture(Gdx.files.internal(texturePath)));

        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;

        point = new CollisionPoint(x,y);
        hitbox = sprite.getBoundingRectangle();
    }

    //METHODS

    /*
        Update increments all the game logic loop type stuff.
     */
    public String update(String currStat) {
        //sprite.setPosition(sprite.getX() + xSpeed, sprite.getY() + ySpeed);
        String stat = currStat;
        //This line magically makes the program not crash. Worship it, for it is beyond mortal ken.
        point.preUpdate();

        //Moves our point of origin and hitbox.
        point.set(point.getX() + xSpeed, point.getY() + ySpeed);
        hitbox.setPosition(point.getX(),point.getY());
        //System.out.println("we Just moved Some amout");

        //Checks to see of the unit is still onscreen. If they are not on screen, they are removed from the enemies array.
        CheckBounds();

        //checks to see if the enemy has collided with the player.
        stat = CheckCollision(stat);
        return stat;
    }

    //This serves as aa bridge between render and update. It smoothes over any of the weirdness of the hardware.
    public void interpolate(float alpha){
        point.interpolate(null, alpha);
    }


    /*
        This function takes the game logic, and it turns it into graphics on the screen.
        Most (all) of the heavy lifting for this function is taken care of by the engine.
        @param Graphics g, the thing that does all the drawing

     */
    public void render(Graphics g) {
        //sprite.setPosition(point.getRenderX(),point.getRenderY());
        //g.drawTexture(sprite.getTexture(),point.getRenderX(),point.getRenderY());
        g.drawSprite(sprite,point.getRenderX(),point.getRenderY());

        //this draws the hitbox for the sake of debugging. Not needed in the final product.
        g.drawRect(hitbox.getX(),hitbox.getY(),hitbox.getWidth(),hitbox.getHeight());
        //Rectangle temp = sprite.getBoundingRectangle();
        //g.drawRect(temp.getX(),temp.getY(),temp.getWidth(),temp.getHeight());
    }

    /*
        Checks to see if the unit is out of bounds. If they are, they are removed.
        It does this by seeing if the enemy is off the screen. If they are off the screen, then the unit is searched from the array and removed.
     */
    private void CheckBounds(){
        if (point.getX() >= MyMini2DxGame.screenWidth + sprite.getWidth() || point.getY() >= MyMini2DxGame.screenHeight + sprite.getHeight()) {
            MyMini2DxGame.enemies.remove(this);
            System.out.println("Removed an enemy!");
        } else if (point.getX() <= 0 - sprite.getWidth() || point.getY() <= -200 - sprite.getHeight() ) {
            //the extra -200 gives us an extra buffer zone at the top of the screen.
            MyMini2DxGame.enemies.remove(this);
            System.out.println("Removed an enemy!");
    }
    }

    /*
        Checks for collisions using the math library.
     */
    private String CheckCollision(String currStat){
        //Every enemy when they updates checks to see if they have collided with the player.
        String stat = currStat;
        if (hitbox.overlaps(MyMini2DxGame.player.GetHitBox())){
            System.out.println("The player has been hit!!!");
            stat = "GameOver";
        }
        return stat;
    }

}
