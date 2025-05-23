package your.domain.path.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.pixelmonmod.api.pokemon.PokemonSpecificationProxy;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

public class CustomSpawnCommand {

    /**
     *
     * Used for registering the command on the {@link net.neoforged.neoforge.event.RegisterCommandsEvent}
     *
     * For more information about brigadier, how it works, what things mean, and lots of examples please read the
     * GitHub READ ME here <a href="https://github.com/Mojang/brigadier/blob/master/README.md">URL</a>
     *
     * @param dispatcher The dispatcher from the event
     */
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(LiteralArgumentBuilder.<CommandSourceStack>literal("customspawn")
                .executes(context -> {
                    context.getSource().sendFailure(Component.literal("Invalid command"));
                    return 0;
                })
                .then(Commands.argument("spec", StringArgumentType.greedyString())
                        .executes(context -> {
                            var unparsedSpec = StringArgumentType.getString(context, "spec");
                            var parseAttempt = PokemonSpecificationProxy.create(unparsedSpec);

                            if (!parseAttempt.wasSuccess()) {
                                if (parseAttempt.wasError()) {
                                    context.getSource().sendFailure(Component.literal("Error parsing spec: " + parseAttempt.getError()));
                                    return 0;
                                }

                                context.getSource().sendFailure(Component.literal("Invalid spec provided! " + parseAttempt.getException().getLocalizedMessage()));
                                return 0;
                            }

                            var spec = parseAttempt.get();
                            var entity = spec.create(context.getSource().getLevel());
                            entity.setPos(context.getSource().getPosition());

                            context.getSource().getLevel().addFreshEntity(entity);
                            context.getSource().sendSuccess(() -> Component.literal("Spawned " + entity.getName()), true);

                            return 1;
                        })
                ));
    }
}
