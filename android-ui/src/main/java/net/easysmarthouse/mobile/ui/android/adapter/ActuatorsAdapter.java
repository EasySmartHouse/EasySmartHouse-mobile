package net.easysmarthouse.mobile.ui.android.adapter;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import net.easysmarthouse.mobile.ui.android.R;
import net.easysmarthouse.mobile.ui.android.handler.ToggleSwitchStateTask;
import net.easysmarthouse.mobile.ui.android.util.Log;
import net.easysmarthouse.provider.device.actuator.Actuator;
import net.easysmarthouse.provider.device.actuator.ActuatorType;
import net.easysmarthouse.provider.device.actuator.SwitchActuator;

/**
 * Created by rusakovich on 19.02.2017.
 */
public class ActuatorsAdapter extends BaseDeviceAdapter<Actuator> {

    public ActuatorsAdapter(Context ctx, Activity activity) {
        super(ctx, activity);
    }

    private Actuator getActuator(int position) {
        return ((Actuator) getItem(position));
    }

    private void addToggleButton(View rootView, SwitchActuator switchActuator){
        if (rootView.findViewById(R.id.bActuatorToggleId) == null) {
            Button toggleButton = new Button(rootView.getContext());
            toggleButton.setId(R.id.bActuatorToggleId);
            toggleButton.setText(R.string.text_actuatorsmodule_button_toggle);
            toggleButton.setTextSize(10);
            toggleButton.setOnClickListener(new ToggleSwitchStateTask(switchActuator));

            LinearLayout layout = (LinearLayout) rootView.findViewById(R.id.actuatorLayout);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 0.06f);
            layout.addView(toggleButton, layoutParams);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_actuatorsmodule_detail, parent, false);
        }

        Actuator actuator = getActuator(position);
        ((TextView) view.findViewById(R.id.tvIndex)).setText(String.valueOf(position + 1));
        ((TextView) view.findViewById(R.id.tvName)).setText(actuator.getLabel());

        TextView tvStatus = (TextView) view.findViewById(R.id.tvStatus);
        ActuatorType actuatorType = actuator.getActuatorType();
        switch(actuatorType){
            case switchActuator:{
                SwitchActuator switchActuator = (SwitchActuator)actuator;
                try {
                    Boolean switchState = switchActuator.getState();
                    if (switchState){
                        tvStatus.setText(R.string.text_signalingmodule_status_open);
                    }else{
                        tvStatus.setText(R.string.text_signalingmodule_status_closed);
                    }
                }catch(Exception ex){
                    Log.e("Error while getting switch state", ex);
                    tvStatus.setText(ex.getMessage());
                }

                addToggleButton(view, switchActuator);
                break;
            }
            case adjustableActuator:{
                //TODO insert slider
                break;
            }
        }

        return view;
    }
}
