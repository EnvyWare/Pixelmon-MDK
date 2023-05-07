package your.domain.path.listener;

import com.google.common.collect.Lists;
import com.pixelmonmod.pixelmon.api.battles.BattleAIMode;
import com.pixelmonmod.pixelmon.api.pokemon.boss.BossTierRegistry;
import com.pixelmonmod.pixelmon.entities.npcs.NPCChatting;
import com.pixelmonmod.pixelmon.entities.npcs.NPCEntity;
import com.pixelmonmod.pixelmon.entities.npcs.NPCTrainer;
import com.pixelmonmod.pixelmon.enums.EnumTrainerAI;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import your.domain.path.ModFile;

@Mod.EventBusSubscriber(modid = ModFile.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class NPCSpawnExampleListener {

    @SubscribeEvent
    public static void onEntityJoinWorld(EntityJoinWorldEvent event) {
        if (!(event.getEntity() instanceof NPCEntity)) {
            return;
        }

        NPCEntity npc = (NPCEntity) event.getEntity();

        npc.setName("New name!");
        npc.setAIMode(EnumTrainerAI.StandStill);

        if (npc instanceof NPCTrainer) {
            NPCTrainer trainer = (NPCTrainer) npc;

            trainer.setBattleAIMode(BattleAIMode.ADVANCED);
            trainer.setBossTier(BossTierRegistry.getRandomBossTier());
            trainer.setPokemonLevel(100);
        } else if (npc instanceof NPCChatting) {
            NPCChatting chatting = (NPCChatting) npc;

            chatting.setChat(Lists.newArrayList(
                    "Hello world",
                    "I am a chatting NPC!"
            ));
        }
    }

}
