package com.keyin;

import java.util.ArrayList;
import java.util.List;

public class GameInventory {
    private List<Item> generalInventory;
    private Item equippedWeapon;
    private Item equippedShield;
    private Item equippedChest;
    private Item equippedLegs;
    private Item equippedFeet;
    private Item equippedShoulders;
    private Item equippedArms;
    private Item equippedWaist;
    private Item equippedHead;
    private List<Item> potionSlots;

    private final int MAX_POTION_SLOTS = 3;

    public GameInventory() {
        this.generalInventory = new ArrayList<>();
        this.potionSlots = new ArrayList<>();
    }

    // Add an item to general inventory
    public void addItem(Item item) {
        generalInventory.add(item);
    }

    // Remove an item from general inventory
    public void removeItem(Item item) {
        generalInventory.remove(item);
        if (item.equals(equippedWeapon)) equippedWeapon = null;
        if (item.equals(equippedShield)) equippedShield = null;
        if (item.equals(equippedChest)) equippedChest = null;
        if (item.equals(equippedLegs)) equippedLegs = null;
        if (item.equals(equippedFeet)) equippedFeet = null;
        if (item.equals(equippedShoulders)) equippedShoulders = null;
        if (item.equals(equippedArms)) equippedArms = null;
        if (item.equals(equippedWaist)) equippedWaist = null;
        if (item.equals(equippedHead)) equippedHead = null;
    }

    // Equip a weapon
    public void equipWeapon(Item weapon) {
        if (weapon.getType() == ItemType.TWO_HANDED_WEAPON && equippedShield != null) {
            throw new IllegalStateException("Cannot equip a two-handed weapon while holding a shield.");
        }

        if (generalInventory.contains(weapon) && (weapon.getType() == ItemType.ONE_HANDED_WEAPON || weapon.getType() == ItemType.TWO_HANDED_WEAPON)) {
            this.equippedWeapon = weapon;
        } else {
            throw new IllegalArgumentException("Weapon not found in inventory or wrong item type.");
        }
    }

    // Equip a shield
    public void equipShield(Item shield) {
        if (equippedWeapon != null && equippedWeapon.getType() == ItemType.TWO_HANDED_WEAPON) {
            throw new IllegalStateException("Cannot equip a shield while holding a two-handed weapon.");
        }

        if (generalInventory.contains(shield) && shield.getType() == ItemType.SHIELD) {
            this.equippedShield = shield;
        } else {
            throw new IllegalArgumentException("Shield not found in inventory or wrong item type.");
        }
    }

    // Equip armor to specific slots
    public void equipArmor(Item armor) {
        if (!generalInventory.contains(armor)) {
            throw new IllegalArgumentException("Armor not found in inventory.");
        }

        switch (armor.getType()) {
            case ARMOR_CHEST:
                equippedChest = armor;
                break;
            case ARMOR_LEGS:
                equippedLegs = armor;
                break;
            case ARMOR_FEET:
                equippedFeet = armor;
                break;
            case ARMOR_SHOULDERS:
                equippedShoulders = armor;
                break;
            case ARMOR_ARMS:
                equippedArms = armor;
                break;
            case ARMOR_WAIST:
                equippedWaist = armor;
                break;
            case ARMOR_HEAD:
                equippedHead = armor;
                break;
            default:
                throw new IllegalArgumentException("Invalid armor type.");
        }
    }

    // Equip a potion
    public void equipPotion(Item potion) {
        if (potion.getType() != ItemType.POTION) {
            throw new IllegalArgumentException("Item is not a potion.");
        }

        if (potionSlots.size() >= MAX_POTION_SLOTS) {
            throw new IllegalStateException("Potion slots are full.");
        }

        potionSlots.add(potion);
    }

    // Remove potion
    public void removePotion(Item potion) {
        potionSlots.remove(potion);
    }

    // Getters for equipped items and inventory
    public Item getEquippedWeapon() {
        return equippedWeapon;
    }

    public Item getEquippedShield() {
        return equippedShield;
    }

    public Item getEquippedChest() {
        return equippedChest;
    }

    public Item getEquippedLegs() {
        return equippedLegs;
    }

    public Item getEquippedFeet() {
        return equippedFeet;
    }

    public Item getEquippedShoulders() {
        return equippedShoulders;
    }

    public Item getEquippedArms() {
        return equippedArms;
    }

    public Item getEquippedWaist() {
        return equippedWaist;
    }

    public Item getEquippedHead() {
        return equippedHead;
    }

    public List<Item> getPotionSlots() {
        return potionSlots;
    }

    public List<Item> getGeneralInventory() {
        return generalInventory;
    }
}
