package org.saberdev.demoneye.util;

import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.saberdev.demoneye.DemonEyePlugin;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Driftay
 * @Date: 2/13/2022 12:48 AM
 */
public class Cooldowns {

    public static void setCooldown(Player player, String name, int seconds) {
        player.setMetadata(name, new FixedMetadataValue(DemonEyePlugin.getInstance(), System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(seconds)));
    }

    public static String sendCooldownLeft(Player player, String name) {
        if (player.hasMetadata(name) || player.getMetadata(name).size() > 0) {
            long remaining = player.getMetadata(name).get(0).asLong() - System.currentTimeMillis();
            int remainSec = (int) (remaining / 1000L);
            return formatSeconds(remainSec);
        }
        return "";
    }


    public static boolean isOnCooldown(Player player, String name) {
        if (!player.hasMetadata(name) || player.getMetadata(name).size() <= 0) return false;
        long time = player.getMetadata(name).get(0).asLong();
        return (time > System.currentTimeMillis());
    }


    public static String formatSeconds(int timeInSeconds) {
        int hours = timeInSeconds / 3600;
        int secondsLeft = timeInSeconds - hours * 3600;
        int minutes = secondsLeft / 60;
        int seconds = secondsLeft - minutes * 60;
        String formattedTime = "";
        if (seconds < 10)
            formattedTime = formattedTime + "0";
        formattedTime = formattedTime + seconds;
        return formattedTime;
    }
}
