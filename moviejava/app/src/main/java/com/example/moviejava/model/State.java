package com.example.moviejava.model;

public class State<T> {
    private final Status status;
    private final T data;
    private final Throwable throwable;

    public State(Status status, T data, Throwable throwable) {
        this.status = status;
        this.data = data;
        this.throwable = throwable;
    }

    public Status getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public boolean isLoading() {
        return status == Status.Loading;
    }

    public boolean isSuccess() {
        return status == Status.Success;
    }

    public boolean isError() {
        return status == Status.Error;
    }

    public boolean isFinish() {
        return isError() || isSuccess();
    }

    public static <T> State<T> success(T data) {
        return new State<>(Status.Success, data, null);
    }

    public static <T> State<T> error(Throwable throwable) {
        return new State<>(Status.Error, null, throwable);
    }

    public static <T> State<T> loading() {
        return new State<>(Status.Loading, null, null);
    }
}

enum Status {
    Loading, Success, Error
}