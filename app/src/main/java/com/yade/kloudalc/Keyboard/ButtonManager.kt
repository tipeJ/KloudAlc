package com.yade.kloudalc.Keyboard

import org.mariuszgromada.math.mxparser.mathcollection.MathFunctions


object ButtonManager {
    val operators = arrayOf("+","-","*","/",".",",","^")

    val longlist = listOf<Long>(-1,-2,-3)

    fun getButtons(): ArrayList<ArrayList<ActionButton>>{
        val rows = ArrayList<ArrayList<ActionButton>>()

        rows.addAll(getMainButtons())
        rows.addAll(getFunButtons())

        return rows
    }
    val equalsButton = SpecButton("=")
    fun getMainButtons(): ArrayList<ArrayList<ActionButton>>{
        val r1 = ArrayList<ActionButton>()
        r1.add(NumButton("7"))
        r1.add(NumButton("4"))
        r1.add(NumButton("1"))
        r1.add(NumButton(".", NumButton(",")))

        val r2 = ArrayList<ActionButton>()
        r2.add(NumButton("8"))
        r2.add(NumButton("5"))
        r2.add(NumButton("2"))
        r2.add(NumButton("0"))

        val r3 = ArrayList<ActionButton>()
        r3.add(NumButton("9"))
        r3.add(NumButton("6"))
        r3.add(NumButton("3"))

        r3.add(equalsButton)

        val r4 = ArrayList<ActionButton>()
        val xsub = ArrayList<ActionButton>()
        xsub.add(OperButton("x²","^2","^2"))
        xsub.add(OperButton("x³","^3","^3"))
        xsub.add(OperButton("x^y","^","^"))
        r4.add(OperButton("*",xsub))

        r4.add(NumButton("/"))
        r4.add(NumButton("+"))
        r4.add(NumButton("-"))
        r4.add(FuncButton("√", "sqrt"))

        val rows = ArrayList<ArrayList<ActionButton>>()
        rows.add(r1)
        rows.add(r2)
        rows.add(r3)
        rows.add(r4)

        return rows
    }
    fun getAllFunButtons() : ArrayList<ActionButton>{
        val buttons = ArrayList<ActionButton>()
        getFunButtons().forEach { but ->
            buttons.addAll(but)
        }
        return buttons
    }

