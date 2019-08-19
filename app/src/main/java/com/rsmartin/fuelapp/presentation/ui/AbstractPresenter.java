package com.rsmartin.fuelapp.presentation.ui;

import androidx.annotation.NonNull;

import com.rsmartin.fuelapp.domain.executor.ErrorHandler;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;

public abstract class AbstractPresenter<V> {

    private V viewProxy;
    private Class<V> viewClass;
    private ErrorHandler errorHandler;

    @SuppressWarnings("unchecked")
    public AbstractPresenter(ErrorHandler errorHandler) {
        viewClass = (Class<V>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];

        this.errorHandler = errorHandler;
    }

    protected V getView() {
        return viewProxy;
    }

    @SuppressWarnings("unchecked")
    public void setView(@NonNull V view) {
        this.viewProxy = (V) Proxy.newProxyInstance(
                viewClass.getClassLoader(),
                new Class[]{viewClass},
                new ViewInvocationHandler(view));
    }

    @SuppressWarnings("unchecked")
    public void unsetView() {
        this.viewProxy = (V) Proxy.newProxyInstance(
                viewClass.getClassLoader(),
                new Class[]{viewClass},
                new ViewInvocationHandler(null));
    }

    public ErrorHandler getErrorHandler() {
        return errorHandler;
    }

    /**
     * View invocation handler. Invokes view methods just if view exists.
     *
     * @param <V> the view type
     */
    private static class ViewInvocationHandler<V> implements InvocationHandler {

        private WeakReference<V> view;

        ViewInvocationHandler(V view) {
            this.view = new WeakReference<>(view);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (view == null || view.get() == null) {
                return null;
            }

            return method.invoke(view.get(), args);
        }
    }
}
