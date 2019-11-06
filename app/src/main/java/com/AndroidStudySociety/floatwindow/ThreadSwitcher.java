package com.AndroidStudySociety.floatwindow;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by liyilin on 2017/10/11.
 */

public class ThreadSwitcher {

    public static void switch2MainThread(final SwitchListener switchListener) {
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                        switchListener.run();
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public interface SwitchListener{
        void run();
    }

}
