package your.domain.path.listener;

import com.pixelmonmod.pixelmon.api.events.EggHatchEvent;
import net.minecraft.network.chat.Component;
import net.neoforged.bus.api.SubscribeEvent;

public class PixelmonEggHatchExampleListener {

    @SubscribeEvent
    public void onEggHatch(EggHatchEvent.Post event) {
        event.getPlayer().sendSystemMessage(Component.literal("Hello, your egg just hatched!"));
    }

}
