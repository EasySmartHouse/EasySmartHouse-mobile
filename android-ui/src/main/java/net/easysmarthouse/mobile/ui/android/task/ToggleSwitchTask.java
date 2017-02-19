package net.easysmarthouse.mobile.ui.android.task;

import net.easysmarthouse.mobile.ui.android.remote.RemoteServiceFactory;
import net.easysmarthouse.mobile.ui.android.util.Log;
import net.easysmarthouse.provider.device.actuator.SwitchActuator;
import net.easysmarthouse.provider.device.exception.DeviceException;
import net.easysmarthouse.ui.webui.client.rpc.ActuatorsService;

/**
 * Created by rusakovich on 19.02.2017.
 */
public class ToggleSwitchTask extends VoidAsyncTask<Boolean>{

    private ActuatorsService service = RemoteServiceFactory.INSTANCE.getService(ActuatorsService.class);
    private final SwitchActuator switchActuator;

    public ToggleSwitchTask(SwitchActuator switchActuator) {
        this.switchActuator = switchActuator;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        try{
            service.changeState(switchActuator.getAddress(), !switchActuator.getState());
            return true;
        }catch(DeviceException ex){
            Log.e("Error while togging switch state", ex);
            return false;
        }
    }
}
