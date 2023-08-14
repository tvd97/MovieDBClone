package com.example.movie.model


class State<T>(
    val status: Status, val data: T? = null, val throwable: Throwable? = null
) {


    val isLoading = status is Status.Loading
    val isSuccess = status is Status.Success
    val isError = status is Status.Error
    val isFinish: Boolean
        get() = isError || isSuccess

    companion object {
        fun <T> success(data: T? = null): State<T> {
            return State(Status.Success, data)
        }

        fun <T> error(throwable: Throwable?): State<T> {
            return State(Status.Error, throwable = throwable)
        }

        fun <T> loading(): State<T> {
            return State(Status.Loading)
        }
    }

    sealed class Status {
        object Loading : Status()
        object Success : Status()
        object Error : Status()
    }
}
