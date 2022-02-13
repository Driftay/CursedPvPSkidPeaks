package org.saberdev.demoneye;

import org.bukkit.plugin.java.JavaPlugin;
import org.saberdev.demoneye.commands.DemonEyeCommand;
import org.saberdev.demoneye.listener.DemonEyeListener;

/**
 * @Author: Driftay
 * @Date: 2/13/2022 12:39 AM
 */
public class DemonEyePlugin extends JavaPlugin {

    public static DemonEyePlugin instance;

    public void onEnable() {
        instance = this;
        getServer().getPluginManager().registerEvents(new DemonEyeListener(), this);
        getCommand("givedemoneye").setExecutor(new DemonEyeCommand());
    }

    public static DemonEyePlugin getInstance() {
        return instance;
    }
}
