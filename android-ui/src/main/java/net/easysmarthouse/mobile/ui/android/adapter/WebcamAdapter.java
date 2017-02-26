package net.easysmarthouse.mobile.ui.android.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import net.easysmarthouse.mobile.ui.android.R;
import net.easysmarthouse.mobile.ui.android.domain.Webcam;

/**
 * Created by rusakovich on 26.02.2017.
 */
public class WebcamAdapter extends BaseDeviceAdapter<Webcam>{

    public WebcamAdapter(Context ctx, Activity activity) {
        super(ctx, activity);
    }

    private Webcam getWebcam(int position){
        return ((Webcam) getItem(position));
    }

    private void setImage(ImageView imageView, String image){
        byte[] decodedString = Base64.decode(image, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        imageView.setImageBitmap(bitmap);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_camerasmodule_detail, parent, false);
        }

        Webcam webcam = getWebcam(position);
        ((TextView) view.findViewById(R.id.tvCameraName)).setText(webcam.getName());

        ImageView imageView = (ImageView) view.findViewById(R.id.cameraView);

        if (webcam.getImage() != null) {
            setImage(imageView, webcam.getImage());
        }

        return view;
    }
}
