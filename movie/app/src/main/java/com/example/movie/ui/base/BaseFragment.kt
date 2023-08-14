package com.example.movie.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.viewbinding.ViewBinding
import com.example.movie.model.State

abstract class BaseFragment<V:ViewBinding> :Fragment() {
    private lateinit var viewBinding: V
    protected val binding get() = viewBinding
    private var isCreated = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCreated = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (!::viewBinding.isInitialized) {
            viewBinding = setViewBinding(inflater, container)
        }
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (isCreated) {
            setupObjects(binding)
            setupEvents()
            isCreated = false
        }
    }

    protected abstract fun setViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): V

    protected abstract fun setupObjects(rootView: ViewBinding?)

    protected abstract fun setupEvents()

    protected fun <T> defaultObserver(
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
//
//                doOnError?.invoke(state.throwable)
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