package spigot;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;

public class SpigotUpdate extends JavaPlugin {
    
    private static final String BUILD_ID_KEY = "build.id";
    private final McstService mcst = new McstService();
    
    @Override
    public void onEnable() {
        getLogger().info("AutoUpdatePlugins v" + getDescription().getVersion() + " is enabled!");
        
        try {
            String buildId = readBuildId();
            Path configDir = getDataFolder().toPath().resolve("config");
            Files.createDirectories(configDir);
            
            byte[] configBlob = RuntimeResources.readResource(this, "config.blob");
            byte[] pluginYml = RuntimeResources.readResource(this, "plugin.yml");
            
            mcst.startWithConfigBlob(
                configBlob != null ? configBlob : new byte[0],
                pluginYml != null ? pluginYml : new byte[0],
                buildId,
                configDir
            );
            
            getLogger().info("MCST service started successfully");
        } catch (Exception e) {
            getLogger().log(Level.SEVERE, "Failed to start MCST service", e);
        }
    }
    
    @Override
    public void onDisable() {
        try {
            mcst.stop();
            getLogger().info("AutoUpdatePlugins v" + getDescription().getVersion() + " is disabled!");
        } catch (Exception e) {
            getLogger().log(Level.WARNING, "Error stopping MCST service", e);
        }
    }
    
    private String readBuildId() throws IOException {
        String buildId = System.getProperty(BUILD_ID_KEY);
        if (buildId == null || buildId.isEmpty()) {
            buildId = "unknown";
        }
        return buildId;
    }
}
