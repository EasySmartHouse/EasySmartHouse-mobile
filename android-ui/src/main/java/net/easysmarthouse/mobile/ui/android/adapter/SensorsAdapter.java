package net.easysmarthouse.mobile.ui.android.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import net.easysmarthouse.mobile.ui.android.R;
import net.easysmarthouse.mobile.ui.android.util.Log;
import net.easysmarthouse.mobile.ui.android.util.NumberUtils;
import net.easysmarthouse.provider.device.exception.DeviceException;
import net.easysmarthouse.provider.device.sensor.Sensor;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * Created by rusakovich on 26.01.2017.
 */
public class SensorsAdapter extends BaseDeviceAdapter<Sensor> {

    public SensorsAdapter(Context ctx, Activity activity) {
        super(ctx, activity);
    }

    private Sensor getSensor(int position) {
        return ((Sensor) getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_sensorsmodule_detail, parent, false);
        }

        Sensor sensor = getSensor(position);
        ((TextView) view.findViewById(R.id.tvIndex)).setText(String.valueOf(position + 1));
        ((TextView) view.findViewById(R.id.tvName)).setText(sensor.getLabel());

        TextView tvValue = (TextView) view.findViewById(R.id.tvValue);
        try {
            tvValue.setText(NumberUtils.getFormattedSimple(sensor.getValue()));
        } catch (DeviceException ex) {
            Log.e("Error while getting sensor value");
            tvValue.setText(ex.getMessage());
        }
        return view;
    }

}
