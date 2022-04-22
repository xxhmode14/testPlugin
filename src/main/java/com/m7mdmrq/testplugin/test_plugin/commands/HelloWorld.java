package com.m7mdmrq.testplugin.test_plugin.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

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

    @Nullable
    @Override
    public List<String> getAliases() {
        List<String> aliases = new ArrayList<>();
        aliases.add("hello_world");
        aliases.add("testing");
        return aliases;
    }
}
