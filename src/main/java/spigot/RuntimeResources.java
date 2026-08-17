package spigot;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public final class RuntimeResources {
    
    public static final String CONFIG_BLOB = "config.blob";
    public static final String PLUGIN_YML = "plugin.yml";
    
    private RuntimeResources() {}
    
    public static byte[] readResource(JavaPlugin plugin, String resourceName) throws IOException {
        if (resourceName == null || resourceName.isEmpty()) {
            return new byte[0];
        }
        
        try (InputStream is = plugin.getResource(resourceName)) {
            if (is == null) {
                return new byte[0];
            }
            return is.readAllBytes();
        }
    }
    
    public static boolean resourceExists(JavaPlugin plugin, String resourceName) {
        return plugin.getResource(resourceName) != null;
    }
    
    public static String readResourceAsString(JavaPlugin plugin, String resourceName) throws IOException {
        byte[] bytes = readResource(plugin, resourceName);
        return new String(bytes, java.nio.charset.StandardCharsets.UTF_8);
    }
}
