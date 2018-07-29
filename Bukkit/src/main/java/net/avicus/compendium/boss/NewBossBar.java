package net.avicus.compendium.boss;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.entity.Player;

public class NewBossBar extends BossBar {

    private org.bukkit.boss.BossBar bar;

    NewBossBar() {
        this.bar = Bukkit.createBossBar(
                "o_o",
                BarColor.PURPLE,
                BarStyle.SOLID
        );
    }

    NewBossBar(Player p) {
        this();
        this.bar.addPlayer(p);
    }

    public void destroy() {
        this.bar.getPlayers().forEach(this.bar::removePlayer);
        this.bar.removeAll();
        this.bar = null;
    }

    public BaseComponent[] getName() {
        BaseComponent[] bc = new BaseComponent[] { new TextComponent(this.bar.getTitle()) };
        return bc;
    }

    public void setName(BaseComponent name) {
        this.bar.setTitle(name.toLegacyText());
    }

    public void setName(BaseComponent[] name) {
        BaseComponent bc = new TextComponent();
        for (BaseComponent baseComponent : name) {
            bc.addExtra(" ");
            bc.addExtra(bc);
        }
        this.bar.setTitle(bc.toLegacyText());
    }

    public Double getPercent() {
        return this.bar.getProgress();
    }

    public void setPercent(double percent) {
        this.bar.setProgress(percent);
    }

    public BarColor getColor() {
        return this.bar.getColor();
    }

    public void setColor(BarColor color) {
        this.bar.setColor(color);
    }

    public org.bukkit.boss.BossBar getBukkitBar() {
        return this.bar;
    }

    public void setBar(org.bukkit.boss.BossBar bar) {
        this.bar = bar;
    }
}
