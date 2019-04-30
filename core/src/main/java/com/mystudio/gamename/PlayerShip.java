package com.mystudio.gamename;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.Sprite;

import java.util.ArrayList;

public class PlayerShip extends MovementBase {

    private ArrayList<AllyBullet> bullets = new ArrayList<AllyBullet>();
    private ArrayList<AllyBullet> bulletsToDelete = new ArrayList<AllyBullet>();

    public ArrayList<AllyBullet> GetBullets() {
        return bullets;
    }

    public void SetBullets(ArrayList<AllyBullet> arr) {
        bullets = arr;
    }

    public PlayerShip(String texturePath, int x, int y, int xSpeed, int ySpeed) {
        super(texturePath,  x,  y,  xSpeed,  ySpeed);
    }

    public ArrayList<AllyBullet> GetBulletsToDelete() {
        return bulletsToDelete;
    }

    public void SetBulletsToDelete(ArrayList<AllyBullet> arr) {
        bulletsToDelete = arr;
    }

    /*
        Similar to super class, but only moves if a key is pressed. Also, doesn't directly check collisions. Bullets check collisions, and enemies check against the player
     */
    public String update(String currStat) {
        String stat = currStat;

                        //Pauses game
        if(Gdx.input.isKeyPressed(Input.Keys.P)){
            stat = "pause";
            return stat;
        }
        //Moves to the left
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            //GetSprite().setX(GetSprite().getX() - GetXSpeed());
            //Checks to see if the player will cross off screen, moves if they won't
            if(GetPoint().getX() - GetXSpeed() >= 0)
                GetPoint().setX((GetPoint().getX() - GetXSpeed()));
        }

        //Moves to the right
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            //GetSprite().setX(GetSprite().getX() + GetXSpeed());
            if (GetPoint().getX() + GetSprite().getWidth() + GetXSpeed() <= MyMini2DxGame.screenWidth)
                GetPoint().setX((GetPoint().getX() + GetXSpeed()));
        }

        //Moves to the up
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            //GetSprite().setY(GetSprite().getY() - GetYSpeed());
            if (GetPoint().getY() - GetYSpeed() >= 0)
                GetPoint().setY((GetPoint().getY() - GetYSpeed()));
        }

        //Moves to the down
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            //GetSprite().setY(GetSprite().getY() + GetYSpeed());
            if (GetPoint().getY() + GetSprite().getHeight() + GetYSpeed() <= MyMini2DxGame.screenHeight)
                GetPoint().setY((GetPoint().getY() + GetYSpeed()));

            System.out.println(MyMini2DxGame.screenHeight);
            System.out.println(GetPoint().getY());
        }
        //if you hit space, adds another bullet
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            bullets.add(new AllyBullet(GetPoint().getX() + (GetSprite().getWidth() / 2),GetPoint().getY()));
        }

        //updates the location of the hitbox
        GetHitBox().setPosition(GetPoint().getX(),GetPoint().getY());

        for (AllyBullet bullet: bullets) {
            bullet.update();
        }

        //removes all of the bullets that have hit and destroyed an enemy. Then clears the array.
        bullets.removeAll(bulletsToDelete);
        bulletsToDelete = new ArrayList<AllyBullet>();
        return stat;
    }

    /*
        Its the render function. Eerily similar to the other render function
     */
    @Override
    public void render(Graphics g) {
        super.render(g);
        //System.out.println(super.GetSprite().toString());
        //g.drawSprite(super.GetSprite(),GetSprite().getX(),GetSprite().getY());
        for (AllyBullet bullet: bullets) {
            bullet.render(g);
        }
    }

    /*
        Smooths away the weirdness
     */
    @Override
    public void interpolate(float alpha) {
        super.interpolate(alpha);
        for (AllyBullet bullet: bullets) {
            bullet.interpolate(alpha);
        }
    }
}
