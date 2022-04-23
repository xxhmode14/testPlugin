package com.m7mdmrq.testplugin.test_plugin.utils;

import com.m7mdmrq.testplugin.test_plugin.Test_plugin;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

public interface Command {
    @NotNull int Run(String[] args, CommandSender sender, Test_plugin main);
    Senders senderLevel();

    default @NotNull String usage() {
        return "Usage: /" + getName();
    }
    default @NotNull boolean requirePerms() {
        return true;
    }
    default @Nullable String getPerms(String[] args) {
        if(!requirePerms())
            return null;
        return getName() + ".use";
    }
    default @Nullable String getDescription() {
        return null;
    };

    default @Nullable List<String> getTabCompletion(String[] args,Test_plugin main) {
        return null;
    }

    default @Nullable List<String> getAliases() {
        return null;
    }
    @NotNull String getName();
}

