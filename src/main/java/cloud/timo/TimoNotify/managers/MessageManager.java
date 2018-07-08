package cloud.timo.TimoNotify.managers;

import cloud.timo.TimoNotify.TimoNotify;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class MessageManager {

    public static void sendMessageTeam(ProxiedPlayer proxiedPlayer, String message) {
        if (proxiedPlayer.hasPermission(TimoNotify.getInstance().getFileManager().getConfig().getString("getNotifyPermission"))) {
            proxiedPlayer.sendMessage(new TextComponent(message));
        }
    }
}
