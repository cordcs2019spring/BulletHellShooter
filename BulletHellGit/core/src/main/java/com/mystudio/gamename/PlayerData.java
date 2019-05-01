/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mystudio.gamename;

import java.util.Stack;
import org.mini2Dx.core.Mdx;
import org.mini2Dx.core.playerdata.PlayerDataException;
import org.mini2Dx.core.serialization.annotation.Field;

/**
 *
 * @author John
 */
public class PlayerData {

    //Stack used for data storage. @Field indicates to the library that this can be stored
    @Field
    private Stack<PlayerScore> playerScores = new Stack<PlayerScore>();

    public void AddScore(String name, int score){
        playerScores.add(new PlayerScore(name,score));
    }

    /*
        returns an array of all player scores
        this is unsorted
    */
    public PlayerScore[] RetrieveScores() {

        //Cloning to avoid destructive edits
        Stack<PlayerScore> playerScoresClone = (Stack<PlayerScore>) playerScores.clone();

        PlayerScore[] temp = new PlayerScore[playerScoresClone.size()];
        int length = playerScoresClone.size();
        for(int i = 0; i < length; i++) {
            temp[i] = playerScoresClone.pop();
        }

        return temp;
    }
}