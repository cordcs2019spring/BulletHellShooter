/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mystudio.gamename;

import java.util.ArrayList;
import org.mini2Dx.core.Mdx;
import org.mini2Dx.core.playerdata.PlayerDataException;

/**
 *
 * @author John
 */
public class StorageHandler {
    
    //this is the object that actually gets written to memory
    private static PlayerData playerData = new PlayerData();
    
    /*
        Attempts to load data from the file system of the computer.
        If this failes, then an error is printed
    */
    public static void Load(){
        try {
            Mdx.playerData.writeJson(playerData, "playerdata.json");
        } catch (PlayerDataException e){
            System.out.println("No Player Data Avaiable To Load");
        }
    }
    
    /*
        Attempts to write data to the file system of the computer
        If this fails, an error message is printed
    */
    public static void Store() {
        try {
            Mdx.playerData.writeJson(playerData, "playerdata.json");
        } catch(PlayerDataException e){
            System.out.println("Could Not Save Data");
        }
    }
    
    /*
        returns an unsorted array of PlayerScore objects
    */
    public static PlayerScore[] GiveScoresUnsorted() {
        return playerData.RetrieveScores();
    }
    
    /*
        Appends a new player score to the stack of player scores
    */
    public static void AppendScore(String name, int score){
        playerData.AddScore(name, score);
    }
    
    /*
        I am just using bubble sort here
        Because, that is just simple and easy enough for me to do
        And also, there probably isn't ever going to be that many scores
    
        Returns a sorted array of scores
    */
    public static PlayerScore[] GiveScoresSortedByHighest() {
        PlayerScore[] temp = StorageHandler.GiveScoresUnsorted();
        
        //Bubble sorting, for fun and profit
        for(int i = 0; i < temp.length - 1;i++) {
            for(int x = 0; x < temp.length - i - 1;x++){
                if(temp[x].GetPlayerScore() > temp[x + 1].GetPlayerScore()) {
                    PlayerScore temp2 = new PlayerScore(temp[x]);
                    temp[x] = temp[x + 1];
                    temp[x + 1] = temp2;
                }
            }
        }
        
        return temp;
    }
    
    /*
        Same deal, sorted by lowest
    */
    public static PlayerScore[] GiveScoresSortedByLowest(){
        PlayerScore[] temp = StorageHandler.GiveScoresSortedByHighest();
        
        PlayerScore[] reverse = new PlayerScore[temp.length];
        
        //reverses in a fairly janky way
        for(int i = temp.length - 1;i>= 0;i--){
            reverse[temp.length - i - 1] = temp[i];
        }
        
        return reverse;
    }
    
    /*
        Lookup Search, finds all instances of name and then returns them first
        takes a string which is the case sensative name
    */
    public static PlayerScore[] GiveScoresSortedByName(String name) {
        PlayerScore[] temp = playerData.RetrieveScores();
        
        ArrayList<PlayerScore> hasName = new ArrayList<PlayerScore>();
        ArrayList<PlayerScore> noName = new ArrayList<PlayerScore>();
        
        //checks name, and then adds to apropriate list
        for(int i = 0;i < temp.length;i++){
            if(temp[i].GetPlayerName().equals(name)) {
                hasName.add(temp[i]);
            } else {
                noName.add(temp[i]);
            }
        }
        
        hasName.addAll(noName);
        
        //converts to an proper array
        temp = hasName.toArray(new PlayerScore[hasName.size()]);
        
        return temp;
    }
}
