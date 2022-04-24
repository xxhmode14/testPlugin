package com.m7mdmrq.testplugin.test_plugin;

import com.m7mdmrq.testplugin.test_plugin.Events.NoMissUpEvent;
import com.m7mdmrq.testplugin.test_plugin.commands.GivePluginItem;
import com.m7mdmrq.testplugin.test_plugin.commands.HelloWorld;
import com.m7mdmrq.testplugin.test_plugin.commands.Recipies;
import com.m7mdmrq.testplugin.test_plugin.items.AngelRing;
import com.m7mdmrq.testplugin.test_plugin.items.EnderString;
import com.m7mdmrq.testplugin.test_plugin.items.TeleportBow;
import com.m7mdmrq.testplugin.test_plugin.utils.PluginItem;
import com.m7mdmrq.testplugin.test_plugin.utils.Senders;
import com.m7mdmrq.testplugin.test_plugin.commands.Smite;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public final class Test_plugin extends JavaPlugin {

    public List<PluginItem> pluginItems = new ArrayList<>();
    private final List<com.m7mdmrq.testplugin.test_plugin.utils.Command> commandList = new ArrayList<>();
    @Override
    public void onEnable() {
        // Registering commands
        registerCommand(new HelloWorld());
        registerCommand(new Smite());
        registerCommand(new GivePluginItem());
        registerCommand(new Recipies());

        // Registering items
        registerItem(new TeleportBow(this));
        registerItem(new AngelRing(this));
        registerItem(new EnderString(this));
        // Register events

        registerEvent(new NoMissUpEvent(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        commandList.forEach((cmd) -> {
            if(!label.equals(cmd.getName())) {
                List<String> aliases = cmd.getAliases();
                if (aliases == null)
                    return;
                for (int i = 0; i <= aliases.size() - 1; i++) {
                    if (label.equals(aliases.get(i)))
                        break;
                    if (i == aliases.size() - 1)
                        return;
                }
            }
            String perms = cmd.getPerms(args);
            Senders senderLevel = cmd.senderLevel();
            if(senderLevel != Senders.BOTH && senderLevel != getSenderLevel(sender))
                return;
            if(perms != null && !sender.hasPermission(perms)) {
                sender.sendMessage("You don't have permission to run this command!");
                return;
            }
            int res = cmd.Run(args,sender,this);
            if(res == 1)
                sender.sendMessage(cmd.usage());
        });
        return false;
    }

    private Senders getSenderLevel(CommandSender sender) {
        if(sender instanceof Player)
            return Senders.PLAYER;
        else
            return Senders.CONSOLE;
    }
    private void registerCommand(com.m7mdmrq.testplugin.test_plugin.utils.Command command) {
        if(command instanceof Listener)
            this.getServer().getPluginManager().registerEvents((Listener) command,this);
        commandList.add(command);
    }
    private void registerItem(PluginItem item) {
        if(item instanceof Listener)
            this.getServer().getPluginManager().registerEvents((Listener) item,this);
        pluginItems.add(item);
    }
    private void registerEvent(Listener event) {
        this.getServer().getPluginManager().registerEvents(event,this);
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        AtomicReference<List<String>> tabCompletion = new AtomicReference<>();
        commandList.forEach((cmd) -> {
            if(!command.getLabel().equals(cmd.getName()) && !alias.equals(cmd.getName()))
                return;
            tabCompletion.set(cmd.getTabCompletion(args, this));
        });
        return tabCompletion.get();
    }
}
