package com.yade.kloudalc.Presenter

import android.content.Context
import com.yade.kloudalc.Keyboard.ActionButton

interface PresenterInterface {
    fun variablesChanged()
    fun constantsChanged()
    fun buttonClicked(button: ActionButton)
}