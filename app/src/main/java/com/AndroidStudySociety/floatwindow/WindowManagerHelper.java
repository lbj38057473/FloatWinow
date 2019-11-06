package com.AndroidStudySociety.floatwindow;

import android.content.Context;
import android.view.WindowManager;

/**
 * Created by liyilin on 2017/10/13.
 */

public class WindowManagerHelper {

    private static WindowManager windowManager;

    public static WindowManager getWindowManager(Context context) {
        if (windowManager == null) {
            windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        }
        return windowManager;
    }
}
