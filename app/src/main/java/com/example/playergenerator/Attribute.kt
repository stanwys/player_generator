package com.example.playergenerator

import kotlin.math.min

class Attribute(attributeName : String, startVal : Int) {
    val name = attributeName
    private var startValue = startVal
    private var newValue = startVal
    private var improvePoints = 0

    fun getStartValue():Int = startValue

    fun getNewValue():Int = newValue

    fun getImprovePoints():Int = improvePoints

    fun setStartValue(value : Int){
        this.startValue = value
    }

    fun setNewValue(value: Int){
        this.newValue = value
    }

    fun setNewValueBasedOnImprovPoints(){
        this.newValue = min(95,this.startValue + this.improvePoints/100)
    }

    fun increaseImprovePoints(value: Int){
        this.improvePoints += value
    }

    fun decreaseImprovePoints(value: Int){
        this.improvePoints -= value
    }

    fun setImprovePoints(value: Int){
        this.improvePoints = value
    }
}
