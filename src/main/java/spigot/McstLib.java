package spigot;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;

import java.nio.file.Path;

public interface McstLib extends Library {
    
    McstLib INSTANCE = Native.load("mcst", McstLib.class);
    
    static McstLib load(Path path) {
        return Native.load(path.toAbsolutePath().toString(), McstLib.class);
    }
    
    long McstStartWithConfigBlob(byte[] configBlob, int configLen, 
                                  byte[] pluginYml, int pluginYmlLen,
                                  String buildId, PointerByReference handlePtr);
    
    void McstStop(long handle);
    
    void McstFreeCString(Pointer str);
}
