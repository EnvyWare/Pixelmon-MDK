package your.domain.path.listener;

import com.pixelmonmod.pixelmon.entities.npcs.NPC;
import net.minecraft.network.chat.Component;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import your.domain.path.ModFile;

@EventBusSubscriber(modid = ModFile.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class NPCSpawnExampleListener {

    @SubscribeEvent
    public static void onEntityJoinWorld(EntityJoinLevelEvent event) {
        if (!(event.getEntity() instanceof NPC npc)) {
            return;
        }

        npc.setCustomName(Component.literal("Custom Name"));
    }

}
