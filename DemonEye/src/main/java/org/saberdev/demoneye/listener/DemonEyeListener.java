package org.saberdev.demoneye.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.saberdev.demoneye.struct.ItemDemonEye;
import org.saberdev.demoneye.util.CC;
import org.saberdev.demoneye.util.Cooldowns;

/**
 * @Author: Driftay
 * @Date: 2/13/2022 12:47 AM
 */
public class DemonEyeListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onRedeem(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Player player = event.getPlayer();

            if (!player.getWorld().getName().equals("world")) {
                return;
            }

            ItemStack item = player.getItemInHand();
            if (ItemDemonEye.isDemonEye(item)) {
                if (Cooldowns.isOnCooldown(player, "demonEye")) {
                    player.sendMessage(CC.translate("&d&lDemonEye &8» &fYou may not use this for &a" + Cooldowns.sendCooldownLeft(player, "demonEye") + " seconds&f."));
                    return;
                }

                if (!player.hasMetadata("lastHitByPlayer")) {
                    player.sendMessage(CC.translate("&d&lDemonEye &8» &f&nNo Previous Damager&8&o ((&7&oLast &a&o10 seconds&8&o))"));
                    player.sendMessage("");
                    return;
                }

                DemonEyeHandler.handleDemonEye(player);
                if (player.getItemInHand().getAmount() > 1) {
                    player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
                } else {
                    player.setItemInHand(null);
                }

                player.updateInventory();
            }
        }
    }
}
