/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mystudio.gamename;

/**
 * This class represents the score of a particular player
 * @author John
 */
public class PlayerScore implements Cloneable {
    private int score;
    
    public int GetPlayerScore(){
        return score;
    }
    
    public void SetPlayerScore(int score){
        this.score = score;
    }
    
    private String name;
    
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
    
    public PlayerScore clone() {
        return new PlayerScore(this);
    }
    
    public String toString(){
        return name + score;
    }
}
