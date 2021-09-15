package com.example.playergenerator

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    var improveAreasValues = MutableLiveData<MutableMap<String,Int>>()//mutableListOf<Int>(2,2,2,2,2,2)
    private var playedSeasons = MutableLiveData<Int>()
    private var leftImprovPoints = MutableLiveData<Int>()
    private var player  = MutableLiveData<Player>()
    init{
        improveAreasValues.value = Constants.improveAreas.map {
            it to 2
        }.toMap().toMutableMap()
        leftImprovPoints.value = 0
        playedSeasons.value = 0
        player.value = Player("default", "DEFAULT",17,180,70,"R","France",
            "Centre Forward","N/A","A")
    }

    fun getPlayedSeasons() : MutableLiveData<Int> = playedSeasons

    fun calculatePlayerAge(){
        player.value!!.setAgeBasedOnPlayedSeasons(playedSeasons.value!!)
    }

    fun increasePlayedSeasons(){
        playedSeasons.value?.let { a ->
            playedSeasons.value = a + 1
        }
    }

    fun decreasePlayedSeasons(){
        playedSeasons.value?.let { a ->
            playedSeasons.value = a - 1
        }
    }

    fun increaseImprovePoint(key : String){
        val oldValue = improveAreasValues.value!!.getValue(key)
        improveAreasValues.value!!.put(key, oldValue + 1)
    }

    fun decreaseImprovePoint(key : String){
        val oldValue = improveAreasValues.value!!.getValue(key)
        improveAreasValues.value!!.put(key, oldValue - 1)
    }

    //fun getImprovePoint(index : Int ) : MutableLiveData<Int> = improveAreasValues[index]

    fun getImprovePoints(): MutableLiveData<MutableMap<String,Int>> = improveAreasValues

    fun getLeftImprovePoints() : MutableLiveData<Int> = leftImprovPoints

    fun increaseLeftImprovePoint(){
        leftImprovPoints.value?.let { a ->
            leftImprovPoints.value = a + 1
        }
    }

    fun decreaseLeftImprovePoint(){
        leftImprovPoints.value?.let { a ->
            leftImprovPoints.value = a - 1
        }
    }

    fun setPlayer(newPlayer : Player){
        player.value = newPlayer
    }

    fun getPlayer() : MutableLiveData<Player> = player
}