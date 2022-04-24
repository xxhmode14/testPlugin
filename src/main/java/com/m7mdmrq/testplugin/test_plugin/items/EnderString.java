package com.m7mdmrq.testplugin.test_plugin.items;

import com.m7mdmrq.testplugin.test_plugin.Test_plugin;
import com.m7mdmrq.testplugin.test_plugin.utils.PluginItem;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnderString implements PluginItem, Listener {
    private final ItemStack item;
    private final Test_plugin plugin;

    public EnderString(Test_plugin plugin) {
        this.plugin = plugin;
        ItemStack ender_string = new ItemStack(Material.STRING);
        ItemMeta meta = ender_string.getItemMeta();
        meta.setCustomModelData(1);
        meta.displayName(Component.text("\u00A75Ender String"));
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text("\u00A77rare drop from enderman."));
        meta.lore(lore);
        ender_string.setItemMeta(meta);
        this.item = ender_string;
    }

    @Override
    public ItemStack get() {
        return item;
    }

    @Override
    public String getName() {
        return "ender_string";
    }

    @Override
    public Boolean craftable() {
        return false;
    }

    @EventHandler
    public void onEndermanDie(EntityDeathEvent event) {
        LivingEntity entity = event.getEntity();
        if(!(entity instanceof Enderman))
            return;

        Random random = new Random();
        if(random.nextFloat() > .15)
            return;
        Location location = entity.getLocation();
        entity.getWorld().dropItem(location,item);
    }
}
