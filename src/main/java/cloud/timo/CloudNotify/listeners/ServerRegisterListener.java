package cloud.timo.CloudNotify.listeners;

import cloud.timo.CloudNotify.CloudNotify;
import cloud.timo.TimoCloud.api.TimoCloudAPI;
import cloud.timo.TimoCloud.api.events.EventHandler;
import cloud.timo.TimoCloud.api.events.Listener;
import cloud.timo.TimoCloud.api.events.server.ServerRegisterEvent;

public class ServerRegisterListener implements Listener {

    public ServerRegisterListener() {
        TimoCloudAPI.getEventAPI().registerListener(this);
    }

    @EventHandler
    public void onServerRegister(ServerRegisterEvent event) {
        CloudNotify.getInstance().getHelper().initiatingNotification(event, event.getServer());
    }

}
