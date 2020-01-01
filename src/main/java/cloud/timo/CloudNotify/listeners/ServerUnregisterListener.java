package cloud.timo.CloudNotify.listeners;

import cloud.timo.CloudNotify.utils.NotifyType;
import cloud.timo.TimoCloud.api.TimoCloudAPI;
import cloud.timo.TimoCloud.api.events.EventHandler;
import cloud.timo.TimoCloud.api.events.Listener;
import cloud.timo.CloudNotify.CloudNotify;
import cloud.timo.TimoCloud.api.events.server.ServerUnregisterEvent;

public class ServerUnregisterListener implements Listener {

    public ServerUnregisterListener(){
        TimoCloudAPI.getEventImplementation().registerListener(this);
    }

    @EventHandler
    public void onServerUnregister(ServerUnregisterEvent event) {
        CloudNotify.getInstance().getHelper().notify(NotifyType.UNREGISTER, event.getServer());
    }

}
