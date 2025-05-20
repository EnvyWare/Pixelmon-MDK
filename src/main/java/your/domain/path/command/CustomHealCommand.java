package your.domain.path.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;

import java.awt.*;

public class CustomHealCommand {

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
        dispatcher.register(LiteralArgumentBuilder.<CommandSourceStack>literal("customheal")
                .executes(context -> {
                    context.getSource().sendFailure(Component.literal("Invalid command"));
                    return 0;
                })
                .then(Commands.argument("players", EntityArgument.players())
                        .executes(context -> {
                            var targets = EntityArgument.getPlayers(context, "players");

                            for (var target : targets) {
                                target.getPartyNow().heal();
                                target.sendSystemMessage(Component.literal("Your party has been fully healed!").withColor(Color.GREEN.getRGB()));
                            }

                            context.getSource().sendSuccess(() -> Component.literal("Healed " + targets.size() + " players"), true);

                            return targets.size();
                        })
                ));
    }
}
