package net.easysmarthouse.mobile.ui.android.handler;

import android.view.View;
import net.easysmarthouse.mobile.ui.android.task.ToggleSwitchTask;
import net.easysmarthouse.provider.device.actuator.SwitchActuator;

/**
 * Created by rusakovich on 19.02.2017.
 */
public class ToggleSwitchStateTask implements View.OnClickListener {

   private final SwitchActuator switchActuator;

    public ToggleSwitchStateTask(SwitchActuator switchActuator) {
        this.switchActuator = switchActuator;
    }

    @Override
    public void onClick(View view) {
        ToggleSwitchTask switchTask = new ToggleSwitchTask(switchActuator);
        switchTask.execute();
    }
}
