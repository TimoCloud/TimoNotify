package cloud.timo.CloudNotify.listeners;

import cloud.timo.CloudNotify.utils.NotifyType;
import cloud.timo.TimoCloud.api.TimoCloudAPI;
import cloud.timo.TimoCloud.api.events.EventHandler;
import cloud.timo.TimoCloud.api.events.Listener;
import cloud.timo.TimoCloud.api.events.ServerRegisterEvent;
import cloud.timo.CloudNotify.CloudNotify;
import cloud.timo.CloudNotify.managers.MessageManager;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;

public class ServerRegisterListener implements Listener {

    public ServerRegisterListener(){
        TimoCloudAPI.getEventImplementation().registerListener(this);
    }

    @EventHandler
    public void onServerRegister(ServerRegisterEvent event) {
        CloudNotify.getInstance().getHelper().notify(NotifyType.REGISTER, event.getServerObject());
    }

}
