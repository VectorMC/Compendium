package net.avicus.compendium.boss;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import net.avicus.compendium.plugin.CompendiumPlugin;
import net.avicus.compendium.utils.NMSHacks;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.entity.Player;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class OldBossBar extends BossBar {

    private final Map<Player, NMSHacks.FakeWither> views = new HashMap<>();
    private final Map<Player, Integer> tasks = new HashMap<>();

    private BaseComponent title;
    private double progress;
    private boolean visible;

    public OldBossBar(Player player) {
        this.title = new TextComponent("o_o");
        this.progress = 1.0;
        this.visible = true;
        if(!views.containsKey(player)) {
            NMSHacks.FakeWither view = new NMSHacks.FakeWither(player.getWorld(), title.toLegacyText());
            views.put(player, view);
            int task = Bukkit.getScheduler().scheduleSyncRepeatingTask(CompendiumPlugin.getInstance(), () -> {
                if(isVisible()) {
                    view.teleport(player, witherLocation(player));
                }
            }, 0, 1);
            tasks.put(player, task);
            if(isVisible()) {
                view.spawn(player, witherLocation(player));
            }
        }
    }

    public void destroy() {
        tasks.forEach((player, id) -> Bukkit.getScheduler().cancelTask(id));
        views.forEach((player, wither) -> wither.destroy(player));
    }

    public BaseComponent[] getName() {
        BaseComponent[] bc = new BaseComponent[] { title };
        return bc;
    }

    
    public BarColor getColor() {
        return BarColor.PURPLE;
    }

    public void setName(BaseComponent title) {
        this.title = title;
        views.forEach((player, wither) -> wither.name(player, title.toLegacyText(), isVisible()));
    }

    public void setName(BaseComponent[] name) {
        BaseComponent bc = new TextComponent();
        for (BaseComponent baseComponent : name) {
            bc.addExtra(" ");
            bc.addExtra(bc);
        }
        this.title = bc;
        views.forEach((player, wither) -> wither.name(player, title.toLegacyText(), isVisible()));
    }

    public double getPercent() {
        return progress;
    }

    public void setPercent(double progress) {
        this.progress = progress;
        views.forEach((player, wither) -> wither.health(player, progress, isVisible()));
    }

    public boolean isVisible() {
        return visible;
    }

    // Other

    public List<Player> getPlayers() {
        return ImmutableList.copyOf(views.keySet());
    }

    
    private void removePlayer(Player player) {
        int task = tasks.remove(player);
        if(task != -1) {
            Bukkit.getScheduler().cancelTask(task);
        }
        NMSHacks.FakeWither view = views.remove(player);
        if(view != null) {
            view.destroy(player);
        }
    }

    
    private void removeAll() {
        ImmutableSet.copyOf(views.keySet()).forEach(this::removePlayer);
        views.clear();
    }

    
    public void setVisible(boolean visible) {
        boolean previous = isVisible();
        this.visible = visible;
        if(previous && !visible) {
            views.forEach((player, wither) -> wither.destroy(player));
        } else if(!previous && visible) {
            views.forEach((player, wither) -> wither.spawn(player, witherLocation(player)));
        }
    }

    
    public void update(BaseComponent title, double progress, BarColor color, BarStyle style, Set<BarFlag> flags) {
        this.title = title;
        this.progress = progress;
        views.forEach((player, wither) -> wither.update(player, title.toLegacyText(), progress, isVisible()));
    }

    private Location witherLocation(Player player) {
        final Location original = player.getEyeLocation();
        Location eye = new Location(player.getWorld(), original.getX(), original.getY(), original.getZ(), original.getYaw(), original.getPitch());
        eye.setPitch(eye.getPitch() - 20);
        return player.getEyeLocation().add(eye.getDirection().multiply(32));
    }

}
