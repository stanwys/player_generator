package com.example.playergenerator


import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_generate_player2.*
import android.util.Log
import android.widget.ListView
import android.widget.Toast
import kotlinx.android.synthetic.main.played_seasons_layout.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class Attributes : Fragment() {

    var textViews = emptyList<TextView>()
    private lateinit var viewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_generate_player2, container, false)
    }

    override fun onViewCreated(view : View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(activity!!).get(SharedViewModel::class.java!!)

        val attrList = view.findViewById<ListView>(R.id.attributeListView)
        setAdapter(attrList)

        playedSeasonsValueText.setText(viewModel.getPlayedSeasons().value.toString())

        nextSeasonButton.setOnClickListener { view ->
            if (viewModel.getPlayedSeasons().value!! < 14 ){
                if (isValidNumberGamesPlayed()) {
                    viewModel.increasePlayedSeasons()
                    playedSeasonsValueText.setText(viewModel.getPlayedSeasons().value.toString())

                    //calculate, modify player in viewModel and set adapter
                    viewModel.getPlayer().value!!.addImprovePoints(
                        viewModel.getImprovePoints().value!!,
                        gamesPlayedEditText.text.toString().toInt(),
                        viewModel.getPlayedSeasons().value!!
                    )

                    setAdapter(attrList)
                }
                else{
                    Toast.makeText(view.context, "Number of games played can't be less than 40", Toast.LENGTH_LONG).show()
                }
            }
        }

        prevSeasonButton.setOnClickListener { view ->
            if (viewModel.getPlayedSeasons().value!! > 0 ){
                viewModel.decreasePlayedSeasons()
                playedSeasonsValueText.setText(viewModel.getPlayedSeasons().value.toString())
                viewModel.getPlayer().value!!.subtractImprovePoints()
                setAdapter(attrList)
            }
        }
    }

    fun isValidNumberGamesPlayed() : Boolean = (gamesPlayedEditText.text.toString().length > 0
            && gamesPlayedEditText.text.toString().toInt() >= 40)

    fun setAdapter(listView: ListView){
        val listAdapter1 = AttributesAdapter(
            activity!!,//this!!.context!! ,
            R.layout.attribute_item,
            ArrayList(viewModel.getPlayer().value!!.attributeMap.values)
        )
        listView.adapter = listAdapter1
    }



}
