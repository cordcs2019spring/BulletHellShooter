package com.mystudio.gamename;
/*
    This is the bullet fired by certain kinds of enemies. It goes at the same speed as the default bullet class.
    It is already built to detect collisions with the player, so we're pretty solid.
 */
public class EnemyBullet extends Bullet {

    private static final String ENEMY_BULLET_FILEPATH = "EnemyProjectile.png";
    private static final int BULLET_SPEED = 7;

    //An enemy bullet is literally just the basemovement class, but even more simplified
    //Its' renamed for the sake of clarity and easier creation.
    public EnemyBullet(float x, float y) {
        super(ENEMY_BULLET_FILEPATH, x,y);
        SetYSpeed(BULLET_SPEED);
    }
}
