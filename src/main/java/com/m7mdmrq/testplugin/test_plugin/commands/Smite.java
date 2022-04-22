package com.m7mdmrq.testplugin.test_plugin.commands;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Smite implements Command {
    @Override
    public int Run(String[] args, CommandSender sender, Plugin main) {
        if(args.length <= 0 || args.length > 1)
            return 1;

        Player plr = main.getServer().getPlayer(args[0]);
        if(plr == null)
            return 1;

        World world = plr.getWorld();
        Location location = plr.getLocation();
        world.strikeLightning(location);
        world.playSound(location, Sound.ENTITY_LIGHTNING_BOLT_THUNDER,1,1);
        plr.sendMessage("Thor has spoken...");
        sender.sendMessage("boom.");
        return 0;
    }

    @Override
    public Senders senderLevel() {
        return Senders.BOTH;
    }

    @Override
    public String usage() {
        return "Usage: /smite <Player>";
    }

    @Override
    public String getName() {
        return "smite";
    }

    @Override
    public List<String> getTabCompletion(String[] args, Plugin main) {
        List<String> players = new ArrayList<>();
        main.getServer().getOnlinePlayers().forEach((plr) -> {
            players.add(plr.getName());
        });
        return players;
    }

    @Nullable
    @Override
    public List<String> getAliases() {
        List<String> aliases = new ArrayList<String>();
        aliases.add("thor");
        return aliases;
    }
}
