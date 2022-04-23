package com.m7mdmrq.testplugin.test_plugin.utils;

import com.m7mdmrq.testplugin.test_plugin.Test_plugin;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static List<String> getOnlinePlayerNames(Plugin main) {
        List<String> players = new ArrayList<>();
        main.getServer().getOnlinePlayers().forEach((plr) -> players.add(plr.getName()));
        return players;
    }

    public static List<String> getPluginItems(Test_plugin main) {
        List<String> items = new ArrayList<>();
        main.pluginItems.forEach((item) -> items.add(item.getName()));
        return items;
    }
}
