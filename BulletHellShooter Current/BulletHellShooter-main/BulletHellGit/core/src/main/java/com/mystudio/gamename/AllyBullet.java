package com.mystudio.gamename;

import java.util.ArrayList;

public class AllyBullet extends Bullet{

    //Preset Classpath for this type of bullet
    private static final String TEXTURE_PATH = "AllyProjectile.png";

    public AllyBullet( float x, float y){
        super(TEXTURE_PATH,x,y);
    }

    /*
        Almost identical to the superclass, but we check for how/if we collide with an enemy.
     */
    public void update() {
        //sprite.setPosition(sprite.getX() + xSpeed, sprite.getY() + ySpeed);

        //This does some behind the scenes stuff for the engine
        GetPoint().preUpdate();

        //Moves point instead of whole sprite
        GetPoint().setY(GetPoint().getY() + GetYSpeed());
        GetHitBox().setPosition(GetPoint().getX(),GetPoint().getY());
        //System.out.println("we Just moved Some amout");

        //The juicy juicy hit detection
        DetectCollisions();
    }

    private void DetectCollisions(){
        //checks every enemy to see of the bullet has collided with any enemy.
        ArrayList<MovementBase> toRemoveEnemy = new ArrayList<MovementBase>();


        for (MovementBase enemy: MyMini2DxGame.enemies) {
            if (GetHitBox().overlaps(enemy.GetHitBox())){
                //Slates for remove to avoid a concurrent modification error
                toRemoveEnemy.add(enemy);

                //Slates bullet for removal to avoid a concurrent modification error
                MyMini2DxGame.player.GetBulletsToDelete().add(this);
            }
        }

        MyMini2DxGame.enemies.removeAll(toRemoveEnemy);

    }
}
