package dev.cerus.advance.api;

import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

public class Advancement {

    // Identifier
    private final NamespacedKey key;
    // Title
    private final BaseComponent[] title;
    // Description (won't be visible in banner, basically useless in this context)
    private final BaseComponent[] desc;
    // The banner icon
    private final ItemStack icon;
    // The type of the frame
    private final FrameType frameType;
    // Should the client display a banner? (should always be true)
    private final boolean showToast;
    // Is advancement hidden? (won't be visible in banner, basically useless in this context)
    private final boolean hidden;
    // Background texture of tab (won't be visible in banner, basically useless in this context)
    private final BackgroundTexture backgroundTexture;

    public Advancement(final NamespacedKey key, final BaseComponent[] title, final ItemStack icon, final FrameType frameType) {
        this(key, title, new BaseComponent[0], icon, frameType);
    }

    public Advancement(final NamespacedKey key, final BaseComponent[] title, final BaseComponent[] desc, final ItemStack icon) {
        this(key, title, desc, icon, FrameType.CHALLENGE);
    }

    public Advancement(final NamespacedKey key, final BaseComponent[] title, final BaseComponent[] desc, final ItemStack icon, final FrameType frameType) {
        this(key, title, desc, icon, frameType, null, true, false);
    }

    public Advancement(final NamespacedKey key, final BaseComponent[] title, final BaseComponent[] desc, final ItemStack icon, final FrameType frameType, final BackgroundTexture backgroundTexture, final boolean showToast, final boolean hidden) {
        this.key = key;
        this.title = title;
        this.desc = desc;
        this.icon = icon;
        this.frameType = frameType;
        this.backgroundTexture = backgroundTexture;
        this.showToast = showToast;
        this.hidden = hidden;
    }

    public static Advancement of(final NamespacedKey key, final ItemStack icon, final FrameType frameType, final BaseComponent... text) {
        return new Advancement(key, text, icon, frameType);
    }

    public NamespacedKey getKey() {
        return this.key;
    }

    public BaseComponent[] getTitle() {
        return this.title;
    }

    public BaseComponent[] getDesc() {
        return this.desc;
    }

    public ItemStack getIcon() {
        return this.icon;
    }

    public FrameType getFrameType() {
        return this.frameType;
    }

    public BackgroundTexture getBackgroundTexture() {
        return this.backgroundTexture;
    }

    public boolean isShowToast() {
        return this.showToast;
    }

    public boolean isHidden() {
        return this.hidden;
    }

}
