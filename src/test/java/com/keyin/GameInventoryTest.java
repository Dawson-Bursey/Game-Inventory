package com.keyin;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class GameInventoryTest {
    private GameInventory inventory;
    private Item sword;
    private Item shield;
    private Item chestArmor;
    private Item potion;

    @Before
    public void setUp() {
        inventory = new GameInventory();
        sword = new Item("Sword", ItemType.ONE_HANDED_WEAPON);
        shield = new Item("Shield", ItemType.SHIELD);
        chestArmor = new Item("Chestplate", ItemType.ARMOR_CHEST);
        potion = new Item("Health Potion", ItemType.POTION);
    }

    @Test
    public void testAddItem() {
        inventory.addItem(sword);
        assertTrue(inventory.getGeneralInventory().contains(sword));
    }

    @Test
    public void testEquipWeapon() {
        inventory.addItem(sword);
        inventory.equipWeapon(sword);
        assertEquals(sword, inventory.getEquippedWeapon());
    }

    @Test(expected = IllegalStateException.class)
    public void testEquipTwoHandedWeaponWithShield() {
        Item greatsword = new Item("Greatsword", ItemType.TWO_HANDED_WEAPON);
        inventory.addItem(greatsword);
        inventory.addItem(shield);
        inventory.equipShield(shield);
        inventory.equipWeapon(greatsword); // Should throw exception
    }

    @Test
    public void testEquipShield() {
        inventory.addItem(shield);
        inventory.equipShield(shield);
        assertEquals(shield, inventory.getEquippedShield());
    }

    @Test
    public void testEquipArmor() {
        inventory.addItem(chestArmor);
        inventory.equipArmor(chestArmor);
        assertEquals(chestArmor, inventory.getEquippedChest());
    }

    @Test
    public void testEquipPotion() {
        inventory.addItem(potion);
        inventory.equipPotion(potion);
        assertTrue(inventory.getPotionSlots().contains(potion));
    }

    @Test(expected = IllegalStateException.class)
    public void testPotionSlotsLimit() {
        Item potion1 = new Item("Potion1", ItemType.POTION);
        Item potion2 = new Item("Potion2", ItemType.POTION);
        Item potion3 = new Item("Potion3", ItemType.POTION);
        Item potion4 = new Item("Potion4", ItemType.POTION);

        inventory.addItem(potion1);
        inventory.addItem(potion2);
        inventory.addItem(potion3);
        inventory.addItem(potion4);

        inventory.equipPotion(potion1);
        inventory.equipPotion(potion2);
        inventory.equipPotion(potion3);

        // Trying to equip a fourth potion should throw exception
        inventory.equipPotion(potion4);
    }
}
