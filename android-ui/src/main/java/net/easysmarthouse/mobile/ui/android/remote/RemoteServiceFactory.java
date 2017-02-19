package net.easysmarthouse.mobile.ui.android.remote;

import net.easysmarthouse.mobile.ui.android.Language;

import java.util.Locale;

/**
 * Created by rusakovich on 18.02.2017.
 */
public interface RemoteServiceFactory {

    public <T> T getService(Class<T> serviceClass);

    public void setLanguage(Language language);

    public static final RemoteServiceFactory INSTANCE = new GRpcCachedServiceFactory();

}
