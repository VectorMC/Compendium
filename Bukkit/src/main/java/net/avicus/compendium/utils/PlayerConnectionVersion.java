package net.avicus.compendium.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import protocolsupport.api.ProtocolSupportAPI;
import us.myles.ViaVersion.api.Via;

import java.util.UUID;

public class PlayerConnectionVersion {
    public static int getProtocolVersion(Player p) {
        try {
            return ProtocolSupportAPI.getProtocolVersion(p).getId();
        } catch(Exception psnothere) {
            try {
                return Via.getAPI().getPlayerVersion(p.getUniqueId());
            } catch(Exception e) {
                psnothere.printStackTrace();
                e.printStackTrace();
            }
        }
        return p.getProtocolVersion();
    }

    public static int getProtocolVersion(UUID u) {
        return getProtocolVersion(Bukkit.getPlayer(u));
    }
}
