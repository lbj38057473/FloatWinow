package com.AndroidStudySociety.floatwindow;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

public class FloatService extends Service implements View.OnTouchListener {

    private String TAG = this.getClass().getSimpleName();

    private WindowManager windowManager;
    private View floatView;
    private boolean touchable = true;
    private WindowManager.LayoutParams layoutParams;

    public FloatService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.e(TAG, "onStart");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand");
        prepareWindowManager();
        createFloatView();
        createLayoutParams();
        attachFloatView2Window();
        return super.onStartCommand(intent, flags, startId);
    }

    private void createLayoutParams() {
        layoutParams = new WindowManager.LayoutParams();
        layoutParams.alpha = 0.8f; //透明度
        layoutParams.gravity = Gravity.START | Gravity.TOP; //设置起始位置
//        layoutParams.type = WindowManager.LayoutParams.TYPE_PRIORITY_PHONE; // 设置window的显示等级（tips：Android5.0一下可以使用TYPE_TOAST,做全局的悬浮窗显示，且不需要全县）
        layoutParams.type = WindowManager.LayoutParams.TYPE_TOAST; // 设置window的显示等级（tips：Android5.0一下可以使用TYPE_TOAST,做全局的悬浮窗显示，且不需要全县）
        if (touchable) { //控制是否可以响应触摸事件
            layoutParams.flags = WindowManager.LayoutParams.FLAG_FULLSCREEN // 全屏幕显示
                    | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;  //限制在屏幕内显示
        } else {
            layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL //不可响应触摸事件
                    | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
        }
        layoutParams.format = PixelFormat.RGBA_8888;// 背景透明
//        int[] realScreenSize = DisplayHelper.getRealScreenSize(getBaseContext());
////            int[] realScreenSize = {1440, 2560};
//        Log.e(TAG,"realSize:" + realScreenSize[0] + "_" + realScreenSize[1]);
        layoutParams.width = 300; //设置宽度属性
        layoutParams.height = 300; //设置高度属性
    }

    private void attachFloatView2Window() {
        windowManager.addView(floatView, layoutParams); //添加view到window上进行显示
        windowManager.updateViewLayout(floatView, layoutParams); //更新相关的属性，使其生效
    }

    private void removeViewFromWindow() {
        windowManager.removeView(floatView); //从当前window中移除该view，即取消显示
    }

    private void createFloatView() {
        floatView = LayoutInflater.from(getBaseContext()).inflate(R.layout.layout_float_view, null, false);
        floatView.setOnTouchListener(this);
    }

    private void prepareWindowManager() {
        Log.e(TAG, "prepareWindowManager");
        //windowManger是沟通app和WINDOW_SERVICE系统服务的一个接口，真正的实现类是WindowMangerImpl
        windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
    }


    public WindowManager getWindowManager() {
        return windowManager;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                layoutParams.x = (int) event.getRawX() - 150;
                layoutParams.y = (int) event.getRawY() - 150;
                windowManager.updateViewLayout(floatView, layoutParams);
                break;
        }
        return true;
    }
}
