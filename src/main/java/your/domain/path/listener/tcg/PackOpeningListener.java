package your.domain.path.listener.tcg;

import com.pixelmonmod.tcg.api.events.PackOpenEvent;
import net.minecraft.network.chat.Component;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class PackOpeningListener {

    @SubscribeEvent
    public void onTCGPackOpen(PackOpenEvent.Post event) {
        for (var card : event.getCards()) {
            event.getPlayer().sendSystemMessage(Component.literal("You got a " + card.getName() + "!"));
        }
    }
}
