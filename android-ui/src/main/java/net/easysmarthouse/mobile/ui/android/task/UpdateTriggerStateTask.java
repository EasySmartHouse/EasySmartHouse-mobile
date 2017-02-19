package net.easysmarthouse.mobile.ui.android.task;

import net.easysmarthouse.mobile.ui.android.remote.RemoteServiceFactory;
import net.easysmarthouse.provider.device.trigger.Trigger;
import net.easysmarthouse.ui.webui.client.rpc.TriggerService;

/**
 * Created by rusakovich on 19.02.2017.
 */
public class UpdateTriggerStateTask extends VoidAsyncTask<Void>{

    private TriggerService service = RemoteServiceFactory.INSTANCE.getService(TriggerService.class);
    private final boolean enabled;
    private final Trigger trigger;

    public UpdateTriggerStateTask(boolean enabled, Trigger trigger) {
        this.enabled = enabled;
        this.trigger = trigger;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        String triggerName = trigger.getName();
        if (triggerName != null) {
            service.setEnabled(triggerName, enabled);
        }
        return null;
    }

}
