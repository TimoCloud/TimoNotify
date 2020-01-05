package cloud.timo.CloudNotify.managers;

import cloud.timo.CloudNotify.CloudNotify;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;

public class MessageManager {

    public static void sendMessage(CommandSender proxiedPlayer, String message) {
        proxiedPlayer.sendMessage(TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', CloudNotify.getInstance().getPrefix() + message)));
    }

}
