package com.example.moviejava.ui.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.viewbinding.ViewBinding;

import com.example.moviejava.model.State;




public abstract class BaseActivity<V extends ViewBinding> extends AppCompatActivity {
    private V viewBinding;

    protected V binding() {
        return viewBinding;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = getLayoutBinding();
        setContentView(viewBinding.getRoot());
        onHandleObject();
        onHandleEvent();
    }

    protected abstract V getLayoutBinding();

    protected abstract void onHandleObject();

    protected abstract void onHandleEvent();

    protected <T> void defaultObserver(LiveData<State<T>> liveData, OnErrorListener onErrorListener, OnFinishListener onFinishListener, OnLoadingListener onLoadingListener, OnSuccessListener<T> onSuccessListener) {
        liveData.observe(this, state -> {
            if (state.isLoading()) {
                onLoadingListener.onLoading(true);
            }
            if (state.isError()) {
                onLoadingListener.onLoading(false);
                // handle error base
//                if (state.getThrowable() instanceof HttpError) {
//                    handleHttpError((HttpError) state.getThrowable());
//                }

                if (onErrorListener != null) {
                    onErrorListener.onError(state.getThrowable());
                }
                if (onFinishListener != null) {
                    onFinishListener.onFinish();
                }
            }
            if (state.isSuccess()) {
                onLoadingListener.onLoading(false);
                onSuccessListener.onSuccess(state.getData());
                if (onFinishListener != null) {
                    onFinishListener.onFinish();
                }
            }
        });
    }
//    protected void handleHttpError(HttpError error) {
//        // Handle HTTP error here
//    }
    protected interface OnErrorListener {
        void onError(Throwable throwable);
    }

    protected interface OnFinishListener {
        void onFinish();
    }

    protected interface OnLoadingListener {
        void onLoading(boolean isLoading);
    }

    protected interface OnSuccessListener<T> {
        void onSuccess(T data);
    }
}
