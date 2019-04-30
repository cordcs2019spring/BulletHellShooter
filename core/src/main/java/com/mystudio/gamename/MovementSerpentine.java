package com.mystudio.gamename;
/*
    This class enables serpentine movement. This can be used for moving back an forth on an angle. Moving back and worth while moving down, or moving up and down while going right
    or any other similar combination;
 */
public class MovementSerpentine extends MovementBase {

    //The max number of steps one can take side to side, determines when you start the journey back
    //Set to -1 if it doesn't girate along this axis
    private int maxStepsToSide;
    private int currentStepsToSide = 0;

    //Same deal but up and down.
    private int maxStepsUpDown;
    private int currentStepsUpDown;

    /*
        @param String texturepath, the string which leads you to the sprite,
        @float x, x coordinate
        @float y, y coordinate
        @int xSpeed, the speed the enemy moves along the x axis
        @int ySpeed, the speed the enemy moves along the y axis
        @int maxStepsToSide, the number of frames the enemy moves to the side before revering
        @int maxStepsUpDown, the number of frames the enemy moves up or down before reversing.
     */
    public MovementSerpentine(String texturePath, float x, float y, int xSpeed, int ySpeed, int maxStepsToSide, int maxStepsUpDown) {
        super(texturePath,x,y,xSpeed,ySpeed);

        this.maxStepsToSide = maxStepsToSide;
        this.maxStepsUpDown = maxStepsUpDown;
    }

    public String update(String currStat){
        //Adjusts how the object should be moving in terms of speed, before using the super class to make the actual coordinate change.
        //This is the "model" for how we are abstracting behavior. Do the logic in a custom update function, then call the super to make the coordinate change happen.
        String stat = currStat;
        //First checks it see of we should reset steps, and reverses directions if so.
        //if it's zero, then we just pretend it doesn't exists.
        if (currentStepsToSide == maxStepsToSide && maxStepsToSide != -1) {
            SetXSpeed(GetXSpeed() * -1);
            currentStepsToSide = 0;
        } else if (maxStepsToSide != -1) {
            //increments if its not zero.
            currentStepsToSide++;
        }

        if (currentStepsUpDown == maxStepsUpDown && maxStepsUpDown != -1) {
            SetYSpeed(GetYSpeed() * -1);

            //Fires a bullet when the ship changes directions
            //MyMini2DxGame.enemies.add(new EnemyBullet(GetPoint().getX() + (GetSprite().getWidth() / 2),GetPoint().getY() + GetSprite().getHeight()));

            currentStepsUpDown = 0;
        } else if (maxStepsUpDown != -1) {
            currentStepsUpDown++;
        }


        //Updates game logic, checks collisions
        //This updates the coordinate position of the ship.
        stat = super.update(stat);
        return stat;
    }
}
