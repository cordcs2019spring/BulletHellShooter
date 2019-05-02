/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mystudio.gamename;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import static com.mystudio.gamename.StorageHandler.AppendScore;
import java.util.ArrayList;
import java.util.Stack;
import org.mini2Dx.core.Mdx;
import org.mini2Dx.core.playerdata.PlayerDataException;

/**
 *
 * @author John
 */
public class StorageHandler {
    
    //this is the object that actually gets written to memory
    private static PlayerData playerData = new PlayerData();
    
    //This is the file where the save data is stored. It varies from machine to machine, but the game can always find it
    private static final FileHandle FILE = Gdx.files.local("savedata.txt");
    
    public static void SetPlayerData(PlayerData pd){
        playerData = pd;
    }
    
    public static PlayerData GetPlayerData(){
        return playerData;
    }
    /*
        Attempts to load data from the file system of the computer.
        If this failes, then false is returned
    */
    public static boolean Load(){
        //this is a rather fragile process, so we need to nest it in two try blocks.
        try {
            try{
                //reads the whole damn string
                String temp = FILE.readString();
                
                //attempts to split at each new line
                String[] rows = temp.split("\n");
                
                for(String str: rows){
                    //attempts to split again at each comma
                    String[] columns = str.split(",");
                    int score = 0;
                    
                    //tries to convert the second comlumn, which is always the score, to an int.
                    try {
                        score = Integer.parseInt(columns[1]);
                    } catch (NumberFormatException e) {
                        score = -1;
                    }
                    //this adds the score from memory into the current data object.
                    AppendScore(columns[0],score);
                } 
            
                return true;
            } catch(ArrayIndexOutOfBoundsException e) {
                return false;
            }
        } catch (GdxRuntimeException e) {
            return false;
        }
    }
    
    /*
        Stores data as a string
        Seperated by columns and linebreaks
        May the gods have mercy on my soul
        
    */
    public static boolean Store() {
        /* Just ignore this part. It's an unhappy memory.
        try {
            Mdx.playerData.writeJson(playerData, "playerdata.json");
            return true;
        } catch(PlayerDataException e){
            System.out.println("Could Not Save Data");
            return false;
        }*/
        //So, we have our scores
        PlayerScore[] temp = playerData.RetrieveScores();
        
        //We've reset the file contents to make sure we don't duplicate anything
        //This is needlessly inefficient, but I'm out of braincells. 
        FILE.writeString("", false);
        
        //We cycle through the array attempting to write to the file. 
        for(PlayerScore score: temp) {
            FILE.writeString(score.GetPlayerName() + "," + score.GetPlayerScore()+"\n", true);
        }
        
      //System.out.println(FILE.readString());
        
        //clears out player data.
        //Store should only be called when you are absolutely done with player scores
        playerData = new PlayerData();
        
        return true;
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
    
        Returns a sorted array of scores. Lowest to highest. What more do you
        need to know?
    */
    public static PlayerScore[] GiveScoresSortedByLowest() {
        PlayerScore[] temp = StorageHandler.GiveScoresUnsorted();
        
        //Bubble sorting, for fun and profit. O(n^2) ftw.
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
        Same deal, sorted by lowest...
    */
    public static PlayerScore[] GiveScoresSortedByHighest(){
        PlayerScore[] temp = StorageHandler.GiveScoresSortedByLowest();
        
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
        
        //Two arraylists get added two as we iterate over the entire temp array.
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
        
        //By the power of the Java standard library, I declare you a proper Array!
        temp = hasName.toArray(new PlayerScore[hasName.size()]);
        
        return temp;
    }
}
