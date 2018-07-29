package net.avicus.compendium.sound;

import org.apache.commons.lang3.text.WordUtils;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public enum SoundType {

  NONE(null),

  // Generic
  DING(Sound.ENTITY_ARROW_HIT),
  LEVEL_UP(Sound.ENTITY_PLAYER_LEVELUP),
  DRINK(Sound.ENTITY_GENERIC_DRINK),
  EAT(Sound.ENTITY_GENERIC_EAT),
  BURP(Sound.ENTITY_PLAYER_BURP),
  CLICK(Sound.UI_BUTTON_CLICK),
  HIT(Sound.ENTITY_PLAYER_HURT),

  // Notes
  SNARE(Sound.BLOCK_NOTE_SNARE),
  BASS(Sound.BLOCK_NOTE_BASS),
  PIANO(Sound.BLOCK_NOTE_XYLOPHONE),
  PLING(Sound.BLOCK_NOTE_PLING),

  // Entities
  FIREWORK(Sound.ENTITY_FIREWORK_BLAST),
  LAUNCH(Sound.ENTITY_FIREWORK_LAUNCH),
  PLOP(Sound.ENTITY_ITEM_PICKUP),
  EXPLOSION(Sound.ENTITY_GENERIC_EXPLODE),
  POP(Sound.BLOCK_LAVA_POP),

  // Blocks
  ANVIL(Sound.BLOCK_ANVIL_LAND),
  DOOR_HIT(Sound.ENTITY_ZOMBIE_ATTACK_DOOR_WOOD),
  DOOR_BREAK(Sound.ENTITY_ZOMBIE_BREAK_DOOR_WOOD),

  // Mobs
  MEOW(Sound.ENTITY_CAT_PURREOW),
  ENDERDRAGON(Sound.ENTITY_ENDERDRAGON_GROWL),
  GOLEM_DEATH(Sound.ENTITY_IRONGOLEM_DEATH),
  BARK(Sound.ENTITY_WOLF_AMBIENT),
  ZOMBIE_MOAN(Sound.ENTITY_ZOMBIE_AMBIENT),
  BLAZE_BREATH(Sound.ENTITY_BLAZE_AMBIENT),
  BLAZE_HIT(Sound.ENTITY_BLAZE_HURT),
  BLAZE_DEATH(Sound.ENTITY_BLAZE_DEATH),
  TELEPORT(Sound.ENTITY_ENDERMEN_TELEPORT),
  GHAST_DEATH(Sound.ENTITY_GHAST_DEATH),
  MOAN(Sound.ENTITY_GHAST_AMBIENT),
  SPIDER(Sound.ENTITY_SPIDER_DEATH),
  HMMM(Sound.ENTITY_VILLAGER_AMBIENT),
  WITHER_SPAWN(Sound.ENTITY_WITHER_SPAWN),
  WITHER_SHOOT(Sound.ENTITY_WITHER_SHOOT),
  WITHER_DEATH(Sound.ENTITY_WITHER_DEATH),
  REMEDY(Sound.ENTITY_ZOMBIE_VILLAGER_CURE),;

  private final Sound sound;

  SoundType(Sound sound) {
    this.sound = sound;
  }

  public String prettyName() {
    return WordUtils.capitalize(name().replaceAll("_", " ").toLowerCase());
  }

  public void play(Player player, float pitch) {
    if (this != NONE) {
      player.playSound(player.getLocation(), this.sound, 1.0F, pitch);
    }
  }
}
