package net.easysmarthouse.mobile.ui.android.task;

import net.easysmarthouse.mobile.ui.android.websocket.WebcamHandler;

/**
 * Created by rusakovich on 26.02.2017.
 */
public class WebcamCloseTask  extends VoidAsyncTask<Boolean>{

    private final WebcamHandler handler;

    public WebcamCloseTask(WebcamHandler handler) {
        this.handler = handler;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        if (handler != null){
            return handler.close();
        }
        return false;
    }
}
