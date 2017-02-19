package net.easysmarthouse.mobile.ui.android.domain;

import net.easysmarthouse.mobile.ui.android.Modules;
import net.easysmarthouse.mobile.ui.android.SmartHouseApp;

import java.util.*;

import static net.easysmarthouse.mobile.ui.android.SmartHouseApp.getContextResourceString;

/**
 * Created by rusakovich on 27.01.2017.
 */
public class ModulesContent {

    private ModulesContent(){
    }

    public static final List<ModuleItem> MODULES = new ArrayList<ModuleItem>();
    public static final Map<String, ModuleItem> MODULES_MAP = new LinkedHashMap<String, ModuleItem>();

    static {
        addItem(new ModuleItem("0", Modules.SIGNALING,
                getContextResourceString("module_signaling_title")));
        addItem(new ModuleItem("1", Modules.SENSORS,
                getContextResourceString("module_sensors_title")));
        addItem(new ModuleItem("2", Modules.SWITCH,
                getContextResourceString("module_switch_title")));
        addItem(new ModuleItem("3", Modules.TRIGGERS,
                getContextResourceString("module_triggers_title")));
        addItem(new ModuleItem("4", Modules.CAMERAS,
                getContextResourceString("module_cameras_title")));
    }

    private static void addItem(ModuleItem item) {
        MODULES.add(item);
        MODULES_MAP.put(item.getId(), item);
    }

}
