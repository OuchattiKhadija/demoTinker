package com.test.demotinker;


import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.orhanobut.hawk.Hawk;
import com.tencent.tinker.anno.DefaultLifeCycle;
import com.tencent.tinker.entry.ApplicationLike;
import com.tencent.tinker.loader.shareutil.ShareConstants;

@SuppressWarnings("unused")
@DefaultLifeCycle(application = ".MyTinkerApplication" ,
        flags = ShareConstants.TINKER_ENABLE_ALL,
        loadVerifyFlag = false) // are officially required to write this
public class SampleApplicationLike extends ApplicationLike {

    public SampleApplicationLike(Application application,
                                 int tinkerFlags,
                                 boolean tinkerLoadVerifyFlag,
                                 long applicationStartElapsedTime,
                                 long applicationStartMillisTime,
                                 Intent tinkerResultIntent) {
        super(application,
                tinkerFlags,
                tinkerLoadVerifyFlag,
                applicationStartElapsedTime,
                applicationStartMillisTime,
                tinkerResultIntent);

    }

    @Override
    public void onBaseContextAttached(Context base) {
        super.onBaseContextAttached(base);

        TinkerManager.installedTinker(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Hawk.init(getApplication()).build();
    }
}