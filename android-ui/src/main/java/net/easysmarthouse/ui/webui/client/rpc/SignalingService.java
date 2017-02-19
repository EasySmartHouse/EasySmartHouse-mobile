package net.easysmarthouse.ui.webui.client.rpc;

import com.github.creepid.grpc.client.ServiceRelativePath;
import net.easysmarthouse.provider.device.alarm.SignalingElement;

import java.util.List;

/**
 * Created by rusakovich on 14.02.2017.
 */
@ServiceRelativePath("signaling")
public interface SignalingService {

    public List<SignalingElement> getSignalingElements();

    public void setEnabled(String address, boolean enabled);

}
