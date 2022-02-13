package org.saberdev.demoneye.commands;/**
 * @Author: Driftay
 * @Date: 2/13/2022 12:51 AM
 */

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.saberdev.demoneye.struct.ItemDemonEye;
import org.saberdev.demoneye.util.CC;

public class DemonEyeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;

        if(args.length == 2) {
            Player target = Bukkit.getPlayer(args[0]);
            if(target == null) {
                player.sendMessage(CC.translate("&c&l[!] &cPlayer is not online!"));
                return false;
            }

            int amount = Integer.parseInt(args[1]);
            if (args[1] == null) {
                amount = 1;
            }

            player.getInventory().addItem(ItemDemonEye.getItem(amount));
            player.sendMessage(CC.translate("&a&l[!] &7Given &bx" + amount + " &6&lDemon Eye(s) &7to " + target.getName()));
        } else  {
            player.sendMessage(CC.translate("&c&l[!] &7Try /givedemoneye <player> <amount>"));
        }
        return false;
    }
}
