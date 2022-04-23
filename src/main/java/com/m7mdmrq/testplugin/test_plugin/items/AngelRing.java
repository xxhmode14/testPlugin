package com.m7mdmrq.testplugin.test_plugin.items;

import com.m7mdmrq.testplugin.test_plugin.Test_plugin;
import com.m7mdmrq.testplugin.test_plugin.utils.PluginItem;
import net.kyori.adventure.text.Component;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class AngelRing extends BukkitRunnable implements PluginItem {
    private final ItemStack item;
    private final Test_plugin plugin;
    public AngelRing(Test_plugin plugin) {
        ItemStack ring = new ItemStack(Material.GOLD_INGOT);
        ItemMeta meta = ring.getItemMeta();
        meta.displayName(Component.text("Angel ring"));
        meta.addEnchant(Enchantment.DAMAGE_ALL,0,true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.setCustomModelData(1);
        ring.setItemMeta(meta);
        this.plugin = plugin;
        this.runTaskTimer(this.plugin,5,20);
        this.item = ring;
    }
    @Override
    public ItemStack get() {
        return item;
    }

    @Override
    public String getName() {
        return "angel_ring";
    }

    @Override
    public void run() {
        this.plugin.getServer().getOnlinePlayers().forEach((plr) -> {
            if(plr.getGameMode() != GameMode.SURVIVAL)
                return;
            plr.setAllowFlight(plr.getInventory().contains(this.item));
        });
    }
}
