package net.easysmarthouse.mobile.ui.android.task;

import android.os.AsyncTask;
import net.easysmarthouse.mobile.ui.android.adapter.DevicesObserver;
import net.easysmarthouse.mobile.ui.android.domain.Webcam;
import net.easysmarthouse.mobile.ui.android.websocket.WebcamFactory;
import net.easysmarthouse.mobile.ui.android.websocket.WebcamHandler;

/**
 * Created by rusakovich on 26.02.2017.
 */
public class WebcamConnectTask extends AsyncTask<Void, Void, WebcamHandler> {

    private final DevicesObserver<Webcam> observer;

    public WebcamConnectTask(DevicesObserver<Webcam> observer) {
        this.observer = observer;
    }

    @Override
    protected WebcamHandler doInBackground(Void... voids) {
        WebcamHandler handler = WebcamFactory.getHandler(observer);
        handler.connect();
        return handler;
    }
}
