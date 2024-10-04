package com.keyin;

import java.util.ArrayList;
import java.util.List;

public class GameInventory {
    private List<String> items;
    private String equippedWeapon;
    private String equippedShield;

    // List of two-handed weapons for simplicity
    private List<String> twoHandedWeapons;

    public GameInventory() {
        this.items = new ArrayList<>();
        this.equippedWeapon = null;
        this.equippedShield = null;

        // Define which weapons are two-handed
        this.twoHandedWeapons = new ArrayList<>();
        twoHandedWeapons.add("Greatsword");
        twoHandedWeapons.add("Bow");
        twoHandedWeapons.add("Battle Axe");
    }

    public void addItem(String item) {
        items.add(item);
    }

    public void removeItem(String item) {
        items.remove(item);
        if (item.equals(equippedWeapon)) {
            equippedWeapon = null;
        }
        if (item.equals(equippedShield)) {
            equippedShield = null;
        }
    }

    public boolean hasItem(String item) {
        return items.contains(item);
    }

    public List<String> listItems() {
        return items;
    }

    // Equip a weapon from the inventory
    public void equipWeapon(String weapon) {
        if (!items.contains(weapon)) {
            throw new IllegalArgumentException("Weapon not found in inventory.");
        }

        // Check if it's a two-handed weapon
        boolean isTwoHanded = twoHandedWeapons.contains(weapon);

        if (isTwoHanded && equippedShield != null) {
            throw new IllegalStateException("Cannot equip a two-handed weapon while holding a shield.");
        }

        if (isTwoHanded && equippedWeapon != null && !twoHandedWeapons.contains(equippedWeapon)) {
            throw new IllegalStateException("Cannot equip a two-handed weapon while holding a one-handed weapon.");
        }

        // Equip the weapon
        this.equippedWeapon = weapon;
    }

    // Equip a shield from the inventory
    public void equipShield(String shield) {
        if (!items.contains(shield)) {
            throw new IllegalArgumentException("Shield not found in inventory.");
        }

        // Can't equip a shield if a two-handed weapon is equipped
        if (equippedWeapon != null && twoHandedWeapons.contains(equippedWeapon)) {
            throw new IllegalStateException("Cannot equip a shield while holding a two-handed weapon.");
        }

        // Equip the shield
        this.equippedShield = shield;
    }

    // Check the currently equipped weapon
    public String getEquippedWeapon() {
        return equippedWeapon;
    }

    // Check the currently equipped shield
    public String getEquippedShield() {
        return equippedShield;
    }
}
