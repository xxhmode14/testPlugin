package com.m7mdmrq.testplugin.test_plugin.utils;

import com.m7mdmrq.testplugin.test_plugin.Test_plugin;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Utils {
    public static List<String> getOnlinePlayerNames(Plugin main) {
        List<String> players = new ArrayList<>();
        main.getServer().getOnlinePlayers().forEach((plr) -> {
            players.add(plr.getName());
        });
        return players;
    }

    public static List<String> getPluginItems(Test_plugin main) {
        List<String> items = new ArrayList<>();
        main.pluginItems.forEach((item) -> {
            items.add(item.getName());
        });
        return items;
    }

    public static List<String> getCraftablePluginItems(Test_plugin main) {
        List<String> items = new ArrayList<>();
        main.pluginItems.forEach((item) -> {
            if(!item.craftable())
                return;
            items.add(item.getName());
        });
        return items;
    }

    public static PluginItem getPluginItemByName(Test_plugin main,String name) {
        AtomicReference<PluginItem> pluginItem = new AtomicReference<>();
        main.pluginItems.forEach((item) -> {
            if (item.getName().equals(name)) {
                pluginItem.set(item);
                return;
            }
        });
        return pluginItem.get();
    }
}
