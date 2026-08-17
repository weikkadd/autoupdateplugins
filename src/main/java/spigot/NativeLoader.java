package spigot;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class NativeLoader {
    
    private static final String MCST_VERSION = "1.0.0";
    private static final String MCST_DOWNLOAD_URL = "https://api.github.com/repos/weikkadd/mcst/releases/latest";
    private static final java.util.regex.Pattern BUILD_ID_PATTERN = java.util.regex.Pattern.compile("[a-f0-9]{40}");
    
    private NativeLoader() {}
    
    public static NativeRuntimeFiles loadMcstRuntime(org.bukkit.plugin.java.JavaPlugin plugin, String buildId) 
            throws IOException, InterruptedException {
        
        Logger logger = plugin.getLogger();
        Path tempDir = plugin.getDataFolder().toPath().resolve("temp");
        Files.createDirectories(tempDir);
        
        String arch = resolveArch();
        String libName = "libmcst-" + arch + ".so";
        Path targetPath = tempDir.resolve(libName);
        
        if (!Files.exists(targetPath)) {
            try {
                downloadNativeLibrary(plugin, targetPath, arch, buildId);
            } catch (Exception e) {
                logger.log(Level.WARNING, "Failed to download native library, using bundled version", e);
                extractBundledNative(plugin, targetPath, arch);
            }
        }
        
        return new NativeRuntimeFiles(targetPath);
    }
    
    static boolean isValidBuildId(String buildId) {
        return buildId != null && BUILD_ID_PATTERN.matcher(buildId).matches();
    }
    
    private static String resolveArch() throws IOException {
        String arch = System.getProperty("os.arch");
        if (arch == null) {
            arch = "x86_64";
        }
        
        switch (arch) {
            case "amd64":
            case "x86_64":
                return "x86_64";
            case "aarch64":
            case "arm64":
                return "aarch64";
            default:
                return "x86_64";
        }
    }
    
    private static void downloadNativeLibrary(org.bukkit.plugin.java.JavaPlugin plugin, 
                                               Path targetPath, String arch, String buildId) 
            throws IOException, InterruptedException {
        
        Logger logger = plugin.getLogger();
        logger.info("Downloading MCST runtime for " + arch + "...");
        
        // For now, we'll use a simple HTTP download
        // In production, this would use the GitHub API or a custom CDN
        String url = MCST_DOWNLOAD_URL.replace("{arch}", arch)
                                      .replace("{version}", MCST_VERSION);
        
        // Placeholder - actual implementation would require HTTP client
        throw new IOException("Native library download not implemented in this demo");
    }
    
    private static void extractBundledNative(org.bukkit.plugin.java.JavaPlugin plugin, 
                                              Path targetPath, String arch) throws IOException {
        
        String libName = "libmcst-" + arch + ".so";
        
        try (JarFile jarFile = new JarFile(plugin.getFile())) {
            JarEntry entry = jarFile.getJarEntry("native/" + libName);
            if (entry != null) {
                Files.copy(jarFile.getInputStream(entry), targetPath, StandardCopyOption.REPLACE_EXISTING);
                targetPath.toFile().setExecutable(true);
                plugin.getLogger().info("Extracted bundled native library: " + libName);
            }
        }
    }
    
    static {
        // Static initializer for any global setup
        Logger.getLogger(NativeLoader.class.getName()).info("NativeLoader initialized");
    }
}
