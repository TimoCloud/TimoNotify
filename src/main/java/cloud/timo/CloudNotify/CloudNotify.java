package cloud.timo.CloudNotify;

import cloud.timo.CloudNotify.commands.CloudNotifyCommand;
import cloud.timo.CloudNotify.listeners.ServerRegisterListener;
import cloud.timo.CloudNotify.listeners.ServerUnregisterListener;
import cloud.timo.CloudNotify.managers.DatabaseManager;
import cloud.timo.CloudNotify.managers.FileManager;
import cloud.timo.CloudNotify.managers.PluginMessageManager;
import cloud.timo.CloudNotify.utils.Helper;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.plugin.Plugin;

public class CloudNotify extends Plugin {
    private static CloudNotify instance;
    private FileManager fileManager;
    private DatabaseManager databaseManager;
    private CloudNotifyCommand cloudNotifyCommand;
    private PluginMessageManager pluginMessageManager;
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
        setDatabaseManager(new DatabaseManager());
        setHelper(new Helper());
        setPluginMessageManager(new PluginMessageManager());
        setPrefix(ChatColor.translateAlternateColorCodes('&', fileManager.getConfig().getString("Prefix") + " "));
        setCloudNotifyCommand(new CloudNotifyCommand("cloudNotify", "cloudNotify.command.notify"));
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

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }

    public void setCloudNotifyCommand(CloudNotifyCommand cloudNotifyCommand) {
        this.cloudNotifyCommand = cloudNotifyCommand;
    }

    public void setDatabaseManager(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    public PluginMessageManager getPluginMessageManager() {
        return pluginMessageManager;
    }

    public void setPluginMessageManager(PluginMessageManager pluginMessageManager) {
        this.pluginMessageManager = pluginMessageManager;
    }
}
