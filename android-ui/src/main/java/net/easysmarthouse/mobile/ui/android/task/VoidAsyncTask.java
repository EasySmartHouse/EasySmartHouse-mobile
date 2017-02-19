package net.easysmarthouse.mobile.ui.android.task;

import android.os.AsyncTask;

/**
 * Created by rusakovich on 18.02.2017.
 */
public abstract class VoidAsyncTask<T> extends AsyncTask<Void, Void, T> {
    public void execute() {
        super.execute();
    }
}
