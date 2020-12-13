package com.yade.kloudalc.Keyboard.Fragments

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.yade.kloudalc.Kactivity
import com.yade.kloudalc.Keyboard.ActionButton
import com.yade.kloudalc.Presenter.CalcPresenter
import com.yade.kloudalc.Presenter.PresenterInterface
import com.yade.kloudalc.R
import com.yade.kloudalc.Variables.VariableAdapter
import kotlinx.android.synthetic.main.mathboard_fragment_num.view.*

class VarFragment : Fragment, PresenterInterface {
    constructor() : super()

    val presenter = CalcPresenter()
    val adapter = VariableAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.bindView(this)
    }

    override fun onDestroyView() {
        presenter.unbindView(this)
        super.onDestroyView()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.mathboard_fragment_num,container,false)

        val varRecycler = RecyclerView(context)

        adapter.callBack = (activity as Kactivity).currentFragment
        varRecycler.layoutManager = LinearLayoutManager(context)
        varRecycler.adapter = adapter

        if (varRecycler.parent != null){
            (varRecycler.parent as ViewGroup).removeView(varRecycler)
        }
        v.mathboard_num.addView(varRecycler,LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT))

        return v
    }

    override fun variablesChanged() {
        Log.i("FUCK","Variables Changed in Fragment")
        adapter.notifyDataSetChanged()
    }

    override fun constantsChanged() {

    }

    override fun buttonClicked(button: ActionButton) {
    }
}