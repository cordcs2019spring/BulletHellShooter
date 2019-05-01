/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mystudio.gamename;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mini2Dx.core.graphics.Graphics;

/**
 *
 * @author noah0
 */
public class PlayerShipTest {
    
    public PlayerShipTest() {
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
     * Test of GetBullets method, of class PlayerShip.
     */
    @org.junit.Test
    public void testGetBullets() {
        System.out.println("GetBullets");
        PlayerShip instance = null;
        ArrayList<AllyBullet> expResult = null;
        ArrayList<AllyBullet> result = instance.GetBullets();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of SetBullets method, of class PlayerShip.
     */
    @org.junit.Test
    public void testSetBullets() {
        System.out.println("SetBullets");
        ArrayList<AllyBullet> arr = null;
        PlayerShip instance = null;
        instance.SetBullets(arr);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of GetBulletsToDelete method, of class PlayerShip.
     */
    @org.junit.Test
    public void testGetBulletsToDelete() {
        System.out.println("GetBulletsToDelete");
        PlayerShip instance = null;
        ArrayList<AllyBullet> expResult = null;
        ArrayList<AllyBullet> result = instance.GetBulletsToDelete();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of SetBulletsToDelete method, of class PlayerShip.
     */
    @org.junit.Test
    public void testSetBulletsToDelete() {
        System.out.println("SetBulletsToDelete");
        ArrayList<AllyBullet> arr = null;
        PlayerShip instance = null;
        instance.SetBulletsToDelete(arr);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class PlayerShip.
     */
    @org.junit.Test
    public void testUpdate() {
        System.out.println("update");
        PlayerShip instance = null;
        instance.update();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of render method, of class PlayerShip.
     */
    @org.junit.Test
    public void testRender() {
        System.out.println("render");
        Graphics g = null;
        PlayerShip instance = null;
        instance.render(g);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of interpolate method, of class PlayerShip.
     */
    @org.junit.Test
    public void testInterpolate() {
        System.out.println("interpolate");
        float alpha = 0.0F;
        PlayerShip instance = null;
        instance.interpolate(alpha);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
