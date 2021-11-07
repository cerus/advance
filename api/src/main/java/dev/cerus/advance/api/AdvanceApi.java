package dev.cerus.advance.api;

import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface AdvanceApi {

    /**
     * Displays "Advancement Made!" advancement banner
     *
     * @param key     Advancement identifier
     * @param title   Text that will be displayed
     * @param icon    Itemstack icon
     * @param players Players that should see this notification
     */
    default void showTask(final NamespacedKey key, final BaseComponent[] title, final ItemStack icon, final Player... players) {
        this.showAdvancement(new Advancement(key, title, icon, FrameType.TASK), players);
    }

    /**
     * Displays "Challenge Complete!" advancement banner
     *
     * @param key     Advancement identifier
     * @param title   Text that will be displayed
     * @param icon    Itemstack icon
     * @param players Players that should see this notification
     */
    default void showChallenge(final NamespacedKey key, final BaseComponent[] title, final ItemStack icon, final Player... players) {
        this.showAdvancement(new Advancement(key, title, icon, FrameType.CHALLENGE), players);
    }

    /**
     * Displays "Goal Reached!" advancement banner
     *
     * @param key     Advancement identifier
     * @param title   Text that will be displayed
     * @param icon    Itemstack icon
     * @param players Players that should see this notification
     */
    default void showGoal(final NamespacedKey key, final BaseComponent[] title, final ItemStack icon, final Player... players) {
        this.showAdvancement(new Advancement(key, title, icon, FrameType.GOAL), players);
    }

    /**
     * Displays an advancement banner
     *
     * @param key     Advancement identifier
     * @param title   Text that will be displayed
     * @param icon    Itemstack icon
     * @param players Players that should see this notification
     */
    default void showAdvancement(final NamespacedKey key, final BaseComponent[] title, final ItemStack icon, final FrameType frameType, final Player... players) {
        this.showAdvancement(new Advancement(key, title, icon, frameType), players);
    }

    /**
     * Displays an advancement banner
     *
     * @param advancement The advancement to display
     * @param players     The receivers
     */
    void showAdvancement(Advancement advancement, Player... players);

}
