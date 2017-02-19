package net.easysmarthouse.mobile.ui.android.adapter;

import net.easysmarthouse.provider.device.Device;

import java.util.List;

/**
 * Created by rusakovich on 12.02.2017.
 */
public interface DevicesObserver<D> {

    public void clear();

    public void add(D device);

    public void addAll(List<D> device);

    public void remove(D device);

    public void update();

}
