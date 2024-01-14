package your.domain.path;

import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.api.config.api.yaml.YamlConfigFactory;
import com.pixelmonmod.tcg.TCG;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.event.server.ServerStoppedEvent;
import net.minecraftforge.event.server.ServerStoppingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import your.domain.path.command.ExampleCommand;
import your.domain.path.command.MoreComplicatedCommand;
import your.domain.path.config.ExampleConfig;
import your.domain.path.listener.PixelmonEggHatchExampleListener;
import your.domain.path.listener.PokemonSpawnExampleListener;
import your.domain.path.listener.tcg.PackOpeningListener;

import java.io.IOException;

@Mod(ModFile.MOD_ID)
@Mod.EventBusSubscriber(modid = ModFile.MOD_ID)
public class ModFile {

    public static final String MOD_ID = "examplemod";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    private static ModFile instance;

    private ExampleConfig config;

    public ModFile() {
        instance = this;

        reloadConfig();

        MinecraftForge.EVENT_BUS.register(this);

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(ModFile::onModLoad);
    }

    public static void onModLoad(FMLCommonSetupEvent event) {
        // Here is how you register a listener for Pixelmon events
        // Pixelmon has its own event bus for its events, as does TCG
        // So any event listener for those mods need to be registered to those specific event buses
        Pixelmon.EVENT_BUS.register(new PixelmonEggHatchExampleListener());
        Pixelmon.EVENT_BUS.register(new PokemonSpawnExampleListener());
        TCG.EVENT_BUS.register(new PackOpeningListener());
    }

    @SubscribeEvent
    public static void onServerStarting(ServerStartingEvent event) {
        // Logic for when the server is starting here
    }

    public void reloadConfig() {
        try {
            this.config = YamlConfigFactory.getInstance(ExampleConfig.class);
        } catch (IOException e) {
            LOGGER.error("Failed to load config", e);
        }
    }

    @SubscribeEvent
    public static void onServerStarted(ServerStartedEvent event) {
        // Logic for once the server has started here
    }

    @SubscribeEvent
    public static void onCommandRegister(RegisterCommandsEvent event) {
        //Register command logic here
        // Commands don't have to be registered here
        // However, not registering them here can lead to some hybrids/server software not recognising the commands
        ExampleCommand.register(event.getDispatcher());
        MoreComplicatedCommand.register(event.getDispatcher());
    }

    @SubscribeEvent
    public static void onServerStopping(ServerStoppingEvent event) {
        // Logic for when the server is stopping
    }

    @SubscribeEvent
    public static void onServerStopped(ServerStoppedEvent event) {
        // Logic for when the server is stopped
    }

    public static ModFile getInstance() {
        return instance;
    }

    public static Logger getLogger() {
        return LOGGER;
    }

    public static ExampleConfig getConfig() {
        return instance.config;
    }
}
