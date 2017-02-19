package net.easysmarthouse.mobile.ui.android.task;

import net.easysmarthouse.mobile.ui.android.adapter.DevicesObserver;
import net.easysmarthouse.mobile.ui.android.remote.RemoteServiceFactory;
import net.easysmarthouse.provider.device.actuator.Actuator;
import net.easysmarthouse.ui.webui.client.rpc.ActuatorsService;

import java.util.List;

/**
 * Created by rusakovich on 19.02.2017.
 */
public class GetActuatorsTask extends VoidAsyncTask<List<Actuator>>{

    private ActuatorsService actuatorsService = RemoteServiceFactory.INSTANCE.getService(ActuatorsService.class);
    private final DevicesObserver<Actuator> observer;

    public GetActuatorsTask(DevicesObserver<Actuator> observer) {
        this.observer = observer;
    }

    @Override
    protected List<Actuator> doInBackground(Void... voids) {
        List<Actuator> actualSensors =  actuatorsService.getActuators();
        observer.clear();
        observer.addAll(actualSensors);
        observer.update();
        return actualSensors;
    }
}
