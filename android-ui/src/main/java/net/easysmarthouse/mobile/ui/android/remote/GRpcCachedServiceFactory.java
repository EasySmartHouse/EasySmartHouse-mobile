package net.easysmarthouse.mobile.ui.android.remote;

import com.github.creepid.grpc.client.*;
import com.github.creepid.grpc.client.settings.*;
import com.github.creepid.grpc.client.transporter.OkHttpTransporter;
import com.github.creepid.grpc.client.transporter.Transporter;
import net.easysmarthouse.mobile.ui.android.*;
import net.easysmarthouse.ui.webui.client.rpc.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by rusakovich on 18.02.2017.
 */
class GRpcCachedServiceFactory implements RemoteServiceFactory{

    private static final Language DEFAULT_LANGUAGE = Language.English;
    private Language language = DEFAULT_LANGUAGE;
    private Class<? extends Transporter> transporterClass = OkHttpTransporter.class;

    private Map<Class, Object> serviceCache = new ConcurrentHashMap<>();

    protected String getPolicyFileValue(Class serviceClass){
        String policyFileResourceName = "";
        if (serviceClass == ActuatorsService.class){
            policyFileResourceName = "service_actuators_policy";
        }else if (serviceClass == MonitoringService.class){
            policyFileResourceName = "service_monitoring_policy";
        }else if (serviceClass == SignalingService.class){
            policyFileResourceName = "service_signaling_policy";
        }else if (serviceClass == TriggerService.class){
            policyFileResourceName = "service_triggers_policy";
        }
        return SmartHouseApp.getContextResourceString(policyFileResourceName);
    }

    protected Object createService(String serviceUrl, String policyFile, Class serviceClass){
        GRpcSettings settings = new GRpcSettings();

        RequestHeader langHeader = (language == Language.Russian)
                ? new RequestHeader("Accept-Language", "ru-ru,ru;q=0.5")
                : new RequestHeader("Accept-Language", "en-gb,en;q=0.5");

        settings.put(GRpcSetting.BASE_URL, serviceUrl);
        settings.put(GRpcSetting.POLICY_FILE_STRONG_NAME, policyFile);
        settings.put(GRpcSetting.CUSTOM_HTTP_HTTPS_HEADER, langHeader);

        GRPC.setTransporterClass(transporterClass);
        return GRPC.create(serviceClass, settings);
    }

    @Override
    public <T> T getService(Class<T> serviceClass) {
        if (serviceCache.containsKey(serviceClass)){
            return (T)serviceCache.get(serviceClass);
        }

        String serviceUrl = SmartHouseApp.getContextResourceString("service_url");
        String policyFile = getPolicyFileValue(serviceClass);

        Object service = createService(serviceUrl, policyFile, serviceClass);
        serviceCache.put(serviceClass, service);
        return (T) service;
    }

    private void clearCache(){
        serviceCache.clear();
    }

    public void setLanguage(Language language){
        this.language = language;
        clearCache();
    }
}
