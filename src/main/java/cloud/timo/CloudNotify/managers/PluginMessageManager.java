package cloud.timo.CloudNotify.managers;

import cloud.timo.CloudNotify.CloudNotify;
import cloud.timo.TimoCloud.api.TimoCloudAPI;
import cloud.timo.TimoCloud.api.messages.listeners.MessageListener;
import cloud.timo.TimoCloud.api.messages.objects.AddressedPluginMessage;

public class PluginMessageManager implements MessageListener {

    public PluginMessageManager(){
        TimoCloudAPI.getMessageAPI().registerMessageListener(this::onPluginMessage, "CLOUDNOTIFY_NOTIFICATION");
    }

    @Override
    public void onPluginMessage(AddressedPluginMessage addressedPluginMessage) {
        CloudNotify.getInstance().getHelper().notify(addressedPluginMessage.getMessage());
    }
}
