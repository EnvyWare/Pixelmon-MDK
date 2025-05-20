package your.domain.path.listener;

import com.pixelmonmod.pixelmon.api.events.spawning.SpawnEvent;
import com.pixelmonmod.pixelmon.api.spawning.archetypes.entities.pokemon.SpawnActionPokemon;
import net.neoforged.bus.api.SubscribeEvent;

public class PokemonSpawnExampleListener {

    @SubscribeEvent
    public void onEntitySpawn(SpawnEvent event) {
        var action = event.action;

        if (!(action instanceof SpawnActionPokemon pokemonAction)) {
            return;
        }

        var pixelmon = pokemonAction.getOrCreateEntity();
        var pokemon = pokemonAction.pokemon;

        //TODO: logic goes here
    }

}
