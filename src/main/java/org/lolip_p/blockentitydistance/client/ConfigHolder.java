package org.lolip_p.blockentitydistance.client;

import com.google.gson.*;
import net.fabricmc.loader.api.FabricLoader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class ConfigHolder {
    private static final Path CONFIG_PATH = FabricLoader.getInstance().getConfigDir().resolve("BlockEntityRenderDistanceConfig.json");
    public static final Map<String, Double> DISTANCE_CONFIG = new HashMap<>();

    public static boolean isEnabled = true;
    public static boolean UseGlobalValue = false;
    public static int GlobalValue = 40;
    public static int CheckIntervalTicks = 20; // 1.0 сек

    static {
        DISTANCE_CONFIG.put("minecraft:chest", 48.0);
        DISTANCE_CONFIG.put("minecraft:sign", 32.0);
        DISTANCE_CONFIG.put("minecraft:hanging_sign", 32.0);
        DISTANCE_CONFIG.put("minecraft:banner", 32.0);
        DISTANCE_CONFIG.put("minecraft:trapped_chest", 48.0);
        DISTANCE_CONFIG.put("minecraft:shulker_box", 48.0);
        DISTANCE_CONFIG.put("minecraft:campfire", 24.0);
        DISTANCE_CONFIG.put("minecraft:lectern", 24.0);
        DISTANCE_CONFIG.put("minecraft:beacon", 64.0);
        DISTANCE_CONFIG.put("minecraft:mob_spawner", 32.0);
        DISTANCE_CONFIG.put("minecraft:piston", 24.0);
        DISTANCE_CONFIG.put("minecraft:enchanting_table", 24.0);
        DISTANCE_CONFIG.put("minecraft:end_portal", 32.0);
        DISTANCE_CONFIG.put("minecraft:ender_chest", 48.0);
        DISTANCE_CONFIG.put("minecraft:skull", 24.0);
        DISTANCE_CONFIG.put("minecraft:end_gateway", 32.0);
        DISTANCE_CONFIG.put("minecraft:decorated_pot", 24.0);
        DISTANCE_CONFIG.put("minecraft:bed", 32.0);
        DISTANCE_CONFIG.put("minecraft:conduit", 24.0);
        DISTANCE_CONFIG.put("minecraft:bell", 32.0);

        loadConfig();
    }

    public static void loadConfig() {
        if (!Files.exists(CONFIG_PATH)) {
            saveConfig();
            return;
        }

        try {
            String json = Files.readString(CONFIG_PATH);
            JsonObject config = JsonParser.parseString(json).getAsJsonObject();

            if (config.has("isEnabled")) {
                isEnabled = config.get("isEnabled").getAsBoolean();
            }
            if (config.has("UseGlobalValue")) {
                UseGlobalValue = config.get("UseGlobalValue").getAsBoolean();
            }
            if (config.has("GlobalValue")) {
                GlobalValue = config.get("GlobalValue").getAsInt();
            }
            if (config.has("CheckIntervalTicks")) {
                CheckIntervalTicks = config.get("CheckIntervalTicks").getAsInt();
            }

            /*
            for (Map.Entry<String, JsonElement> entry : config.entrySet()) {
                String key = entry.getKey();
                JsonElement valueElement = entry.getValue();

                if (key.equals("UseGlobalValue") || key.equals("GlobalValue") || key.equals("CheckIntervalTicks")) {
                    continue; // пропустить служебные поля
                }

                if (valueElement.isJsonPrimitive() && valueElement.getAsJsonPrimitive().isNumber()) {
                    double value = valueElement.getAsDouble();
                    DISTANCE_CONFIG.put(key, value);
                }
            }*/

            if (config.has("distances")) {
                JsonObject distances = config.getAsJsonObject("distances");
                for (Map.Entry<String, JsonElement> entry : distances.entrySet()) {
                    if (entry.getValue().isJsonPrimitive()) {
                        double value = entry.getValue().getAsDouble();
                        DISTANCE_CONFIG.put(entry.getKey(), value);
                    }
                }
            }
        } catch (IOException | JsonParseException e) {
            System.err.println("Failed to load config: " + e.getMessage());
            saveConfig();
        }
    }

    public static void saveConfig() {
        try {
            JsonObject config = new JsonObject();

            config.addProperty("isEnabled", isEnabled);
            config.addProperty("UseGlobalValue", UseGlobalValue);
            config.addProperty("GlobalValue", GlobalValue);
            config.addProperty("CheckIntervalTicks", CheckIntervalTicks);

            /*
            DISTANCE_CONFIG.forEach(config::addProperty);
            Files.writeString(CONFIG_PATH, new GsonBuilder().setPrettyPrinting().create().toJson(config));
             */

            JsonObject distances = new JsonObject();
            DISTANCE_CONFIG.forEach(distances::addProperty);
            config.add("distances", distances);
            Files.writeString(CONFIG_PATH, new GsonBuilder().setPrettyPrinting().create().toJson(config));
        } catch (IOException e) {
            System.err.println("Failed to save config: " + e.getMessage());
        }
    }
}
