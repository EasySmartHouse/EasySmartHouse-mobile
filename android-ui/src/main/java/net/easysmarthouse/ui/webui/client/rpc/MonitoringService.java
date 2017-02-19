package net.easysmarthouse.ui.webui.client.rpc;

import com.github.creepid.grpc.client.ServiceRelativePath;
import net.easysmarthouse.provider.device.sensor.Sensor;

import java.util.List;

/**
 *
 * @author rusakovich
 */
@ServiceRelativePath("monitoring")
public interface MonitoringService  {

    public List<Sensor> getSensors();

}
