package net.easysmarthouse.mobile.ui.android.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by rusakovich on 15.02.2017.
 */
abstract class BaseDeviceAdapter<D> extends BaseAdapter implements DevicesObserver<D>{

    protected Activity activity;
    protected LayoutInflater inflater;
    protected List<D> devices = new CopyOnWriteArrayList<>();

    public BaseDeviceAdapter(Context ctx, Activity activity) {
        this.activity = activity;
        this.inflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return devices.size();
    }

    @Override
    public Object getItem(int position) {
        return devices.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void clear() {
        devices.clear();
    }

    @Override
    public void addAll(List<D> devices) {
        this.devices.addAll(devices);
    }

    @Override
    public void add(D device) {
        if (!devices.contains(device)) {
            devices.add(device);
        }
    }

    @Override
    public void remove(D device) {
        if (devices.contains(device)){
            devices.remove(device);
        }
    }

    @Override
    public void update() {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                notifyDataSetChanged();
            }
        });
    }

}
