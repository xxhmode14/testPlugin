package com.m7mdmrq.testplugin.test_plugin.Events;

import com.m7mdmrq.testplugin.test_plugin.Test_plugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class NoMissUpEvent implements Listener {
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if(event.getItemInHand().getItemMeta().hasCustomModelData())
            event.setCancelled(true);
    }
}
