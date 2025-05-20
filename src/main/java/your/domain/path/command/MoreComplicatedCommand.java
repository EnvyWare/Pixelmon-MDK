package your.domain.path.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.pixelmonmod.api.pokemon.PokemonSpecificationProxy;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.coordinates.BlockPosArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;

public class MoreComplicatedCommand {

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
        dispatcher.register(LiteralArgumentBuilder.<CommandSourceStack>literal("complicated")
                .executes(context -> {
                    context.getSource().sendFailure(Component.literal("Invalid command"));
                    return 0;
                })
                .then(Commands.argument("pos", BlockPosArgument.blockPos())
                        .executes(context -> {
                            context.getSource().sendFailure(Component.literal("Invalid command"));
                            return 0;
                        }).then(
                                Commands.argument("spec", StringArgumentType.greedyString()).executes(context -> {
                                    var spec = PokemonSpecificationProxy.create(context.getArgument("spec", String.class));

                                    if (!spec.wasSuccess()) {
                                        context.getSource().sendFailure(Component.literal("Invalid pokemon specification " + spec.getError()));
                                        return 0;
                                    }

                                    var pos = context.getArgument("pos", BlockPos.class);
                                    var pixelmonEntity = spec.get().create(context.getSource().getLevel());

                                    pixelmonEntity.setPos(pos.getX(), pos.getY(), pos.getZ());
                                    context.getSource().getLevel().addFreshEntity(pixelmonEntity);

                                    context.getSource().sendSuccess(() -> Component.literal(context.getSource().getTextName() + " spawned a " + spec.get()), true);
                                    return 1;
                                })
                        )
                ));
    }
}
