package net.avicus.compendium.menu.inventory;

import java.util.Optional;
import org.bukkit.event.inventory.ClickType;

/**
 * Handles when a player clicks inside an opened inventory menu.
 */
public interface InventoryHandler {

  /**
   * Called when a player clicks inside an opened inventory menu.
   *
   * @param menu The inventory menu.
   * @param index The inventory index of the click.
   * @param item The menu item clicked.
   */
  void onClick(InventoryMenu menu, int index, Optional<InventoryMenuItem> item,
      ClickType clickType);
}
