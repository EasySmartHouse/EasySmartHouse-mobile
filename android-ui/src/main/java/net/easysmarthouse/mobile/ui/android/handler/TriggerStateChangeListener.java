package net.easysmarthouse.mobile.ui.android.handler;

import android.widget.CompoundButton;
import net.easysmarthouse.mobile.ui.android.task.UpdateTriggerStateTask;
import net.easysmarthouse.provider.device.trigger.Trigger;

/**
 * Created by rusakovich on 19.02.2017.
 */
public class TriggerStateChangeListener implements CompoundButton.OnCheckedChangeListener{

    private final Trigger trigger;

    public TriggerStateChangeListener(Trigger trigger) {
        this.trigger = trigger;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        UpdateTriggerStateTask updateTask = new UpdateTriggerStateTask(isChecked, trigger);
        updateTask.execute();
    }
}
