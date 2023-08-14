package com.example.movie.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.viewbinding.ViewBinding
import com.example.movie.model.State


abstract class BaseActivity<V : ViewBinding> : AppCompatActivity() {
    private lateinit var viewBinding: V

    protected val binding get() = viewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = getLayoutBinding()
        setContentView(viewBinding.root)
        onHandleObject()
        onHandleEvent()
    }

    protected abstract fun getLayoutBinding(): V

    protected abstract fun onHandleObject()

    protected abstract fun onHandleEvent()
    protected open fun <T> defaultObserver(
        liveData: LiveData<State<T>>,
        // doOnError: ((throwable: Throwable?) -> Unit)? = null,
        doOnFinish: (() -> Unit)? = null,
        doOnLoading: ((Boolean) -> Unit)? = null,
        doOnSuccess: (T?) -> Unit,
    ) {
        liveData.observe(this) { state ->
            if (state.isLoading) {
                doOnLoading?.invoke(true)
            }
            if (state.isError) {
                doOnLoading?.invoke(false)
                // handle error base
//                if (state.throwable is HttpError) {
//                    handleHttpError(state.throwable)
//                }

                //doOnError?.invoke(state.throwable)
                doOnFinish?.invoke()
            }
            if (state.isSuccess) {
                doOnLoading?.invoke(false)
                doOnSuccess(state.data)
                doOnFinish?.invoke()
            }
        }
    }

    protected fun <T> defaultObserver(
        liveData: LiveData<T>,
        onSuccess: (T) -> Unit
    ) {
        liveData.observe(this) { t -> onSuccess.invoke(t) }
    }
}