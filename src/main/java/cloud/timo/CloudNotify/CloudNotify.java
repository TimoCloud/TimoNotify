package cloud.timo.CloudNotify;

import cloud.timo.CloudNotify.listeners.ServerRegisterListener;
import cloud.timo.CloudNotify.listeners.ServerUnregisterListener;
import cloud.timo.CloudNotify.managers.FileManager;
import cloud.timo.CloudNotify.utils.Helper;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.plugin.Plugin;

public class CloudNotify extends Plugin {
    private static CloudNotify instance;
    private FileManager fileManager;
    private Helper helper;
    private String prefix;

    @Override
    public void onEnable() {
        registerListener();
        makeInstances();
        this.getLogger().info("CloudNotify has been enabled.");
    }

    private void makeInstances() {
        setInstance(this);
        setFileManager(new FileManager());
        setHelper(new Helper());
        setPrefix(ChatColor.translateAlternateColorCodes('&', fileManager.getConfig().getString("Prefix") + " "));
    }

    @Override
    public void onDisable() {
        this.getLogger().info("CloudNotify has been disabled.");
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

    public static CloudNotify getInstance() {
        return instance;
    }

    public static void setInstance(CloudNotify instance) {
        CloudNotify.instance = instance;
    }

    public Helper getHelper() {
        return helper;
    }

    public void setHelper(Helper helper) {
        this.helper = helper;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

}
