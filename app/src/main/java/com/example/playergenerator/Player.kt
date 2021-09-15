package com.example.playergenerator

import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import java.security.SecureRandom
import kotlin.math.abs
import kotlin.math.floor
import kotlin.math.max
import kotlin.math.min

class Player (val name : String,
              val shirtName : String,
              var age : Int,
              val weight : Int,
              val height : Int,
              val betterFoot : String,
              val nationality : String,
              val position : String,
              val side : String,
              val injuryTolerance : String
              ) : Parcelable{

    var attributeMap = initializeAllAttributes()
    var improvePointsInSeasons = mutableListOf<Map<String,Int>>()

    companion object {
        @JvmField
        val CREATOR = object : Parcelable.Creator<Player> {
            override fun createFromParcel(parcel: Parcel) = Player(parcel)
            override fun newArray(size: Int) = arrayOfNulls<Player>(size)
        }

        val secureRandomGenerator = SecureRandom()
        val personalAttributes = listOf("Agility","Aggression","Mentality")

        fun calculateMaxGrowth(season: Int): Int{
            if (season < 3) return 12
            if (season < 6) return 9
            if (season < 10) return 6
            return 3
        }

        fun calculateEpochBasedOnSeason(season: Int) : Int{
            if (season < 3) return 1
            if (season < 6) return 3
            if (season < 10) return 6
            return 10
        }
    }

    private constructor(parcel: Parcel) : this(
        name = parcel.readString(),
        shirtName = parcel.readString(),
        age = parcel.readInt(),
        weight = parcel.readInt(),
        height = parcel.readInt(),
        betterFoot = parcel.readString(),
        nationality = parcel.readString(),
        position = parcel.readString(),
        side = parcel.readString(),
        injuryTolerance = parcel.readString()
    )

    private fun initializeAllAttributes(): Map<String, Attribute>{

        val initialValues = calculateInitialValues()
        val firstMap = Constants.attributesToImproveAreasMap.keys.zip(initialValues).map{
            it.first to Attribute(it.first,it.second)
        }.toMap().toMutableMap()
        personalAttributes.map{
            firstMap.put(it,Attribute(it,calculateAttributeValue(it)))
        }
        return firstMap
    }

    fun setAgeBasedOnPlayedSeasons(numSeasons : Int){
        this.age = 17 + max(numSeasons,1) - 1
    }

    fun calculateInitialValues(): List<Int>{
        Log.v("tage","${this.position}")
        return Constants.positionToMinValueMap.getValue(this.position).map {
            it.first + secureRandomGenerator.nextInt(it.second)
        }.toMutableList()
    }

    fun calculateAttributeValue(attribute : String): Int{
        var value = 50
        when(attribute){
            "Agility" -> value = calculateAttributeBasedOnPhysicality(attribute)
            "Mentality" -> value = 72 + secureRandomGenerator.nextInt(10)
            "Aggression" -> value = 69 + secureRandomGenerator.nextInt(17)
            else -> value = 50
        }
        return value
    }

    fun calculateAttributeBasedOnPhysicality(attribute: String):Int{
        var value = 50
        when(attribute){
            "Agility" -> value = calculateAgility()
            else -> value = 50
        }
        return value
    }

    fun calculateAgility(): Int{
        val bestHeight = this.weight + 105//max(this.height - 105,1)
        val distanceFromBest = (abs(bestHeight-this.height))/bestHeight.toDouble()
        val ratio = 1 - distanceFromBest
        var baseAgility = getAgilityBasedOnHeight() + secureRandomGenerator.nextInt(5)
        return min(max(floor(ratio*baseAgility).toInt(),67),89)
    }

    fun getAgilityBasedOnHeight():Int{
        if (this.height < 165) return 85
        else if (this.height < 170) return 83
        else if (this.height < 175) return 81
        else if (this.height < 180) return 79
        else if (this.height < 185) return 77
        else if (this.height < 190) return 75
        else return 72
    }

    fun calculateAttributeImprovePoints(attribute : String,
                                        improvePoints : Map<String,Int>,
                                        numGamesInSeason : Int,
                                        maxGrowth : Int,
                                        epoch : Int) : Int{
        var sumOfStrengthenPoints = Constants.attributesToImproveAreasMap[attribute]!!.map{
            Constants.seasonToImprovePointsMap
                .getValue(epoch)
                .getValue(improvePoints.getValue(it.key))
                .getValue(it.value)
        }.sum()
        sumOfStrengthenPoints = min(sumOfStrengthenPoints, maxGrowth)
        return numGamesInSeason*sumOfStrengthenPoints
    }

    fun addImprovePoints(improvePoints : Map<String,Int> , numGamesInSeason : Int ,season : Int){

        val maxGrowth = calculateMaxGrowth(season)
        val epoch = calculateEpochBasedOnSeason(season)
        val totalImprovePoints = Constants.attributesToImproveAreasMap.keys.map{
            it to calculateAttributeImprovePoints(it, improvePoints, numGamesInSeason, maxGrowth, epoch)
        }.toMap()

        totalImprovePoints.map{
            attributeMap[it.key]!!.increaseImprovePoints(it.value)
            attributeMap[it.key]!!.setNewValueBasedOnImprovPoints()
        }

        addImprovePointsInSeasons(totalImprovePoints)
    }

    fun subtractImprovePoints(){
        improvePointsInSeasons.last().map{
            attributeMap[it.key]!!.decreaseImprovePoints(it.value)
            attributeMap[it.key]!!.setNewValueBasedOnImprovPoints()
        }
        removeImprovePointsInSeasons()
    }

    fun addImprovePointsInSeasons(improvePointsMap : Map<String,Int>){
        improvePointsInSeasons.add(improvePointsMap)
    }

    fun removeImprovePointsInSeasons(){
        if (improvePointsInSeasons.size > 0){
            val lastIndex = improvePointsInSeasons.size - 1
            improvePointsInSeasons.removeAt(lastIndex)
        }
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(this.name)
        dest?.writeString(this.shirtName)
        dest?.writeInt(this.age)
        dest?.writeInt(this.weight)
        dest?.writeInt(this.height)
        dest?.writeString(this.betterFoot)
        dest?.writeString(this.nationality)
        dest?.writeString(this.position)
        dest?.writeString(this.side)
        dest?.writeString(this.injuryTolerance)
    }

}