package com.m7mdmrq.testplugin.test_plugin.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class HelloWorld implements Command {

    @Override
    public int Run(String[] args, CommandSender sender, Plugin main) {
        sender.sendMessage("This is a test command!");
        return 0;
    }

    @Override
    public Senders senderLevel() {
        return Senders.BOTH;
    }

    @Override
    public boolean requirePerms() {
        return false;
    }

    @Override
    public String getName() {
        return "test";
    }
}
