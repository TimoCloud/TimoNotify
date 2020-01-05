package cloud.timo.CloudNotify.utils;

import cloud.timo.CloudNotify.CloudNotify;
import cloud.timo.CloudNotify.managers.FileManager;
import cloud.timo.CloudNotify.managers.MessageManager;
import cloud.timo.TimoCloud.api.TimoCloudAPI;
import cloud.timo.TimoCloud.api.events.Event;
import cloud.timo.TimoCloud.api.events.EventType;
import cloud.timo.TimoCloud.api.messages.objects.PluginMessage;
import cloud.timo.TimoCloud.api.objects.PlayerObject;
import cloud.timo.TimoCloud.api.objects.ServerObject;
import com.google.gson.Gson;
import net.md_5.bungee.api.ProxyServer;

import java.util.Collection;

public class Helper {

    public void notify(PluginMessage pluginMessage) {
        FileManager fileManager = CloudNotify.getInstance().getFileManager();

        EventType eventType = EventType.valueOf(pluginMessage.getString("eventType"));
        assert eventType != null;


        String mainMessage = (eventType == EventType.SERVER_REGISTER) ? fileManager.getMessages().getString("serverRegisterMessage") : fileManager.getMessages().getString("serverUnregisterMessage");

        Collection<PlayerObject> onlinePlayersCollection = new Gson().fromJson(pluginMessage.getString("serverOnlinePlayers"), Collection.class);

        StringBuilder onlinePlayers = new StringBuilder();
        for (PlayerObject onlinePlayer : onlinePlayersCollection)
            onlinePlayers.append(onlinePlayer.getName()).append(", ");

        ProxyServer.getInstance().getPlayers().forEach(proxiedPlayer -> {
            if (proxiedPlayer.hasPermission("cloudnotify.notify") && CloudNotify.getInstance().getDatabaseManager().getNotifyState(proxiedPlayer.getUniqueId()))
                MessageManager.sendMessage(proxiedPlayer, mainMessage
                        .replace("{serverName}", pluginMessage.getString("serverName"))
                        .replace("{serverBase}", pluginMessage.getString("serverBase"))
                        .replace("{serverExtra}", pluginMessage.getString("serverExtra"))
                        .replace("{serverMap}", pluginMessage.getString("serverMap"))
                        .replace("{serverMotd}", pluginMessage.getString("serverMotd"))
                        .replace("{serverState}", pluginMessage.getString("serverState"))
                        .replace("{serverIpAddress}", pluginMessage.getString("serverIpAddress"))
                        .replace("{serverMaxPlayerCount}", pluginMessage.getString("serverMaxPlayerCount"))
                        .replace("{serverOnlinePlayerCount}", pluginMessage.getString("serverOnlinePlayerCount"))
                        .replace("{serverOnlinePlayers}", (onlinePlayers.length() == 0 ? fileManager.getMessages().getString("nobodyOnline") : onlinePlayers.toString().trim().substring(0, onlinePlayers.length())))
                        .replace("{serverPort}", pluginMessage.getString("serverPort"))
                        .replace("{serverSocketAddress}", pluginMessage.getString("serverSocketAddress")));
        });
    }

    public void initiatingNotification(Event event, ServerObject serverObject) {
        PluginMessage pluginMessage = new PluginMessage("CLOUDNOTIFY_NOTIFICATION");
        pluginMessage.set("eventType", event.getType());
        pluginMessage.set("serverName", serverObject.getName());
        pluginMessage.set("serverBase", serverObject.getBase().getName());
        pluginMessage.set("serverExtra", serverObject.getExtra());
        pluginMessage.set("serverMap", serverObject.getMap());
        pluginMessage.set("serverMotd", serverObject.getMotd());
        pluginMessage.set("serverState", serverObject.getState());
        pluginMessage.set("serverIpAddress", serverObject.getIpAddress() + "");
        pluginMessage.set("serverMaxPlayerCount", serverObject.getMaxPlayerCount() + "");
        pluginMessage.set("serverOnlinePlayerCount", serverObject.getOnlinePlayerCount() + "");
        pluginMessage.set("serverOnlinePlayers", new Gson().toJson(serverObject.getOnlinePlayers()));
        pluginMessage.set("serverPort", serverObject.getPort() + "");
        pluginMessage.set("serverSocketAddress", serverObject.getSocketAddress().toString());
        TimoCloudAPI.getUniversalAPI().getProxies().forEach(proxyObject -> proxyObject.sendPluginMessage(pluginMessage));
    }

}
