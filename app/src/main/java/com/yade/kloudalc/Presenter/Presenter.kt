package com.yade.kloudalc.Presenter

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class Presenter<V> {

    val compositeDisposables = CompositeDisposable()

    open var view : V ?= null

    fun addDisposable(disposable: Disposable){
        compositeDisposables.add(disposable)
    }

    open fun bindView(view: V){
        val previousView = this.view

        if (previousView != null) {
            throw IllegalStateException("Previous view not unbound. Prev. View: " + previousView);
        }
        this.view = view
    }
    open fun unbindView(view: V){
        val previousView = this.view

        if (previousView == view) {
            this.view = null
        } else {
            throw IllegalStateException("Unexpected view! previousView = " + previousView + ", view to unbind = " + view)
        }

        // Unsubscribe all disposables that need to be unsubscribed in this lifecycle state.
        compositeDisposables.clear()
    }
}