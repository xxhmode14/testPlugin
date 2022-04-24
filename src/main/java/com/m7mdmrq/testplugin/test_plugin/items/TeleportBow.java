package com.m7mdmrq.testplugin.test_plugin.items;

import com.m7mdmrq.testplugin.test_plugin.Test_plugin;
import com.m7mdmrq.testplugin.test_plugin.utils.PluginItem;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeleportBow implements PluginItem, Listener {

    private final ItemStack item;
    private final Test_plugin plugin;
    private final Map<String,Player> lastShot = new HashMap<>();
    private ShapedRecipe recipe;
    public TeleportBow(Test_plugin plugin) {
        this.plugin = plugin;
        ItemStack bow = new ItemStack(Material.BOW);
        ItemMeta meta = bow.getItemMeta();
        meta.setUnbreakable(true);
        meta.addEnchant(Enchantment.ARROW_DAMAGE,0,true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE,ItemFlag.HIDE_ENCHANTS);
        meta.displayName(Component.text("Teleport Bow"));
        meta.setCustomModelData(1);
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text("\u00A77Teleports the user to the hit position."));
        meta.lore(lore);
        bow.setItemMeta(meta);
        this.item = bow;
        this.registerRecipe();
    }

    @Override
    public ItemStack get() {
        return item;
    }

    @Override
    public String getName() {
        return "teleport_bow";
    }
    @Override
    public Boolean craftable() {
        return true;
    }

    @EventHandler
    public void onBowShot(ProjectileLaunchEvent event) {
        if(!(event.getEntity().getShooter() instanceof Player))
            return;
        Player plr = (Player) event.getEntity().getShooter();
        if(!plr.getInventory().getItemInMainHand().isSimilar(item))
            return;
        lastShot.put(plr.getUniqueId().toString(),plr);
    }

    @EventHandler
    public void onBowShotHit(ProjectileHitEvent event) {
        Block hitBlock = event.getHitBlock();
        if(hitBlock == null)
            return;
        if(!(event.getEntity().getShooter() instanceof Player))
            return;
        Location location = hitBlock.getLocation();
        Player plr = (Player) event.getEntity().getShooter();
        if(lastShot.get(plr.getUniqueId().toString()) == null)
            return;
        lastShot.remove(plr.getUniqueId().toString());
        float pitch = plr.getLocation().getPitch();
        float yaw = plr.getLocation().getYaw();
        location.add(new Vector(0,1,0));
        location.setPitch(pitch);
        location.setYaw(yaw);
        event.getEntity().remove();
        plr.teleport(location);
    }
    public ShapedRecipe recipe() {
        return this.recipe;
    }
    private void registerRecipe() {
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft(getName()),item);
        recipe.shape("st ",
                     "ept",
                     "st ");
        ItemStack string = new EnderString(plugin).get();
        ItemStack stick = new ItemStack(Material.STICK);
        ItemStack ender_eye = new ItemStack(Material.ENDER_EYE);
        ItemStack perl = new ItemStack(Material.ENDER_PEARL);
        recipe.setIngredient('s',string);
        recipe.setIngredient('t',stick);
        recipe.setIngredient('e',ender_eye);
        recipe.setIngredient('p',perl);
        this.recipe = recipe;
        this.plugin.getServer().addRecipe(recipe);
    }
}
