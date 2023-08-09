package com.example.moviejava.ui.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.viewbinding.ViewBinding;

import com.example.moviejava.model.State;


public abstract class BaseFragment<V extends ViewBinding> extends Fragment {
    private V viewBinding;

    protected V binding() {
        return viewBinding;
    }
    private boolean isCreated = true;

   // public BaseFragment(@LayoutRes int contentLayoutId) {
//        super(contentLayoutId);
//    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isCreated = true;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (viewBinding == null)
            viewBinding = onHandleViewBinding(inflater, container);
        return viewBinding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (isCreated) {
            onHandleObject(viewBinding);
            onHandleEvents();
            isCreated = false;
        }
    }
    protected abstract V onHandleViewBinding(LayoutInflater inflater, ViewGroup container);

    protected abstract void onHandleObject(V rootView);

    protected abstract void onHandleEvents();
    protected <T> void defaultObserve(
            LiveData<State<T>> liveData,
            @Nullable OnErrorListener onErrorListener,
            @Nullable OnFinishListener onFinishListener,
            @Nullable OnLoadingListener onLoadingListener,
            OnSuccessListener<T> onSuccessListener
    ) {
        liveData.observe(this, state -> {
            if (state.isLoading()) {
                if (onLoadingListener != null) {
                    onLoadingListener.onLoading(true);
                }
            }
            if (state.isError()) {
                if (onLoadingListener != null) {
                    onLoadingListener.onLoading(false);
                }
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
                if (onLoadingListener != null) {
                    onLoadingListener.onLoading(false);
                }
                onSuccessListener.onSuccess(state.getData());
                if (onFinishListener != null) {
                    onFinishListener.onFinish();
                }
            }
        });
    }

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

//    protected void handleHttpError(HttpError error) {
//        // Handle HTTP error here
//    }

}
