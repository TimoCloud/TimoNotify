package cloud.timo.CloudNotify.commands;

import cloud.timo.CloudNotify.CloudNotify;
import cloud.timo.CloudNotify.managers.MessageManager;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class CloudNotifyCommand extends Command {

    public CloudNotifyCommand(String name, String permissions) {
        super(name, permissions);
        ProxyServer.getInstance().getPluginManager().registerCommand(CloudNotify.getInstance(), this);
    }

    @Override
    public void execute(CommandSender commandSender, String[] args) {
        if (!(commandSender instanceof ProxiedPlayer)) {
            MessageManager.sendMessage(commandSender, CloudNotify.getInstance().getFileManager().getMessages().getString("onlyPlayer"));
            return;
        }
        ProxiedPlayer proxiedPlayer = (ProxiedPlayer) commandSender;

        if (args.length == 0) {
            boolean currentState = CloudNotify.getInstance().getDatabaseManager().getNotifyState(proxiedPlayer.getUniqueId());
            currentState = !currentState;

            CloudNotify.getInstance().getDatabaseManager().setNotifyState(proxiedPlayer.getUniqueId(), currentState);
            MessageManager.sendMessage(commandSender, CloudNotify.getInstance().getFileManager().getMessages().getString("changeStateCommand").replace("{0}", (currentState) ? CloudNotify.getInstance().getFileManager().getMessages().getString("enabled") :CloudNotify.getInstance().getFileManager().getMessages().getString("disabled")));
        }
    }

}
