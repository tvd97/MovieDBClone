package com.example.moviejava.ui.base;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.ViewModel;

import java.util.function.Consumer;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public abstract class BaseViewModel extends ViewModel {
    @RequiresApi(api = Build.VERSION_CODES.N)
    protected <T> void launchRxSingle(Observable<T> action,
                                      Consumer<T> onSuccess,
                                      Consumer<Throwable> onError){
        action.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<T>() {
                               @Override
                               public void onSubscribe(@NonNull Disposable d) {
                                   Log.d("onSubscribe",""+d);
                               }

                               @Override
                               public void onNext(@NonNull T t) {
                                   onSuccess.accept(t);
                               }

                               @Override
                               public void onError(@NonNull Throwable e) {
                                   onError.accept(e);
                               }

                               @Override
                               public void onComplete() {
                               }
                           }
                );
    }

}
