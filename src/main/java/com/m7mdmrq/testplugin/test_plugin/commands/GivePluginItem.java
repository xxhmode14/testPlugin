package com.m7mdmrq.testplugin.test_plugin.commands;

import com.m7mdmrq.testplugin.test_plugin.Test_plugin;
import com.m7mdmrq.testplugin.test_plugin.utils.Command;
import com.m7mdmrq.testplugin.test_plugin.utils.PluginItem;
import com.m7mdmrq.testplugin.test_plugin.utils.Senders;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.ArrayList;
import java.util.List;

import static com.m7mdmrq.testplugin.test_plugin.utils.Utils.*;

public class GivePluginItem implements Command {


    @Override
    public int Run(String[] args, CommandSender sender, Test_plugin main) {
        String playerToGive = null;
        if(args.length != 1) {
            if(args.length == 2)
                playerToGive = args[1];
            else
                return 1;
        }else{
            playerToGive = sender.getName();
        }
        String itemName = args[0];
        PluginItem pluginItem = getPluginItemByName(main,itemName);

        if (pluginItem == null)
            return 1;
        Player plr = main.getServer().getPlayer(playerToGive);
        if(plr == null)
            return 1;
        pluginItem.give(plr);
        return 0;
    }

    @Override
    public Senders senderLevel() {
        return Senders.PLAYER;
    }

    @Override
    public @NotNull String usage() {
        return "Usage: /" + getName() + " <item> <Optional:Player>";
    }
    @Nullable
    @Override
    public List<String> getTabCompletion(String[] args, Test_plugin main) {
        if(args.length == 1)
            return getPluginItems(main);
        else if(args.length == 2)
            return getOnlinePlayerNames(main);
        return null;
    }

    @Override
    public @NotNull String getName() {
        return "pluginitem";
    }

    @Nullable
    @Override
    public List<String> getAliases() {
        List<String> aliases = new ArrayList<String>();
        aliases.add("giveitem");
        return aliases;
    }
}
