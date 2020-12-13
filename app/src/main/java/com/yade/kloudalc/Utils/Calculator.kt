package com.yade.kloudalc.Utils

import com.yade.kloudalc.Keyboard.ActionButton
import com.yade.kloudalc.Keyboard.NumButton
import org.mariuszgromada.math.mxparser.Expression
import org.mariuszgromada.math.mxparser.Function
import java.math.BigDecimal

/**
 * Created by Tiitus on 8.1.2018.
 */
object Calculator {
    val ERROR_STRING = "SYNTAX ERROR"

    val floor = Function("f(x) = ")

    fun Calculate(buttons: ArrayList<ActionButton>): String {
        if (buttons.isEmpty()) {
            return ""
        }
        val builder = StringBuilder()

        buttons.forEach { b -> builder.append(b.getInside()) }

        val eval = Expression(builder.toString())
        try {
            val result = eval.calculate()
            return "" + result
        } catch (e: Exception) {
            return ERROR_STRING
        }
    }

    fun calculateDecimal(buttons: ArrayList<ActionButton>): BigDecimal {
        val result = Calculate(buttons)
        return if (result == ERROR_STRING) BigDecimal.ZERO else result.toBigDecimal()
    }

    fun Convert(type: String, from: String, to: String, data: ArrayList<ActionButton>): String {
        val input = Calculate(data)
        if (input == ERROR_STRING){
            return ""
        }
        when(type){
            "distance" -> {
                val cInput = Calculate(arrayListOf(NumButton(input), NumButton("*"), NumButton(cUnits.distanceMap[from].toString())))
                return Calculate(arrayListOf(NumButton(cInput), NumButton("/"), NumButton(cUnits.distanceMap[to].toString())))
            }
        }
        return ""
    }
    object cUnits {
        val unitPrefix = "KloudAlc.Units.Unit"
        val typePrefix = "KloudAlc.Units.Type"

        val distance = "$typePrefix.distance"

        val distanceMap = HashMap<String,Double>()
        val massMap = HashMap<String,Double>()
        init {

            distanceMap.put("Kilometre",1000.0)
            distanceMap.put("Metre",1.0)
            distanceMap.put("Decimetre",0.1)
            distanceMap.put("Centimetre",0.01)
            distanceMap.put("Millimetre",0.001)
            distanceMap.put("Micrometre",0.000001)
            distanceMap.put("Nanometre",0.000000001)

            distanceMap.put("Foot",0.3048)
            distanceMap.put("Mile",1609.34)
            distanceMap.put("Yard",0.9144)
            distanceMap.put("Inch",0.0254)


            massMap.put("Tonne",0.001)
            massMap.put("Kilogram",1.0)
            massMap.put("Gram",1000.0)
            massMap.put("Milligram",0.000001)
            massMap.put("Microgram",0.000000001)

            massMap.put("Imperial Ton",0.000984207)
            massMap.put("US Ton",0.00110231)
            massMap.put("Stone",0.157473)
            massMap.put("Pound",2.20462)
            massMap.put("Ounce",35.274)
        }

        val mass = "$typePrefix.mass"
        val speed = "$typePrefix.speed"
        val acceleration = "$typePrefix.acceleration"
    }
}