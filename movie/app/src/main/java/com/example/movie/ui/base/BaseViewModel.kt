package com.example.movie.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {
    protected fun <T> launchViewModel(
        block: Flow<T>, onSuccess: ((T) -> Unit), onError: ((Throwable) -> Unit)? = null
    ) {
        viewModelScope.launch {
            block.catch {
                onError?.invoke(it)
            }.collect {
                onSuccess.invoke(it)
            }
        }
    }

}