package net.easysmarthouse.mobile.ui.android.task;

import net.easysmarthouse.mobile.ui.android.adapter.DevicesObserver;

import java.util.Collections;
import java.util.List;

/**
 * Created by rusakovich on 18.02.2017.
 */
public class IdleTask extends VoidAsyncTask<List>{

    public IdleTask(DevicesObserver observer) {
    }

    @Override
    protected List doInBackground(Void... voids) {
        return Collections.EMPTY_LIST;
    }
}
