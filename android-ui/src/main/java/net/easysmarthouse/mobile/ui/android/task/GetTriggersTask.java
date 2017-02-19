package net.easysmarthouse.mobile.ui.android.task;

import net.easysmarthouse.mobile.ui.android.adapter.DevicesObserver;
import net.easysmarthouse.mobile.ui.android.remote.RemoteServiceFactory;
import net.easysmarthouse.provider.device.trigger.Trigger;
import net.easysmarthouse.ui.webui.client.rpc.TriggerService;

import java.util.List;

/**
 * Created by rusakovich on 19.02.2017.
 */
public class GetTriggersTask extends VoidAsyncTask<List<Trigger>>{

    private TriggerService triggerService = RemoteServiceFactory.INSTANCE.getService(TriggerService.class);
    private final DevicesObserver<Trigger> observer;

    public GetTriggersTask(DevicesObserver<Trigger> observer) {
        this.observer = observer;
    }

    @Override
    protected List<Trigger> doInBackground(Void... voids) {
        List<Trigger> actualSensors =  triggerService.getTriggers();
        observer.clear();
        observer.addAll(actualSensors);
        observer.update();
        return actualSensors;
    }
}
