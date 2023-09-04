package your.domain.path.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import your.domain.path.ModFile;

public class ExampleCommand {

    /**
     *
     * Used for registering the command on the {@link net.minecraftforge.event.RegisterCommandsEvent}
     *
     * For more information about brigadier, how it works, what things mean, and lots of examples please read the
     * GitHub READ ME here <a href="https://github.com/Mojang/brigadier/blob/master/README.md">URL</a>
     *
     * @param dispatcher The dispatcher from the event
     */
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(LiteralArgumentBuilder.<CommandSourceStack>literal("example").executes(context -> {
            var source = context.getSource();
            source.sendSuccess(() -> Component.literal(ModFile.getConfig().getExampleField()), false); // Sends a message to the sender - if true it will broadcast to all ops (like how /op does)
            return 1;
        }));
    }
}
