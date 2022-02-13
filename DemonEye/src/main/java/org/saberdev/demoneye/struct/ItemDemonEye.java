package org.saberdev.demoneye.struct;

import com.cryptomorin.xseries.XMaterial;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.saberdev.demoneye.util.CC;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: Driftay
 * @Date: 2/13/2022 12:40 AM
 */
public class ItemDemonEye {

    public static Material material = XMaterial.ENDER_EYE.parseMaterial();

    public static ItemStack getItem(int amount) {
        ItemStack item = new ItemStack(getMaterial());
        ItemMeta meta = item.getItemMeta();
        meta.setLore(getLore());
        meta.setDisplayName(getDisplayName());
        item.setItemMeta(meta);
        item.setAmount(amount);
        NBTItem nbtItem = new NBTItem(item);
        nbtItem.setBoolean("demonEye", true);
        return nbtItem.getItem();
    }

    public static Material getMaterial() {
        return material;
    }

    public static List<String> getLore() {
        return Arrays.asList(
                CC.translate("&7Right-click in hand to teleport you"),
                CC.translate("&7to the last person that damaged you"),
                CC.translate("&7up to 10 seconds prior."),
                CC.translate(""),
                CC.translate("&d&lTeleport Time: &f3 Seconds"),
                CC.translate("&d&lCooldown: &f45 Seconds")
        );
    }

    public static String getDisplayName() {
        return CC.DarkPurpleB + "Demon Eye " + "(Right Click)";
    }

    public static boolean isDemonEye(ItemStack item) {
        if (item == null || item.getType() != material) {
            return false;
        }
        NBTItem nbtItem = new NBTItem(item);

        return nbtItem.hasKey("demonEye");
    }

}
