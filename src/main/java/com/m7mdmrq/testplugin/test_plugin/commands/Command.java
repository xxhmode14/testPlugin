package com.m7mdmrq.testplugin.test_plugin.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public interface Command {
    int Run(String[] args, CommandSender sender, Plugin main);
    Senders senderLevel();

    default String usage() {
        return "Usage: /" + getName();
    }
    default boolean requirePerms() {
        return true;
    }
    default String getPerms(String[] args) {
        if(!requirePerms())
            return null;
        return getName() + ".use";
    }
    default String getDescription() {
        return "";
    };
    String getName();
}

