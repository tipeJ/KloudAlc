package com.yade.kloudalc.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yade.kloudalc.Interfaces.boardCallBack
import com.yade.kloudalc.Kactivity
import com.yade.kloudalc.Keyboard.*
import com.yade.kloudalc.Keyboard.Fragments.NumFragment
import com.yade.kloudalc.Presenter.CalcPresenter
import com.yade.kloudalc.Presenter.PresenterInterface
import com.yade.kloudalc.R
import com.yade.kloudalc.Tabs.CalcPage
import com.yade.kloudalc.Tabs.Page
import com.yade.kloudalc.Tabs.PageManager
import com.yade.kloudalc.Utils.Calculator
import com.yade.kloudalc.Views.MathInput
import kotlinx.android.synthetic.main.fragment_calculator.view.*


class CalcFragment : Fragment, boardCallBack, PresenterInterface {

    var input : MathInput ?= null

    override
    var board = Mathboard()

    val numFragment = NumFragment()

    private val presenter = CalcPresenter()

    constructor() : super()

    companion object Factory {
        val longKey = "CalcFragment.LongKey"

        fun newInstance(type: String): CalcFragment {
            val args = Bundle()
            args.putString(longKey, type)

            val fragment = CalcFragment()
            fragment.arguments = args

            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_calculator, container, false)

        val transaction = childFragmentManager.beginTransaction()

        if (arguments?.getString(longKey) == Mathboard.typeFun) {
            numFragment.callback = (activity as Kactivity).currentFragment
            board = Mathboard.newInstance(Mathboard.typeFun)

            transaction.replace(R.id.calculator_mathboard, board)
                    .replace(R.id.calculator_mathboard_right, numFragment)
                    .commit()
        } else {
            transaction.replace(R.id.calculator_mathboard, board)
                    .commit()
        }

        input = v.calculator_input

        v.calculator_input.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                v.calculator_output.setText(Calculator.Calculate(v.calculator_input.actual))
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

        loadPage((PageManager.instance.currentPage) as CalcPage)

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.bindView(this)
    }

    override fun buttonClicked(button: ActionButton) {
        when (button.getTitle()) {
            "=" ->{
                val output = view?.calculator_output?.text.toString()
                input?.clear()
                input?.addAction(NumButton(output))
            }
            else ->
                input?.addAction(button)
        }

    }

    override fun onDestroyView() {
        presenter.unbindView(this)
        super.onDestroyView()
    }

    override fun buttonRemoved(position: Int) {

    }

    override fun move(amount: Int, direction: Int) {

    }

    override fun loadPage(page: Page) {
        if (page is CalcPage) {
            input?.clear()
            val data = ((PageManager.instance.currentPage as CalcPage).input).toMutableList()
            data.forEach { it ->
                buttonClicked(it)
            }
        }
    }

    override fun variablesChanged() {
        Log.i("CalcFragment","It happened")
    }

    override fun constantsChanged() {
    }
}