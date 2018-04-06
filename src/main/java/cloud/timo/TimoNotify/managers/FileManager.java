package cloud.timo.TimoNotify.managers;

import cloud.timo.TimoNotify.TimoNotify;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.nio.file.Files;

public class FileManager {
    private String pluginsDirectory = "plugins/TimoNotify/";
    private String configsDirectory = pluginsDirectory + "bungeecord/";
    private File configFile;
    private Configuration config;
    private File messagesFile;
    private Configuration messages;
    public FileManager() {
        load();
    }

    public void load() {
        try {
            File configs = new File(configsDirectory);
            configs.mkdirs();

            //Load configFile
            configFile = new File(configsDirectory, "config.yml");
            if (!configFile.exists()) {
                configFile.createNewFile();
            }
            config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(configFile);
            configFile.delete();
            Files.copy(this.getClass().getResourceAsStream("/bungeecord/config.yml"), configFile.toPath());
            Configuration configNew = ConfigurationProvider.getProvider(YamlConfiguration.class).load(configFile);
            for (String key : config.getKeys()) {
                configNew.set(key, config.get(key));
            }
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(configNew, configFile);
            config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(configFile);

            //Load messagesFile
            messagesFile = new File(configsDirectory, "config.yml");
            if (!messagesFile.exists()) {
                messagesFile.createNewFile();
            }
            messages = ConfigurationProvider.getProvider(YamlConfiguration.class).load(messagesFile);
            messagesFile.delete();
            Files.copy(this.getClass().getResourceAsStream("/bungeecord/messages.yml"), messagesFile.toPath());
            Configuration messagesNew = ConfigurationProvider.getProvider(YamlConfiguration.class).load(messagesFile);
            for (String key : messages.getKeys()) {
                messagesNew.set(key, messages.get(key));
            }
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(messagesNew, messagesFile);
            messages = ConfigurationProvider.getProvider(YamlConfiguration.class).load(messagesFile);
        } catch (Exception e) {
            TimoNotify.getInstance().log("Exception while initializing files:");
            e.printStackTrace();
        }
    }

    public File getConfigFile() {
        return configFile;
    }

    public Configuration getConfig() {
        return config;
    }

    public File getMessagesFile() {
        return messagesFile;
    }

    public Configuration getMessages() {
        return messages;
    }
}