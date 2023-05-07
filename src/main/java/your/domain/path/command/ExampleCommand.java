package your.domain.path.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.command.CommandSource;
import net.minecraft.util.text.StringTextComponent;
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
    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(LiteralArgumentBuilder.<CommandSource>literal("example").executes(context -> {
            CommandSource source = context.getSource();
            source.sendSuccess(new StringTextComponent(ModFile.getConfig().getExampleField()), false); // Sends a message to the sender - if true it will broadcast to all ops (like how /op does)
            return 1;
        }));
    }
}
