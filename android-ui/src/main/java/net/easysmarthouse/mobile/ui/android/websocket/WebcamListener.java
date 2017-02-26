package net.easysmarthouse.mobile.ui.android.websocket;

import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import net.easysmarthouse.mobile.ui.android.adapter.DevicesObserver;
import net.easysmarthouse.mobile.ui.android.domain.Webcam;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by rusakovich on 26.02.2017.
 */
public class WebcamListener  extends WebSocketAdapter {

    private final List<Webcam> webcams = new CopyOnWriteArrayList<>();
    private final DevicesObserver<Webcam> webcamObserver;

    public WebcamListener(DevicesObserver<Webcam> webcamObserver) {
        this.webcamObserver = webcamObserver;
    }

    private void updateObserver(){
        webcamObserver.clear();
        webcamObserver.addAll(webcams);
        webcamObserver.update();
    }

    private void processListType(JSONObject webcamData) throws JSONException{
        JSONArray webcamsArray = webcamData.getJSONArray("webcams");
        int webcamsArrayLength = webcamsArray.length();

        webcams.clear();
        for (int i = 0; i < webcamsArrayLength; i++) {
            String webcamName = webcamsArray.getString(i);

            Webcam webcam = new Webcam();
            webcam.setName(webcamName);

            webcams.add(webcam);
        }

        updateObserver();
    }

    private Webcam findWebcam(String name){
        for(Webcam webcam: webcams){
            if (webcam.getName().equalsIgnoreCase(name)){
                return webcam;
            }
        }
        return null;
    }

    private void processImageType(JSONObject webcamData) throws JSONException{
        String webcamName = webcamData.getString("webcam");
        String webcamImage = webcamData.getString("image");

        Webcam webcam = findWebcam(webcamName);
        if (webcam == null){
            webcam = new Webcam();
        }
        webcam.setImage(webcamImage);

        if (webcam.getName() == null){
            webcam.setName(webcamName);
            webcams.add(webcam);
        }else{
            updateObserver();
        }
    }

    @Override
    public void onTextMessage(WebSocket websocket, String message) throws Exception {
        if (message == null || message.isEmpty()){
            return;
        }

        JSONObject webcamData = new JSONObject(message);
        String typeString = webcamData.getString("type");
        WebcamDataType webcamDataType = WebcamDataType.get(typeString);
        switch(webcamDataType){
            case LIST:
                processListType(webcamData);
                break;
            case IMAGE:
                processImageType(webcamData);
                break;
            case UNKNOWN:
            default:
                break;
        }
    }

}
