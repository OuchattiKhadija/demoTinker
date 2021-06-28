package com.test.demotinker;

import android.content.Context;

import com.tencent.tinker.entry.ApplicationLike;
import com.tencent.tinker.lib.patch.UpgradePatch;
import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.tinker.TinkerInstaller;


public class TinkerManager {

    private static boolean isInstalled = false; // whether the flag has been initialized
    private static ApplicationLike mApplicationLike;

    /**
     * Complete Tinker initialization
     *
     * @param applicationLike
     */
    public static void installedTinker(ApplicationLike applicationLike) {
        mApplicationLike = applicationLike;
        if (isInstalled) {
            return;
        }
        Tinker tinker = new Tinker.Builder(applicationLike.getApplication()).build();
        Tinker.create(tinker);
        tinker.install(applicationLike.getTinkerResultIntent(), com.test.demotinker.MyTinkerService.class, new UpgradePatch());
        isInstalled = true;
    }

    /**
     * Complete the loading of the patch file
     *
     * @param path patch file path
     */
    public static void loadPatch(String path) {
        if (Tinker.isTinkerInstalled()) {//has been installed
            TinkerInstaller.onReceiveUpgradePatch(getApplicationContext(), path);
        }
    }

    /**
     * Use the Tinker proxy Application to get the context of the application global
     * @return global context
     */
    private static Context getApplicationContext() {
        if (mApplicationLike != null)
            return mApplicationLike.getApplication().getApplicationContext();
        return null;
    }

}
