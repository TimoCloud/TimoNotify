package cloud.timo.TimoNotify.listeners;

import cloud.timo.TimoCloud.api.TimoCloudAPI;
import cloud.timo.TimoCloud.api.events.EventHandler;
import cloud.timo.TimoCloud.api.events.Listener;
import cloud.timo.TimoCloud.api.events.ServerRegisterEvent;
import cloud.timo.TimoNotify.TimoNotify;
import cloud.timo.TimoNotify.managers.MessageManager;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;

public class ServerRegisterListener implements Listener {

    public ServerRegisterListener(){
        TimoCloudAPI.getEventImplementation().registerListener(this);
    }

    @EventHandler
    public void onServerRegister(ServerRegisterEvent event) {
        ProxyServer.getInstance().getPlayers().forEach(proxiedPlayer -> MessageManager.sendMessageTeam(proxiedPlayer, ChatColor.translateAlternateColorCodes('&', TimoNotify.getInstance().getFileManager().getMessages().getString("serverRegisterMessage")
                .replace("{serverName}", event.getServerObject().getName())
                .replace("{serverBase}", event.getServerObject().getBase())
                .replace("{serverExtra}", event.getServerObject().getExtra())
                .replace("{serverMap}", event.getServerObject().getMap())
                .replace("{serverMOTD}", event.getServerObject().getMotd())
                .replace("{serverState}", event.getServerObject().getState())
                .replace("{serverIpAddress}", event.getServerObject().getIpAddress() + "")
                .replace("{serverMaxPlayerCount}", event.getServerObject().getMaxPlayerCount() + "")
                .replace("{serverOnlinePlayerCount}", event.getServerObject().getOnlinePlayerCount() + "")
                .replace("{serverOnlinePlayers}", event.getServerObject().getOnlinePlayers() + "")
                .replace("{serverPort}", event.getServerObject().getPort() + "")
                .replace("{serverSocketAddress}", event.getServerObject().getSocketAddress() + ""))));
    }
}
