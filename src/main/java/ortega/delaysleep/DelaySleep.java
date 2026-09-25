package ortega.delaysleep;

import net.fabricmc.api.ModInitializer;

import net.minecraft.resources.Identifier;
import net.minecraft.world.level.Level;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DelaySleep implements ModInitializer {
    public static final String MOD_ID = "delay-sleep";

    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    private static DelaySleepConfig config;

    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        config = DelaySleepConfig.load();
        LOGGER.info(
                "Sleep allowed from tick {} (clear, vanilla {}) and tick {} (rain/thunder, vanilla {})",
                config.minTickClear,
                DelaySleepConfig.VANILLA_MIN_TICK_CLEAR,
                config.minTickRain,
                DelaySleepConfig.VANILLA_MIN_TICK_RAIN);
    }

    public static DelaySleepConfig config() {
        if (config == null) {
            config = DelaySleepConfig.load();
        }

        return config;
    }

    public static boolean isSleepTimeReached(Level level) {
        if (level.dimensionType().hasFixedTime()) {
            return true;
        }

        long timeOfDay = level.getOverworldClockTime() % DelaySleepConfig.TICKS_PER_DAY;
        return timeOfDay >= config().minTickFor(level.isRaining() || level.isThundering());
    }

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }
}
