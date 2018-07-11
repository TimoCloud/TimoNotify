package cloud.timo.TimoNotify;

import cloud.timo.TimoCloud.api.TimoCloudAPI;
import cloud.timo.TimoNotify.listeners.ServerRegisterListener;
import cloud.timo.TimoNotify.listeners.ServerUnregisterListener;
import cloud.timo.TimoNotify.managers.FileManager;
import net.md_5.bungee.api.plugin.Plugin;

public class TimoNotify extends Plugin {
    private static TimoNotify instance;
    private FileManager fileManager;

    @Override
    public void onEnable() {
        registerListener();
        makeInstances();
        this.getLogger().info("TimoNotify has been enabled.");
    }

    private void makeInstances() {
        setInstance(this);
        setFileManager(new FileManager());
    }

    @Override
    public void onDisable() {
        this.getLogger().info("TimoNotify has been disabled.");
    }

    private void registerListener() {
        new ServerRegisterListener();
        new ServerUnregisterListener();
    }

    public FileManager getFileManager() {
        return fileManager;
    }

    public void setFileManager(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    public static TimoNotify getInstance() {
        return instance;
    }

    public static void setInstance(TimoNotify instance) {
        TimoNotify.instance = instance;
    }
}
