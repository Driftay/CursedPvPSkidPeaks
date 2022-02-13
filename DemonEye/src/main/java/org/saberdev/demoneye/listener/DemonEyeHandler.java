package org.saberdev.demoneye.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.saberdev.demoneye.DemonEyePlugin;
import org.saberdev.demoneye.util.CC;
import org.saberdev.demoneye.util.Cooldowns;
import org.saberdev.demoneye.util.CountdownTimer;
import org.saberdev.demoneye.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * @Author: Driftay
 * @Date: 2/11/2022 8:40 AM
 */
public class DemonEyeHandler {


    public static void handleDemonEye(Player player) {
        Cooldowns.setCooldown(player, "demonEye", 45);
        UUID uuid = UUID.fromString(player.getMetadata("lastHitByPlayer").get(0).asString());
        Player teleportTarget = Bukkit.getPlayer(uuid);
        if (teleportTarget == null || !teleportTarget.isOnline()) {
            return;
        }

        CountdownTimer countdownTimer = new CountdownTimer(DemonEyePlugin.getInstance(), 3, (e) -> {
            if (e.getSecondsLeft() >= 1) {
                int seconds = e.getSecondsLeft();
                String time = seconds > 1 ? CC.translate("&a" + seconds + " seconds") : CC.translate("&a" + seconds + " second");
                List<String> messageList = Arrays.asList(
                        StringUtils.getCenteredMessage(""),
                        StringUtils.getCenteredMessage("&d&lDemonEye Consumed"),
                        StringUtils.getCenteredMessage(" &7&l■ &fTarget: &d" + teleportTarget.getName()),
                        StringUtils.getCenteredMessage(" &7&l■ &fTeleport In: &d" + time),
                        StringUtils.getCenteredMessage("")
                );
                messageList.forEach(player::sendMessage);
            }
        }, () -> {
            if(!teleportTarget.getWorld().getName().equals("world")) {
                player.sendMessage(CC.translate("&d&lDemonEye &8» Your target has gone out of the overworld!"));
                return;
            }

            player.teleport(teleportTarget);
            player.sendMessage(CC.translate("&d&lDemonEye &8» &fTeleported to &a" + teleportTarget.getName() + " successfully&f!"));
        });
        countdownTimer.scheduleTimer();
    }


}
