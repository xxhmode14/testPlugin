package com.m7mdmrq.testplugin.test_plugin.utils;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public interface PluginItem {
    ItemStack get();
    String getName();
    default void give(Player plr) {
        PlayerInventory inv = plr.getInventory();
        if(inv.firstEmpty() == -1)
            plr.getWorld().dropItem(plr.getLocation(),get());
        else
            inv.addItem(get());
    }
}
