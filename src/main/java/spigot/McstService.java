package spigot;

import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class McstService {
    
    private static final Logger logger = Logger.getLogger(McstService.class.getName());
    
    private long handle;
    private McstLib lib;
    
    public McstService() {
        this.handle = 0;
        this.lib = null;
    }
    
    public synchronized void startWithConfigBlob(byte[] configBlob, byte[] pluginYml, 
                                                  String buildId, Path configDir) {
        try {
            if (this.lib == null) {
                NativeRuntimeFiles runtime = NativeLoader.loadMcstRuntime(null, buildId);
                this.lib = McstLib.load(runtime.soPath());
            }
            
            PointerByReference handlePtr = new PointerByReference();
            this.handle = this.lib.McstStartWithConfigBlob(
                configBlob, configBlob.length,
                pluginYml, pluginYml.length,
                buildId,
                handlePtr
            );
            
            if (this.handle == 0) {
                throw new RuntimeException("Failed to start MCST service");
            }
            
            logger.info("MCST service started with handle: " + this.handle);
            
            // Ensure config directory exists
            Files.createDirectories(configDir);
            
        } catch (IOException | InterruptedException e) {
            logger.log(Level.SEVERE, "Failed to initialize MCST service", e);
            throw new RuntimeException("MCST initialization failed", e);
        }
    }
    
    public synchronized void stop() {
        if (this.handle != 0 && this.lib != null) {
            try {
                this.lib.McstStop(this.handle);
                this.handle = 0;
                logger.info("MCST service stopped");
            } catch (Exception e) {
                logger.log(Level.WARNING, "Error stopping MCST service", e);
            }
        }
    }
    
    public long getHandle() {
        return this.handle;
    }
}
