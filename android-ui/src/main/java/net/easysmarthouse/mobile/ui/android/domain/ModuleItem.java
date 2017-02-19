package net.easysmarthouse.mobile.ui.android.domain;

import net.easysmarthouse.mobile.ui.android.Modules;

import java.io.Serializable;

/**
 * Created by rusakovich on 27.01.2017.
 */
public class ModuleItem implements Serializable {

    private String id;
    private Modules module;
    private String title;

    public ModuleItem(String id, Modules module, String title) {
        this.id = id;
        this.module = module;
        this.title = title;
    }

    public String getTitle(){
        return this.title;
    }

    public String getId(){
        return this.id;
    }

    public Modules getModule(){
        return this.module;
    }

    @Override
    public String toString() {
        return title;
    }
}
