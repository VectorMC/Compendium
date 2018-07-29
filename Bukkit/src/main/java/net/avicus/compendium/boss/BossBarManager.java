package net.avicus.compendium.boss;

import javax.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import net.avicus.compendium.utils.PlayerConnectionVersion;
import org.bukkit.entity.Player;

/**
 * A boss bar manager.
 */
@RequiredArgsConstructor
public class BossBarManager {

  /**
   * Create a boss bar.
   *
   * @param player the viewer of the boss bar
   * @return the boss bar
   */
  @Nonnull
  @SuppressWarnings("unchecked")
  public BossBar create(@Nonnull final Player player) {
    if(PlayerConnectionVersion.getProtocolVersion(player) < 107) {
      return new OldBossBar(player);
    } else {
      return new NewBossBar(player);
    }
  }
}
