package net.easysmarthouse.mobile.ui.android.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import net.easysmarthouse.mobile.ui.android.R;
import net.easysmarthouse.mobile.ui.android.handler.TriggerStateChangeListener;
import net.easysmarthouse.provider.device.trigger.Trigger;

/**
 * Created by rusakovich on 19.02.2017.
 */
public class TriggersAdapter extends BaseDeviceAdapter<Trigger>{

    public TriggersAdapter(Context ctx, Activity activity) {
        super(ctx, activity);
    }

    private Trigger getTrigger(int position){
        return ((Trigger) getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_signalingmodule_detail, parent, false);
        }

        Trigger trigger = getTrigger(position);
        ((TextView) view.findViewById(R.id.tvIndex)).setText(String.valueOf(position + 1));
        ((TextView) view.findViewById(R.id.tvName)).setText(trigger.getName());

        TextView tvStatus = (TextView) view.findViewById(R.id.tvStatus);
        if (trigger.isEnabled()){
            tvStatus.setText(R.string.text_triggersmodule_status_enabled);
        }else{
            tvStatus.setText(R.string.text_triggersmodule_status_disabled);
        }

        CheckBox cbActivation = (CheckBox)view.findViewById(R.id.cbActivation);
        cbActivation.setOnCheckedChangeListener(new TriggerStateChangeListener(trigger));

        return view;
    }
}
