package com.m7mdmrq.testplugin.test_plugin;

import com.m7mdmrq.testplugin.test_plugin.commands.HelloWorld;
import com.m7mdmrq.testplugin.test_plugin.commands.Senders;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public final class Test_plugin extends JavaPlugin {

    private List<com.m7mdmrq.testplugin.test_plugin.commands.Command> commandList = new ArrayList<>();

    @Override
    public void onEnable() {
        registerCommand(new HelloWorld());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        commandList.forEach((cmd) -> {
            if(!label.equals(cmd.getName()))
                return;

            String perms = cmd.getPerms(args);
            Senders senderLevel = cmd.senderLevel();
            if(senderLevel != Senders.BOTH && senderLevel != getSenderLevel(sender))
                return;
            if(perms != null && !sender.hasPermission(perms))
                return;

            int res = cmd.Run(args,sender,this);
            if(res == 1)
                sender.sendMessage(cmd.usage());
        });
        return false;
    }

    private void registerCommand(com.m7mdmrq.testplugin.test_plugin.commands.Command command) {
        commandList.add(command);
    }

    private Senders getSenderLevel(CommandSender sender) {
        if(sender instanceof Player)
            return Senders.PLAYER;
        else
            return Senders.CONSOLE;
    }
}
