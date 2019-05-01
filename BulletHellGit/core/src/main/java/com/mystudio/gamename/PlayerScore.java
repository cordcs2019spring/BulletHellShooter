/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mystudio.gamename;

import org.mini2Dx.core.serialization.annotation.Field;

/**
 * This class represents the score of a particular player
 * @author John
 */
public class PlayerScore implements Cloneable {
    @Field
    private int score = 0;
    
    public int GetPlayerScore(){
        return score;
    }
    
    public void SetPlayerScore(int score){
        this.score = score;
    }
    
    @Field
    private String name = "";
    
    public String GetPlayerName() {
        return name;
    }
    
    public void SetPlayerName(String name) {
        this.name = name;
    }
    
    //Basic constructor
    public PlayerScore(String name, int score){
        SetPlayerScore(score);
        SetPlayerName(name);
    }
    
    //used for cloning
    public PlayerScore(PlayerScore e) {
        SetPlayerScore(e.GetPlayerScore());
        SetPlayerName(e.GetPlayerName());
    }
    
    private PlayerScore() {}
    
    public PlayerScore clone() {
        return new PlayerScore(this);
    }
    
    @Override
    public boolean equals(Object scr) {
        
        PlayerScore temp = new PlayerScore();
        
        try {
            temp = (PlayerScore) scr;
        } catch (ClassCastException e) {
            return false;
        }
        
        
        if(temp.GetPlayerScore() == this.GetPlayerScore() && this.GetPlayerName().equals(temp.GetPlayerName())){
            return true;
        } else {
            return false;
        }
    }
}
