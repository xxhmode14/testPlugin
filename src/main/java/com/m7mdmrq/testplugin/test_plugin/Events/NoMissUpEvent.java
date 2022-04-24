package com.m7mdmrq.testplugin.test_plugin.Events;

import com.m7mdmrq.testplugin.test_plugin.Test_plugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class NoMissUpEvent implements Listener {

    private final Test_plugin plugin;

    public NoMissUpEvent(Test_plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if(event.getItemInHand().getItemMeta().getCustomModelData() > 0)
            event.setCancelled(true);
    }
}
