package cloud.timo.CloudNotify.managers;

import cloud.timo.CloudNotify.CloudNotify;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;

public class MessageManager {

    public static void sendMessageToTeam(String message) {
        ProxyServer.getInstance().getPlayers().forEach(proxiedPlayer -> {
            if (proxiedPlayer.hasPermission("cloudnotify.notify"))
                proxiedPlayer.sendMessage(new TextComponent(CloudNotify.getInstance().getPrefix() + message));
        });
    }

}
