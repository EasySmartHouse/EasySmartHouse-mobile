package net.easysmarthouse.mobile.ui.android.websocket;

import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketFactory;
import net.easysmarthouse.mobile.ui.android.SmartHouseApp;
import net.easysmarthouse.mobile.ui.android.adapter.DevicesObserver;
import net.easysmarthouse.mobile.ui.android.domain.Webcam;
import net.easysmarthouse.mobile.ui.android.util.Log;

import java.io.IOException;

/**
 * Created by rusakovich on 26.02.2017.
 */
public class WebcamFactory {

    private static final long PING_INTERVAL = 5000l;

    private WebcamFactory(){
    }

    public static WebcamHandler getHandler(final DevicesObserver<Webcam> webcamObserver) {
        final String webcamUrl = SmartHouseApp.getContextResourceString("webcam_url");
        return new WebcamHandler(){

            private WebSocket webSocket;

            @Override
            public synchronized boolean connect() {
                try {
                    this.webSocket = new WebSocketFactory().createSocket(webcamUrl);
                    webSocket.setPingInterval(PING_INTERVAL);
                    webSocket.addListener(new WebcamListener(webcamObserver));
                    webSocket.connect();
                }catch (Exception ex){
                    Log.e("WebSocket connection exception", ex);
                    return false;
                }
                return true;
            }

            @Override
            public synchronized boolean close() {
                if (webSocket != null && webSocket.isOpen()) {
                    webSocket.disconnect();
                    webSocket = null;
                    return true;
                }
                return false;
            }

        };
    }

}
