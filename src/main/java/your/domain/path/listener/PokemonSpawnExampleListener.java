package your.domain.path.listener;

import com.pixelmonmod.pixelmon.api.events.spawning.SpawnEvent;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.spawning.SpawnAction;
import com.pixelmonmod.pixelmon.api.spawning.archetypes.entities.pokemon.SpawnActionPokemon;
import com.pixelmonmod.pixelmon.entities.pixelmon.PixelmonEntity;
import net.minecraft.entity.Entity;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class PokemonSpawnExampleListener {

    @SubscribeEvent
    public void onEntitySpawn(SpawnEvent event) {
        SpawnAction<? extends Entity> action = event.action;

        if (!(action instanceof SpawnActionPokemon)) {
            return;
        }

        PixelmonEntity pixelmon = ((SpawnActionPokemon)action).getOrCreateEntity();
        Pokemon pokemon = ((SpawnActionPokemon)action).pokemon;

        //TODO: logic goes here
    }

}
