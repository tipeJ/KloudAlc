package com.yade.kloudalc.Keyboard

import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.PorterDuff
import android.opengl.Visibility
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.*
import android.widget.Button
import android.widget.LinearLayout
import android.widget.PopupWindow
import com.yade.kloudalc.Interfaces.boardCallBack
import com.yade.kloudalc.KloudalcApp
import com.yade.kloudalc.Presenter.CalcPresenter
import com.yade.kloudalc.R
import com.yade.kloudalc.Views.MathButtonView


class ButtonRow : LinearLayout {

    val TAG = "ButtonRow"

    var list = ArrayList<MathButtonView>()
    var callBack : boardCallBack? = null

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr){
        init(context, attrs)
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes){
        init(context, attrs)
    }

    fun init(context: Context?, attrs: AttributeSet?){

        val t = getContext().obtainStyledAttributes(attrs, R.styleable.ButtonRow)

        val buttoncount = t.getInt(R.styleable.ButtonRow_button_count,0)

        t.recycle()

        orientation = LinearLayout.VERTICAL
        val l = LayoutParams(0,LayoutParams.MATCH_PARENT,1.0f)
        layoutParams = l

        val buttonparams = LayoutParams(LayoutParams.MATCH_PARENT,0, 1.0f)
        for (i in 0 until buttoncount){
            val button = MathButtonView(context, attrs)
            button.layoutParams = buttonparams
            list.add(button)
            addView(button)
        }
    }
    fun setButtonData(list: ArrayList<ActionButton>){
        if (list.size == this.list.size){
            for (i in 0 until list.size) {
                this.list[i].setButton(list[i])
                this.list[i].setOnClickListener(View.OnClickListener {
                    callBack?.buttonClicked(list[i])

                })
                val subButtons = list[i].getSubButtons()
                if (subButtons.size == 0) {
                    continue
                } else if (subButtons.size == 1) {
                    this.list[i].setOnLongClickListener(View.OnLongClickListener {
                        callBack?.buttonClicked(list[i].getSubButtons()[0])
                        true
                    })
                }
                else if(subButtons.size >= 1){
                    this.list[i].setOnLongClickListener(View.OnLongClickListener {
                        val row = ButtonRow(context)
                        var popupWindow = callBack?.board?.popupWindow
                        popupWindow = null
                        for (i2 in 0 until subButtons.size){
                            val button = MathButtonView(context)
                            button.setButton(subButtons[i2])
                            button.setOnClickListener(View.OnClickListener{
                                callBack?.buttonClicked(subButtons[i2])
                                popupWindow?.dismiss()
                                popupWindow = null
                            })
                            row.list.add(button)
                            row.addView(button)
                        }
                        row.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT)
                        popupWindow = PopupWindow(
                                row,
                                subButtons.size * 250,
                                400
                        )
                        popupWindow?.elevation = 5.0f
                        popupWindow?.isOutsideTouchable = true
                        popupWindow?.showAsDropDown(this.list[i])
                        true
                    })
//                    this.list[i].setOnTouchListener(object: OnTouchListener{
//
//                        override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
//                            val detector = GestureDetector(object: GestureDetector.SimpleOnGestureListener(){
//                                override fun onLongPress(p1: MotionEvent?){
//
//                                    if (p1?.action == MotionEvent.ACTION_UP){
//                                        popupWindow?.dismiss()
//                                        popupWindow = null
//                                    }else if (p1?.action == MotionEvent.ACTION_DOWN){
//                                        popupWindow?.showAsDropDown(p0)
//                                    }
//                                }
//
//                                override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
//                                    callBack?.buttonClicked(list[i])
//                                    return true
//                                }
//                            })
//                            return detector.onTouchEvent(p1)
//                        }
//
//                    })
                }
            }
        }
    }

    fun setButtons(amount: Int){
        setButtons(amount,R.color.colorPrimaryDark)
    }
    fun setButtons(amount: Int, color: Int){
        if (amount == 0 || !list.isEmpty()){
            Log.e(TAG, "An empty row inserted")
            return
        }
        for (i in 0 until amount){
            val button = MathButtonView(context)
//            button.setBackgroundColor(Color.argb(0,0,0,0))
            button.setTextColor(color)
            if (amount >= 6){
                button.setTextSize(TypedValue.COMPLEX_UNIT_SP,18.0f)
            }else{
                button.setTextSize(TypedValue.COMPLEX_UNIT_SP,22.0f)
            }
            list.add(button)
            addView(button,getButtonParams())
        }
    }

    val horizontalParams = LinearLayout.LayoutParams(0,LayoutParams.MATCH_PARENT,1.0f)
    val verticalParams = LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,0,1.0f)

    fun getButtonParams() : LayoutParams{
        if (orientation == LinearLayout.HORIZONTAL){
            return horizontalParams
        }else{
            return verticalParams
        }
    }


}