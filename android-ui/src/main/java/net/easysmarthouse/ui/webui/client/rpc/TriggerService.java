package net.easysmarthouse.ui.webui.client.rpc;

import com.github.creepid.grpc.client.ServiceRelativePath;
import net.easysmarthouse.provider.device.trigger.Trigger;

import java.util.List;

/**
 * Created by rusakovich on 14.02.2017.
 */
@ServiceRelativePath("trigger")
public interface TriggerService {

    public List<Trigger> getTriggers();

    public void setEnabled(String name, boolean enabled);

}
