package net.easysmarthouse.mobile.ui.android.fragment;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import net.easysmarthouse.mobile.ui.android.R;
import net.easysmarthouse.mobile.ui.android.activity.DeviceModuleDetailActivity;
import net.easysmarthouse.mobile.ui.android.activity.DeviceModuleListActivity;
import net.easysmarthouse.mobile.ui.android.adapter.*;
import net.easysmarthouse.mobile.ui.android.domain.ModuleItem;
import net.easysmarthouse.mobile.ui.android.domain.ModulesContent;
import net.easysmarthouse.mobile.ui.android.task.*;
import net.easysmarthouse.mobile.ui.android.util.Log;
import net.easysmarthouse.mobile.ui.android.util.ReflectionUtil;

import java.util.Timer;
import java.util.TimerTask;

/**
 * A fragment representing a single DeviceModule detail screen.
 * This fragment is either contained in a {@link DeviceModuleListActivity}
 * in two-pane mode (on tablets) or a {@link DeviceModuleDetailActivity}
 * on handsets.
 */
public class DeviceModuleDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_MODULE_ID = "module_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private ModuleItem moduleItem;
    private Activity activity;

    private static final long TASK_TIMER_DELAY = 5000;

    private final Handler taskHandler = new Handler();
    private boolean timerRunning = false;
    private Timer taskTimer = new Timer();


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public DeviceModuleDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_MODULE_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            String moduleIdArg = getArguments().getString(ARG_MODULE_ID);
            this.moduleItem = ModulesContent.MODULES.get(Integer.parseInt(moduleIdArg));
        }
    }

    private void addRepeatableTask(final Class<? extends VoidAsyncTask> taskClass, final DevicesObserver devicesObserver){
        TimerTask doAsynchronousTask = new TimerTask() {
            @Override
            public void run() {
                taskHandler.post(new Runnable() {
                    public void run() {
                        try {
                            VoidAsyncTask asyncTask = ReflectionUtil.createInstance(taskClass,
                                    new Class[]{DevicesObserver.class}, devicesObserver);
                            asyncTask.execute();
                        } catch (Exception e) {
                            Log.e("Error while executing task", e);
                        }
                    }
                });
            }
        };
        taskTimer.schedule(doAsynchronousTask, 0, TASK_TIMER_DELAY);
        timerRunning = true;
    }

    private void stopRepeatableTasks(){
        if (timerRunning) {
            taskTimer.cancel();
            taskTimer.purge();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_devicemodule_detail, container, false);
        ListView lvDeviceModuleDetail = (ListView)rootView.findViewById(R.id.lvDevicemodule_detail);

        ListAdapter adapter = null;
        Class<? extends VoidAsyncTask> asyncTaskClass = IdleTask.class;
        if (moduleItem != null) {
            activity.setTitle(moduleItem.getTitle());
            stopRepeatableTasks();
            switch(moduleItem.getModule()){
                case SIGNALING:
                    adapter = new SignalingAdapter(rootView.getContext(), activity);
                    asyncTaskClass = GetSignalingElementsTask.class;
                    break;
                case SENSORS: {
                    adapter = new SensorsAdapter(rootView.getContext(), activity);
                    asyncTaskClass = GetSensorsTask.class;
                    break;}
                case SWITCH:{
                    adapter = new ActuatorsAdapter(rootView.getContext(), activity);
                    asyncTaskClass = GetActuatorsTask.class;
                    break;}
                case TRIGGERS:{
                    adapter = new TriggersAdapter(rootView.getContext(), activity);
                    asyncTaskClass = GetTriggersTask.class;
                    break;}
                case CAMERAS:
                    //TODO add cameras adapter
                    break;
            }
        }
        if (adapter != null){
            lvDeviceModuleDetail.setAdapter(adapter);
        }
        addRepeatableTask(asyncTaskClass, (DevicesObserver)adapter);

        return rootView;
    }

    public void setActivity(Activity activity){
        this.activity = activity;
    }
}
