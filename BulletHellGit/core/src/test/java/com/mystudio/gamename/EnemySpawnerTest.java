/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mystudio.gamename;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author noah0
 */
public class EnemySpawnerTest {
    
    public EnemySpawnerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of GetTimer method, of class EnemySpawner.
     */
    @org.junit.Test
    public void testGetTimer() {
        System.out.println("GetTimer");
        EnemySpawner instance = null;
        int expResult = 0;
        int result = instance.GetTimer();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of SetTimer method, of class EnemySpawner.
     */
    @org.junit.Test
    public void testSetTimer() {
        System.out.println("SetTimer");
        int Timer = 0;
        EnemySpawner instance = null;
        instance.SetTimer(Timer);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of spawnEnemy method, of class EnemySpawner.
     */
    @org.junit.Test
    public void testSpawnEnemy() {
        System.out.println("spawnEnemy");
        EnemySpawner instance = null;
        instance.spawnEnemy();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
