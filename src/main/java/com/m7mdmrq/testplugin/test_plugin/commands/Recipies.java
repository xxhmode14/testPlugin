package com.m7mdmrq.testplugin.test_plugin.commands;

import com.m7mdmrq.testplugin.test_plugin.Test_plugin;
import com.m7mdmrq.testplugin.test_plugin.utils.Command;
import com.m7mdmrq.testplugin.test_plugin.utils.PluginItem;
import com.m7mdmrq.testplugin.test_plugin.utils.Senders;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

import static com.m7mdmrq.testplugin.test_plugin.utils.Utils.*;

public class Recipies implements Command, Listener {
    private final Map<PluginItem,Inventory> recipiesInventory = new HashMap<>();
    @Override
    public int Run(String[] args, CommandSender sender, Test_plugin main) {
        initGUI(main);
        if(args.length != 1)
            return 1;
        String itemName = args[0];
        PluginItem item = getPluginItemByName(main,itemName);
        if(item == null || !item.craftable())
            return 1;
        Player plr = (Player) sender;
        plr.openInventory(recipiesInventory.get(item));
        return 0;
    }

    @Override
    public Senders senderLevel() {
        return Senders.PLAYER;
    }

    @Override
    public boolean requirePerms() {
        return false;
    }

    @Override
    public @NotNull String getName() {
        return "recipe";
    }

    @Nullable
    @Override
    public List<String> getTabCompletion(String[] args, Test_plugin main) {
        return getCraftablePluginItems(main);
    }

    @Override
    public @NotNull String usage() {
        return "Usage: /" + getName() + " <Item>";
    }

    private void initGUI(Test_plugin main) {
        main.pluginItems.forEach((item) -> {
            if(!item.craftable())
                return;
            ShapedRecipe recipe = item.recipe();
            Inventory itemInv = main.getServer().createInventory(null,9*6);
            for(int i = 0;i <= itemInv.getSize()-1;i++) {
                itemInv.setItem(i,new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
            }
            List<Character> charList = new ArrayList<>();
            for(String listOfKeys : recipe.getShape()) {
                char[] cList = listOfKeys.toCharArray();
                for (char key : cList) {
                    charList.add(key);
                }
            }
            for(int i=0;i<=2;i++) {
                for(int i2=0;i2<=2;i2++) {
                    ItemStack recipeItem = recipe.getIngredientMap().get(charList.get(0));
                    if(recipeItem == null)
                        recipeItem = new ItemStack(Material.AIR);
                    itemInv.setItem(19+(i2 + (i*9)),recipeItem);
                    charList.remove(0);
                }
            }
            itemInv.setItem(32,new ItemStack(Material.GREEN_STAINED_GLASS_PANE));
            itemInv.setItem(34,recipe.getResult());
            recipiesInventory.put(item,itemInv);
        });
    }

    @EventHandler
    public void onItemStolenFromGUI(InventoryClickEvent event) {
        if(recipiesInventory.containsValue(event.getInventory())) {
            event.setCancelled(true);
        }
    }
}
