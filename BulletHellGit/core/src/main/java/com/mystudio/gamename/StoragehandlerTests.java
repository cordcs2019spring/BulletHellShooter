/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mystudio.gamename;

import java.util.Arrays;

/**
 *
 * @author John
 */
public class StoragehandlerTests {
    
    public static boolean AppendScore_General(){
        StorageHandler.SetPlayerData(new PlayerData());
        StorageHandler.AppendScore("jim", 10);
        
        PlayerScore temp = StorageHandler.GetPlayerData().RetrieveScores()[0];
        if(temp.equals(new PlayerScore("jim",10))) {
            return true;
        } else {
            return false;
        }
    }
    
    public static boolean GiveScoresSortedByHighest_SortsCorrectly(){
        StorageHandler.SetPlayerData(new PlayerData());
        
        StorageHandler.AppendScore("e", 14);
        StorageHandler.AppendScore("a", 10);
        StorageHandler.AppendScore("c", 12);
        StorageHandler.AppendScore("b", 11);
        StorageHandler.AppendScore("d", 13);
        
        
        PlayerScore[] temp = StorageHandler.GiveScoresSortedByHighest();
        PlayerScore[] temp2 = {new PlayerScore("e", 14), new PlayerScore("d", 13), new PlayerScore("c", 12), new PlayerScore("b", 12), new PlayerScore("a", 10)};
        
        /*for(PlayerScore x: temp) {
            System.out.println("Name: " + x.GetPlayerName() + " Score: " + x.GetPlayerScore());
        }*/
        
        return temp[0].equals(temp2[0]);
    }
    
    public static boolean GiveScoresSortedByLowest_SortsCorrectly(){
        StorageHandler.SetPlayerData(new PlayerData());
        
        StorageHandler.AppendScore("e", 14);
        StorageHandler.AppendScore("a", 10);
        StorageHandler.AppendScore("c", 12);
        StorageHandler.AppendScore("b", 11);
        StorageHandler.AppendScore("d", 13);
        
        
        PlayerScore[] temp = StorageHandler.GiveScoresSortedByLowest();
        PlayerScore[] temp2 = {new PlayerScore("a", 10), new PlayerScore("b", 12), new PlayerScore("c", 12), new PlayerScore("d", 13), new PlayerScore("e", 14)};
        
        /*for(PlayerScore x: temp) {
            System.out.println("Name: " + x.GetPlayerName() + " Score: " + x.GetPlayerScore());
        }*/
        
        return temp[0].equals(temp2[0]);
    }
 
    public static boolean TestLoadAndSave_DidNotCrash(){
        StorageHandler.SetPlayerData(new PlayerData());
        
        StorageHandler.AppendScore("e", 14);
        StorageHandler.AppendScore("a", 10);
        StorageHandler.AppendScore("c", 12);
        StorageHandler.AppendScore("b", 11);
        StorageHandler.AppendScore("d", 13);
        
        
        
        boolean didStore = StorageHandler.Store();
        
        StorageHandler.SetPlayerData(new PlayerData());
        
        
        boolean didLoad = StorageHandler.Load();
        
        
        
        return didStore && didLoad;
    }
    
    public static void tests(){
        System.out.println("AppendScore_General() works?: " + AppendScore_General());
        System.out.println("GiveScoresSortedByHighest_SortsCorrectly() works?: " + GiveScoresSortedByHighest_SortsCorrectly());
        System.out.println("GiveScoresSortedByLowest_SortsCorrectly() works? : " + GiveScoresSortedByLowest_SortsCorrectly());
        System.out.println("TestLoadAndSave_DidNotCrash() works? : " + TestLoadAndSave_DidNotCrash());
    }
    
    
}
