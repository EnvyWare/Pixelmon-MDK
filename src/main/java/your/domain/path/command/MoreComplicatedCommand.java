package your.domain.path.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.pixelmonmod.api.pokemon.PokemonSpecification;
import com.pixelmonmod.api.pokemon.PokemonSpecificationProxy;
import com.pixelmonmod.pixelmon.entities.pixelmon.PixelmonEntity;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.BlockPosArgument;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;

public class MoreComplicatedCommand {

    /**
     *
     * Used for registering the command on the {@link net.minecraftforge.event.RegisterCommandsEvent}
     *
     * For more information about brigadier, how it works, what things mean, and lots of examples please read the
     * GitHub READ ME here <a href="https://github.com/Mojang/brigadier/blob/master/README.md">URL</a>
     *
     * @param dispatcher The dispatcher from the event
     */
    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(LiteralArgumentBuilder.<CommandSource>literal("complicated")
                .executes(context -> {
                    context.getSource().sendFailure(new StringTextComponent("Invalid command"));
                    return 0;
                })
                .then(Commands.argument("pos", BlockPosArgument.blockPos())
                        .executes(context -> {
                            context.getSource().sendFailure(new StringTextComponent("Invalid command"));
                            return 0;
                        }).then(
                                Commands.argument("spec", StringArgumentType.greedyString()).executes(context -> {
                                    PokemonSpecification spec = PokemonSpecificationProxy.create(context.getArgument("spec", String.class));
                                    BlockPos pos = context.getArgument("pos", BlockPos.class);

                                    PixelmonEntity pixelmonEntity = spec.create(context.getSource().getLevel());
                                    pixelmonEntity.setPos(pos.getX(), pos.getY(), pos.getZ());
                                    context.getSource().getLevel().addFreshEntity(pixelmonEntity);

                                    context.getSource().sendSuccess(new StringTextComponent(context.getSource().getTextName() + " spawned a " + spec), true);
                                    return 1;
                                })
                        )
                ));
    }
}