    fun getFunButtons(): ArrayList<ArrayList<ActionButton>>{
        val r1 = ArrayList<ActionButton>()
        r1.add(OperButton("x²","^2","²"))
        r1.add(OperButton("x³","^3","³"))
        r1.add(NumButton("^"))
        r1.add(OperButton("e^x","e^","e^"))
        r1.add(OperButton("10^x","10^","10^"))

        val r2 = ArrayList<ActionButton>()
        r2.add(FuncButton("log"))
        r2.add(FuncButton("log¹⁰","log"))
        r2.add(NumButton("%"))
        r2.add(FuncButton("ABS","abs"))
        r2.add(NumButton(","))

        val r3 = ArrayList<ActionButton>()
        r3.add(FuncButton("sin"))
        r3.add(FuncButton("cos"))
        r3.add(FuncButton("tan"))
        r3.add(FuncButton("min"))
        r3.add(FuncButton("RND"))

        val r4 = ArrayList<ActionButton>()
        r4.add(FuncButton("asin"))
        r4.add(FuncButton("acos"))
        r4.add(FuncButton("atan"))
        r4.add(FuncButton("max"))
        r4.add(FuncButton("FLR","floor"))

        val r5 = ArrayList<ActionButton>()
        r5.add(FuncButton("sinh"))
        r5.add(FuncButton("cosh"))
        r5.add(FuncButton("tanh"))
        val rsub = ArrayList<ActionButton>()
        rsub.add(FuncButton("RDM (UCD)","rUni","rUni"))
        rsub.add(FuncButton("RDM (UDD)","rUnid","rUnid"))
        rsub.add(FuncButton("RDM (Normal)","rNor","rNor"))
        rsub.add(FuncButton("RDM (List)","rList","rList"))
        r5.add(OperButton("RDM","rUni(0,1)",rsub))
        r5.add(FuncButton("CLG","ceil"))

        //Bonus Trigonometric functions:
        val r6 = ArrayList<ActionButton>()
        r6.add(FuncButton("cot"))
        r6.add(FuncButton("sec"))
        r6.add(FuncButton("csc","cosec"))
        r6.add(FuncButton("deg"))
        r6.add(FuncButton("rad"))

        //Primes
        val r7 = ArrayList<ActionButton>()
        r7.add(FuncButton("IsP","ispr"))
        r7.add(FuncButton("π(n)","Pi","π"))
        r7.add(FuncButton("Solve","solve","Solve"))
        r7.add(FuncButton("Bell"))
        r7.add(FuncButton("harm"))
        r7.add(FuncButton("ulp"))

        //TestUnits
        val r8 = ArrayList<ActionButton>()
        /*
        r7.addAll(arrayListOf(
                UnitButton("m","[m]","m", arrayListOf(
                        UnitButton("cm","[cm]","cm"),
                        UnitButton("mm","[mm]","mm"),
                        UnitButton("km","[km]","km"),
                        UnitButton("ft","[ft]","ft")
                )),
                UnitButton("m/s","[m/s]","m/s", arrayListOf(
                        UnitButton("km/h","[km/h]","km/h"),
                        UnitButton("mi/h","[mi/h]","mi/h")
                )),
                UnitButton("g","[gr]","g", arrayListOf(
                        UnitButton("kg","[kg]","kg"),
                        UnitButton("mg","[mg]","mg")
                ))
        ))
        */

        val rows = ArrayList<ArrayList<ActionButton>>()
        rows.addAll(arrayListOf(
                r1,r2,r3,r4,r5,r6,r7
        ))

        return rows
    }
    fun getTopRowButtons(): ArrayList<ActionButton>{
        val r = ArrayList<ActionButton>()
        r.add(NumButton("("))
        r.add(NumButton(")"))
        r.add(SpecButton("DEL",SpecButton("CLR")))

        return r
    }
    fun getConstants(): ArrayList<ArrayList<Constant>>{

        val MathConstants = ArrayList<Constant>()
        MathConstants.addAll(arrayListOf(
                Constant("gam","[gam]","Euler-Mascheroni constant"),
                Constant("π","pi","Pi, Archimedes' constant or Ludolph's number"),
                Constant("e","Napier's constant, or Euler's number, base of Natural logarithm"),
                Constant("Φ","[phi]","Golden ratio"),
                Constant("p","[PN]","Plastic number"),
                Constant("B*","[B*]","Embree-Trefethen constant"),
                Constant("F'd","[F'd]","Feigenbaum constant alfa"),
                Constant("F'a","[F'a]","Feigenbaum constant delta")
        ))

        val PhysicsConstants = ArrayList<Constant>()
        PhysicsConstants.addAll(arrayListOf(
                Constant("h","6.626070040 * 10^-34","Planck constant"),
                Constant("ly","[ly]","Light year"),
                Constant("au","[au]","Astronomical unit"),
                Constant("pc","[pc]","Parsec")
        ))

        val ChemistryConstants = ArrayList<Constant>()
        ChemistryConstants.addAll(arrayListOf(
                Constant("Na","6.02214 * 10^23","Avogadro's number"),
                Constant("Vm","22.41","Molar volume"),
                Constant("F","96485","Faraday constant"),
                Constant("u","1.661 * 10^-27","Atom mass unit")
        ))

        val rows = ArrayList<ArrayList<Constant>>()
        rows.add(MathConstants)
        rows.add(PhysicsConstants)
        rows.add(ChemistryConstants)

        var i = 0
        rows.forEach(action = {
            it.forEach(action = {
                it.id = i.toLong()
                i++
            })
        })
        return rows
    }
}