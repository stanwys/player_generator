package com.example.playergenerator


import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_generete_player3.*
import kotlinx.android.synthetic.main.strengthen_layout.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class Strengthen : Fragment() {

    private lateinit var viewModel: SharedViewModel
    var plusButtons = emptyMap<String,Button>()
    var minusButtons = emptyMap<String,Button>()
    var textViews = emptyMap<String,TextView>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.strengthen_layout, container, false)
    }

    override fun onViewCreated(view : View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(activity!!).get(SharedViewModel::class.java!!)
        loadObjectsToMaps()
        prepareButtons()
    }

    override fun onStart() {
        super.onStart()
        viewModel.getImprovePoints().value!!.map{
            textViews[it.key]!!.setText(it.value.toString())
        }
    }

    fun prepareButtons(){
        this.plusButtons.map {
            it.value.setOnClickListener { view ->
                if (viewModel.getLeftImprovePoints().value!! > 0 && viewModel.getImprovePoints().value!!.getValue(it.key) < 6){
                    viewModel.increaseImprovePoint(it.key)
                    viewModel.decreaseLeftImprovePoint()
                    textViews[it.key]!!.setText(viewModel.getImprovePoints().value!!.getValue(it.key).toString())
                }
            }
        }
        this.minusButtons.map {
            it.value.setOnClickListener { view ->
                if (viewModel.getLeftImprovePoints().value!! < 12 && viewModel.getImprovePoints().value!!.getValue(it.key)  > 0){
                    viewModel.decreaseImprovePoint(it.key)
                    viewModel.increaseLeftImprovePoint()
                    textViews[it.key]!!.setText(viewModel.getImprovePoints().value!!.getValue(it.key).toString())
                }
            }
        }
    }

    fun loadObjectsToMaps(){
        this.plusButtons = mapOf(
            Constants.improveAreas[0] to teckickPlusButton,Constants.improveAreas[1] to tecdribPlusButton,
            Constants.improveAreas[2] to balPlusButton, Constants.improveAreas[3] to powerPlusButton,
            Constants.improveAreas[4] to speedPlusButton, Constants.improveAreas[5] to stamPlusButton
        )

        this.minusButtons = mapOf(
            Constants.improveAreas[0] to teckickMinusButton,Constants.improveAreas[1] to tecdribMinusButton,
            Constants.improveAreas[2] to balMinusButton, Constants.improveAreas[3] to powerMinusButton,
            Constants.improveAreas[4] to speedMinusButton, Constants.improveAreas[5] to stamMinusButton
        )

        this.textViews = mapOf(
            Constants.improveAreas[0] to teckickValueText,Constants.improveAreas[1] to tecdribValueText,
            Constants.improveAreas[2] to balValueText, Constants.improveAreas[3] to powerValueText,
            Constants.improveAreas[4] to speedValueText, Constants.improveAreas[5] to stamValueText
        )
    }

}
