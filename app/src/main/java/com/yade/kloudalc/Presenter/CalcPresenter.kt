package com.yade.kloudalc.Presenter

import android.content.IntentFilter
import com.cantrowitz.rxbroadcast.RxBroadcast
import com.yade.kloudalc.KloudalcApp
import io.reactivex.BackpressureStrategy
import io.reactivex.android.schedulers.AndroidSchedulers

class CalcPresenter : Presenter<PresenterInterface> {

    companion object {
        val INTERNAL_INTENT_PREFIX = "KloudAlc"
        val VAR_CHANGED = "$INTERNAL_INTENT_PREFIX.vars_changed"
        val BUTTON_CLICKED = "$INTERNAL_INTENT_PREFIX.button_clicked"
        val CONS_CHANGED = "$INTERNAL_INTENT_PREFIX.cons_changed"
    }

    constructor()


    override fun bindView(view: PresenterInterface) {
        super.bindView(view)

        val intentFilter = IntentFilter()
        intentFilter.addAction(VAR_CHANGED)
        intentFilter.addAction(VAR_CHANGED)
        intentFilter.addAction(VAR_CHANGED)

        addDisposable(RxBroadcast.fromBroadcast(KloudalcApp.instance.applicationContext,intentFilter)
                .toFlowable(BackpressureStrategy.LATEST)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({intent ->
                    val action = intent.action
                    when(action){
                        VAR_CHANGED -> {view.variablesChanged()}
                        CONS_CHANGED -> view.constantsChanged()
                    }
                }, {error -> })
        )
    }

    override fun unbindView(view: PresenterInterface) {
        super.unbindView(view)
    }
}