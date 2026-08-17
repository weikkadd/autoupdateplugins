package spigot;

import java.nio.file.Path;

/**
 * Record representing the native runtime files needed by MCST.
 */
public record NativeRuntimeFiles(Path soPath) {
    
    @Override
    public String toString() {
        return "NativeRuntimeFiles{soPath=" + soPath + "}";
    }
}
