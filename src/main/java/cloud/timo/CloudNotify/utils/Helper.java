package cloud.timo.CloudNotify.utils;

import cloud.timo.CloudNotify.CloudNotify;
import cloud.timo.CloudNotify.managers.FileManager;
import cloud.timo.CloudNotify.managers.MessageManager;
import cloud.timo.TimoCloud.api.objects.PlayerObject;
import cloud.timo.TimoCloud.api.objects.ServerObject;
import net.md_5.bungee.api.ChatColor;

public class Helper {

    public void notify(NotifyType notifyType, ServerObject serverObject) {
        FileManager fileManager = CloudNotify.getInstance().getFileManager();
        String mainMessage = (notifyType == NotifyType.REGISTER) ? fileManager.getMessages().getString("serverRegisterMessage") : fileManager.getMessages().getString("serverUnregisterMessage");

        StringBuilder onlinePlayers = new StringBuilder();
        for (PlayerObject onlinePlayer : serverObject.getOnlinePlayers())
            onlinePlayers.append(onlinePlayer.getName()).append(", ");

        MessageManager.sendMessageToTeam(ChatColor.translateAlternateColorCodes('&', mainMessage
                .replace("{serverName}", serverObject.getName())
                .replace("{serverBase}", serverObject.getBase().getName())
                .replace("{serverExtra}", serverObject.getExtra())
                .replace("{serverMap}", serverObject.getMap())
                .replace("{serverMOTD}", serverObject.getMotd())
                .replace("{serverState}", serverObject.getState())
                .replace("{serverIpAddress}", serverObject.getIpAddress() + "")
                .replace("{serverMaxPlayerCount}", serverObject.getMaxPlayerCount() + "")
                .replace("{serverOnlinePlayerCount}", serverObject.getOnlinePlayerCount() + "")
                .replace("{serverOnlinePlayers}", (onlinePlayers.length() == 0 ? "§cNobody is online." : onlinePlayers.toString().trim().substring(0, onlinePlayers.length() - 1)))
                .replace("{serverPort}", serverObject.getPort() + "")
                .replace("{serverSocketAddress}", serverObject.getSocketAddress().toString())));
    }

}
