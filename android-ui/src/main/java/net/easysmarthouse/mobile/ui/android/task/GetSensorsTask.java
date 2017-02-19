package net.easysmarthouse.mobile.ui.android.task;

import android.os.AsyncTask;
import net.easysmarthouse.mobile.ui.android.adapter.DevicesObserver;
import net.easysmarthouse.mobile.ui.android.remote.RemoteServiceFactory;
import net.easysmarthouse.provider.device.sensor.Sensor;
import net.easysmarthouse.ui.webui.client.rpc.MonitoringService;

import java.util.List;

/**
 * Created by rusakovich on 26.01.2017.
 */
public class GetSensorsTask extends VoidAsyncTask<List<Sensor>> {

    private MonitoringService monitoringService = RemoteServiceFactory.INSTANCE.getService(MonitoringService.class);
    private final DevicesObserver<Sensor> observer;

    public GetSensorsTask(DevicesObserver<Sensor> observer) {
        this.observer = observer;
    }

    @Override
    protected List<Sensor> doInBackground(Void... voids) {
        List<Sensor> actualSensors =  monitoringService.getSensors();
        observer.clear();
        observer.addAll(actualSensors);
        observer.update();
        return actualSensors;
    }
}
