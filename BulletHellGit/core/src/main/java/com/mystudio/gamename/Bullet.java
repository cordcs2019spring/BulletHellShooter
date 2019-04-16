package com.mystudio.gamename;

/*
    Super Class of PlayerBullet and EnemyBullet. Mostly, is a simplified wrapper for MovementBase that is slightly more legible.
    Subsequent bullet wrappers will be made for different bullet types
 */
public class Bullet extends MovementBase {

    //Controlls the speed with which the bullet travels down
    public static final int BULLETSPEED = -6;

    /*
        Bullets are really damn simple, so they are just a stripped down version of a MovementBase
        @param texture, same as super class, same for the x and y
     */
    public Bullet(String texturePath, float x, float y){
        super(texturePath,x,y,0,BULLETSPEED);
    }
}
