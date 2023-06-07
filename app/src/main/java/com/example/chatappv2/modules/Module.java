package com.example.chatappv2.modules;

import android.widget.TextView;

public class Module {


    public Module(String moduleName) {
        this.ModuleName = moduleName;
    }

    public String getModuleName() {
        return ModuleName;
    }

    public void setModuleName(String moduleName) {
        this.ModuleName = moduleName;
    }

    String ModuleName;
}