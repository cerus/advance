package dev.cerus.advance.api;

import org.bukkit.NamespacedKey;

public class BackgroundTexture {

    public static final BackgroundTexture STONE = new BackgroundTexture(NamespacedKey.minecraft("textures/gui/advancements/backgrounds/stone.png"));
    public static final BackgroundTexture NETHER = new BackgroundTexture(NamespacedKey.minecraft("textures/gui/advancements/backgrounds/nether.png"));
    public static final BackgroundTexture END = new BackgroundTexture(NamespacedKey.minecraft("textures/gui/advancements/backgrounds/end.png"));
    public static final BackgroundTexture ADVENTURE = new BackgroundTexture(NamespacedKey.minecraft("textures/gui/advancements/backgrounds/adventure.png"));
    public static final BackgroundTexture HUSBANDRY = new BackgroundTexture(NamespacedKey.minecraft("textures/gui/advancements/backgrounds/husbandry.png"));
    public static final BackgroundTexture NONE = null;

    private final NamespacedKey key;

    private BackgroundTexture(final NamespacedKey key) {
        this.key = key;
    }

    public static BackgroundTexture valueOf(final String str) {
        switch (str.toUpperCase()) {
            case "STORY":
                return STONE;
            case "NETHER":
                return NETHER;
            case "END":
                return END;
            case "ADVENTURE":
                return ADVENTURE;
            case "HUSBANDRY":
                return HUSBANDRY;
            case "NONE":
            default:
                return NONE;
        }
    }

    public NamespacedKey toKey() {
        return this.key;
    }

}
