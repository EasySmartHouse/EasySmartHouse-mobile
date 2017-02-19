package net.easysmarthouse.ui.webui.client.rpc;

import com.github.creepid.grpc.client.ServiceRelativePath;
import net.easysmarthouse.provider.device.actuator.Actuator;

import java.util.List;

/**
 * Created by rusakovich on 14.02.2017.
 */
@ServiceRelativePath("actuators")
public interface ActuatorsService {

    public List<Actuator> getActuators();

    public void changeState(String address, Boolean state);

    public void changeState(String address, Double state);

}
