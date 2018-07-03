package net.avicus.compendium.boss;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.inventivetalent.bossbar.BossBarAPI;

/**
 * A modern boss bar.
 * <p>
 * <p>A modern boss bar is used for 1.9, 1.10, and 1.11 clients.</p>
 */
public class ModernBossBar extends BossBar {

    /**
     * An array of colors that a modern boss bar supports.
     */
    private static final BossBarAPI.Color[] COLORS = BossBarAPI.Color.values();
    /**
     * An array of overlays that a modern boss bar supports.
     */
    private static final BossBarAPI.Style[] OVERLAYS = BossBarAPI.Style.values();
    /**
     * The boss bar container.
     */
    //private us.myles.ViaVersion.api.boss.BossBar<Player> bar;
    private org.inventivetalent.bossbar.BossBar bar;

    /**
     * Constructs a new modern boss bar.
     *
     * @param player the player who is viewing this boss bar
     */
    ModernBossBar(Player player) {
        // A normal (as seen in versions before 1.9) boss bar has a PROGRESS (SOLID) overlay and is PINK in color
        // this.bar = Via.getAPI().createBossBar("o_o", BossColor.PINK, BossStyle.SOLID);
        this.bar = BossBarAPI.addBar(player, // The receiver of the BossBar
                new TextComponent("o_o"), // Displayed message
                BossBarAPI.Color.PINK, // Color of the bar
                BossBarAPI.Style.PROGRESS, // Bar style
                1.0f, // Progress (0.0 - 1.0)
                20, // Timeout
                2);
        this.bar.addPlayer(player);
    }

    @Override
    public void destroy() {
        final Player player = this.bar.getPlayers().iterator().next();
        this.bar.removePlayer(player);
        this.bar = null;
    }

    @Override
    public ModernBossBar setName(BaseComponent[] name) {
        if (name != this.name) {
            super.setName(name);
            this.bar.setMessage(BaseComponent.toLegacyText(name));
        }

        return this;
    }

    @Override
    public ModernBossBar setPercent(float percent) {
        if (percent != this.percent) {
            super.setPercent(percent);
            this.bar.setHealth(percent);
        }

        return this;
    }

    @Override
    public ModernBossBar setColor(BossBarColor color) {
        if (color != this.color) {
            super.setColor(color);
            this.bar.setColor(COLORS[color.ordinal()]);
        }

        return this;
    }

    @Override
    public ModernBossBar setOverlay(BossBarOverlay overlay) {
        if (overlay != this.overlay) {
            super.setOverlay(overlay);
            this.bar.setStyle(OVERLAYS[overlay.ordinal()]);
        }

        return this;
    }

    @Override
    public ModernBossBar setDarkenSky(boolean darkenSky) {
        if (darkenSky != this.darkenSky) {
            super.setDarkenSky(darkenSky);

            this.bar.setProperty(BossBarAPI.Property.DARKEN_SKY, darkenSky);
        }

        return this;
    }

    @Override
    public ModernBossBar setPlayEndBossMusic(boolean playEndBossMusic) {
        if (playEndBossMusic != this.playEndBossMusic) {
            super.setPlayEndBossMusic(playEndBossMusic);

            this.bar.setProperty(BossBarAPI.Property.PLAY_MUSIC, playEndBossMusic);
        }

        return this;
    }

    /*
     * This method intentionally uses PLAY_BOSS_MUSIC for addFlag and removeFlag - vanilla
     * treats them both (PLAY_BOSS_MUSIC and CREATE_FOG) the same in the network, so ViaVersion
     * decided not to add a BossFlag for it.
     */
    @Override
    public ModernBossBar setCreateFog(boolean createFog) {
        if (createFog != this.createFog) {
            super.setCreateFog(createFog);

            this.bar.setProperty(BossBarAPI.Property.CREATE_FOG, createFog);
        }

        return this;
    }

    @Override
    public BossBar setVisible(boolean visible) {
        if (visible != this.visible) {
            this.bar.setVisible(visible);
        }

        return super.setVisible(visible);
    }
}
