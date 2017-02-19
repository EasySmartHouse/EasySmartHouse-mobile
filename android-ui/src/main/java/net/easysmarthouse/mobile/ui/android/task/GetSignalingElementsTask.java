package net.easysmarthouse.mobile.ui.android.task;

import android.os.AsyncTask;
import net.easysmarthouse.mobile.ui.android.adapter.DevicesObserver;
import net.easysmarthouse.mobile.ui.android.remote.RemoteServiceFactory;
import net.easysmarthouse.provider.device.alarm.SignalingElement;
import net.easysmarthouse.ui.webui.client.rpc.SignalingService;

import java.util.List;

/**
 * Created by rusakovich on 18.02.2017.
 */
public class GetSignalingElementsTask extends VoidAsyncTask<List<SignalingElement>> {

    private SignalingService signalingService = RemoteServiceFactory.INSTANCE.getService(SignalingService.class);
    private final DevicesObserver<SignalingElement> observer;

    public GetSignalingElementsTask(DevicesObserver<SignalingElement> observer) {
        this.observer = observer;
    }

    @Override
    protected List<SignalingElement> doInBackground(Void... voids) {
        List<SignalingElement> elements =  signalingService.getSignalingElements();
        observer.clear();
        observer.addAll(elements);
        observer.update();
        return elements;
    }
}
