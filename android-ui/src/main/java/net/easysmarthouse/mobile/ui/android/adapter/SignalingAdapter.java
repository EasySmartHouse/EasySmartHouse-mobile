package net.easysmarthouse.mobile.ui.android.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import net.easysmarthouse.mobile.ui.android.R;
import net.easysmarthouse.mobile.ui.android.handler.SignalingStateChangeListener;
import net.easysmarthouse.provider.device.Device;
import net.easysmarthouse.provider.device.alarm.SignalingElement;

/**
 * Created by rusakovich on 15.02.2017.
 */
public class SignalingAdapter extends BaseDeviceAdapter<SignalingElement> {

    private static final String DEVICE_LABEL_DEFAULT = "";

    public SignalingAdapter(Context ctx, Activity activity) {
        super(ctx, activity);
    }

    private SignalingElement getSignlingElement(int position){
        return ((SignalingElement) getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_signalingmodule_detail, parent, false);
        }

        SignalingElement signalingElement = getSignlingElement(position);
        ((TextView) view.findViewById(R.id.tvIndex)).setText(String.valueOf(position + 1));

        String deviceLabel = DEVICE_LABEL_DEFAULT;
        if (signalingElement instanceof Device) {
            Device signalingDevice = (Device) signalingElement;
            String label = signalingDevice.getLabel();
            if (label != null){
                deviceLabel = label;
            }
        }
        ((TextView) view.findViewById(R.id.tvName)).setText(deviceLabel);

        TextView tvStatus = (TextView) view.findViewById(R.id.tvStatus);
        if (signalingElement.isAlarm()){
            tvStatus.setText(R.string.text_signalingmodule_status_open);
        }else{
            tvStatus.setText(R.string.text_signalingmodule_status_closed);
        }

        CheckBox cbActivation = (CheckBox)view.findViewById(R.id.cbActivation);
        cbActivation.setOnCheckedChangeListener(new SignalingStateChangeListener(signalingElement));

        return view;
    }


}
