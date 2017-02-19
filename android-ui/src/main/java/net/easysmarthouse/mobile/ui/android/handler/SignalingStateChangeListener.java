package net.easysmarthouse.mobile.ui.android.handler;

import android.widget.CompoundButton;
import net.easysmarthouse.mobile.ui.android.task.UpdateSignalingElementTask;
import net.easysmarthouse.provider.device.alarm.SignalingElement;

/**
 * Created by rusakovich on 18.02.2017.
 */
public class SignalingStateChangeListener implements CompoundButton.OnCheckedChangeListener {

    private final SignalingElement signaling;

    public SignalingStateChangeListener(SignalingElement signaling) {
        this.signaling = signaling;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        UpdateSignalingElementTask updateTask = new UpdateSignalingElementTask(isChecked,signaling);
        updateTask.execute();
    }
}
