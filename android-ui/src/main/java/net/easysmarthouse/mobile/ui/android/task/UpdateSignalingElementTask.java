package net.easysmarthouse.mobile.ui.android.task;

import net.easysmarthouse.mobile.ui.android.remote.RemoteServiceFactory;
import net.easysmarthouse.provider.device.Device;
import net.easysmarthouse.provider.device.alarm.SignalingElement;
import net.easysmarthouse.ui.webui.client.rpc.SignalingService;

/**
 * Created by rusakovich on 18.02.2017.
 */
public class UpdateSignalingElementTask extends VoidAsyncTask<Void> {

    private SignalingService service = RemoteServiceFactory.INSTANCE.getService(SignalingService.class);
    private final boolean enabled;
    private final SignalingElement signaling;

    public UpdateSignalingElementTask(boolean enabled, SignalingElement signaling) {
        this.enabled = enabled;
        this.signaling = signaling;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        String address = null;
        if (signaling instanceof Device) {
            Device signalingDevice = (Device) signaling;
            address = signalingDevice.getAddress();
        }
        if (address != null) {
            service.setEnabled(address, enabled);
        }
        return null;
    }
}
