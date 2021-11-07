package dev.cerus.advance.api.v16r3;

import dev.cerus.advance.api.AdvanceApi;
import dev.cerus.advance.api.Advancement;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.chat.ComponentSerializer;
import net.minecraft.server.v1_16_R3.AdvancementDisplay;
import net.minecraft.server.v1_16_R3.AdvancementFrameType;
import net.minecraft.server.v1_16_R3.AdvancementProgress;
import net.minecraft.server.v1_16_R3.Criterion;
import net.minecraft.server.v1_16_R3.CriterionProgress;
import net.minecraft.server.v1_16_R3.IChatBaseComponent;
import net.minecraft.server.v1_16_R3.MinecraftKey;
import net.minecraft.server.v1_16_R3.Packet;
import net.minecraft.server.v1_16_R3.PacketPlayOutAdvancements;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.craftbukkit.v1_16_R3.util.CraftChatMessage;
import org.bukkit.entity.Player;

public class AdvanceApiImpl implements AdvanceApi {

    @Override
    public void showAdvancement(final Advancement advancement, final Player... players) {
        final Object showPacket = this.makePacket(advancement);
        final Object clearPacket = this.makeClearPacket(advancement);
        for (final Player player : players) {
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket((Packet<?>) showPacket);
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket((Packet<?>) clearPacket);
        }
    }

    private Object makeClearPacket(final Advancement advancement) {
        return new PacketPlayOutAdvancements(true, List.of(), Set.of(new MinecraftKey(
                advancement.getKey().getNamespace(), advancement.getKey().getKey())
        ), Map.of());
    }

    private Object makePacket(final Advancement advancement) {
        // Construct progress
        final AdvancementProgress progress = new AdvancementProgress();

        // Inject criterion
        progress.a(Map.of(
                advancement.getKey().getKey(), new Criterion()
        ), new String[][] {new String[] {advancement.getKey().getKey()}});

        // Inject faked progress
        try {
            final Field field = progress.getClass().getDeclaredField("a");
            field.setAccessible(true);
            final Map<String, CriterionProgress> map = (Map<String, CriterionProgress>) field.get(progress);
            map.put(advancement.getKey().getKey(), CriterionProgress.a("1970-01-01 01:00:00 +0100"));
        } catch (final NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }

        // Construct packet
        return new PacketPlayOutAdvancements(false, List.of(this.makeAdvancement(advancement)), Set.of(), Map.of(
                new MinecraftKey(advancement.getKey().getNamespace(), advancement.getKey().getKey()), progress
        ));
    }

    private net.minecraft.server.v1_16_R3.Advancement makeAdvancement(final Advancement advancement) {
        return new net.minecraft.server.v1_16_R3.Advancement(
                new MinecraftKey(advancement.getKey().getNamespace(), advancement.getKey().getKey()),
                null,
                new AdvancementDisplay(
                        CraftItemStack.asNMSCopy(advancement.getIcon()),
                        this.makeComponent(advancement.getTitle()),
                        this.makeComponent(advancement.getTitle()),
                        advancement.getBackgroundTexture() == null
                                ? null
                                : MinecraftKey.a(advancement.getBackgroundTexture().toKey().getKey()),
                        AdvancementFrameType.valueOf(advancement.getFrameType().name()),
                        advancement.isShowToast(),
                        false, // announce to chat
                        advancement.isHidden()
                ),
                null,
                Map.of(
                        advancement.getKey().getKey(), new Criterion()
                ),
                new String[][] {
                        new String[] {advancement.getKey().getKey()}
                }
        );
    }

    private IChatBaseComponent makeComponent(final BaseComponent... components) {
        final String jsonStr = ComponentSerializer.toString(components);
        return CraftChatMessage.fromJSON(jsonStr);
    }

}
