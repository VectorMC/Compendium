package net.avicus.compendium.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PlayerConnectionVersion {
    public static int getProtocolVersion(Player p) {
        int id;
        try {
            if(Bukkit.getPluginManager().isPluginEnabled("ProtocolSupport")) {
                id = protocolsupport.api.ProtocolSupportAPI.getProtocolVersion(p).getId();
            } else if(Bukkit.getPluginManager().isPluginEnabled("ViaVersion")) {
                id = us.myles.ViaVersion.api.Via.getAPI().getPlayerVersion(p.getUniqueId());
            } else {
                id = p.getProtocolVersion();
            }
        } catch(Exception e) {
            id = 0;
            e.printStackTrace();
        }

        return id;
    }

    public static int getProtocolVersion(UUID u) {
        return getProtocolVersion(Bukkit.getPlayer(u));
    }
}
