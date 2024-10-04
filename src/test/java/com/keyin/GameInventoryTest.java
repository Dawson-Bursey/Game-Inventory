package com.keyin;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class GameInventoryTest {
    private GameInventory inventory;

    @Before
    public void setUp() {
        inventory = new GameInventory();
    }

    @Test
    public void testAddItem() {
        inventory.addItem("Sword");
        assertTrue(inventory.hasItem("Sword"));
    }

    @Test
    public void testRemoveItem() {
        inventory.addItem("Shield");
        inventory.removeItem("Shield");
        assertFalse(inventory.hasItem("Shield"));
    }

    @Test
    public void testEquipWeapon() {
        inventory.addItem("Sword");
        inventory.equipWeapon("Sword");
        assertEquals("Sword", inventory.getEquippedWeapon());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEquipWeaponNotInInventory() {
        inventory.equipWeapon("Axe"); // Should throw exception
    }

    @Test
    public void testEquipShield() {
        inventory.addItem("Shield");
        inventory.equipShield("Shield");
        assertEquals("Shield", inventory.getEquippedShield());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEquipShieldNotInInventory() {
        inventory.equipShield("Wooden Shield"); // Should throw exception
    }

    @Test
    public void testRemoveEquippedItem() {
        inventory.addItem("Shield");
        inventory.equipShield("Shield");
        inventory.removeItem("Shield");
        assertNull(inventory.getEquippedShield());
    }

    @Test(expected = IllegalStateException.class)
    public void testCannotEquipTwoHandedWeaponWithShield() {
        inventory.addItem("Shield");
        inventory.addItem("Greatsword");
        inventory.equipShield("Shield");
        inventory.equipWeapon("Greatsword"); // Should throw exception
    }

    @Test(expected = IllegalStateException.class)
    public void testCannotEquipShieldWithTwoHandedWeapon() {
        inventory.addItem("Greatsword");
        inventory.addItem("Shield");
        inventory.equipWeapon("Greatsword");
        inventory.equipShield("Shield"); // Should throw exception
    }

    @Test
    public void testEquipOneHandedWeaponAndShield() {
        inventory.addItem("Sword");
        inventory.addItem("Shield");
        inventory.equipWeapon("Sword");
        inventory.equipShield("Shield");
        assertEquals("Sword", inventory.getEquippedWeapon());
        assertEquals("Shield", inventory.getEquippedShield());
    }
}
