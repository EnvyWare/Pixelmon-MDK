package your.domain.path.listener;

import com.pixelmonmod.pixelmon.api.events.EggHatchEvent;
import net.minecraft.util.Util;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class PixelmonEggHatchExampleListener {

    @SubscribeEvent
    public void onEggHatch(EggHatchEvent.Post event) {
        event.getPlayer().sendMessage(new StringTextComponent("Hello, your egg just hatched!"), Util.NIL_UUID);
    }

}
