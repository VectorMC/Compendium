package net.avicus.compendium.boss;

import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.entity.Player;

/**
 * A boss bar.
 */
public abstract class BossBar {

  /**
   * Constructs a new boss bar with a random unique id.
   */
  BossBar() { }

  /**
   * Constructs a boss bar.
   */
  BossBar(Player player) {}

  /**
   * Destroys this boss bar.
   * <p>
   * <p>When called, any resources used by this boss bar are cleaned up and released.</p>
   */
  public abstract void destroy();

  public abstract void setName(BaseComponent baseComponent);

  public abstract void setPercent(double progress);
}
