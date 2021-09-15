package com.example.playergenerator

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import java.util.ArrayList
import android.R.attr.name
import android.graphics.Color
import android.widget.TextView
//import kotlinx.android.synthetic.main.strengthen_item.view.*

class StrengthenAdapter (private val mContext: Context, private val mResource: Int, objects: ArrayList<String>) :
    ArrayAdapter<String>(mContext, mResource, objects) {

    override fun getView(position: Int, convView: View?, parent: ViewGroup): View {

        var convertView = convView
        val element = getItem(position)

        if (convertView == null) {
            val inflater = LayoutInflater.from(mContext)
            convertView = inflater.inflate(mResource, parent, false)
        }

                /*convertView?.startValueTextView?.setText(element.getStartValue().toString())
        convertView?.newValueTextView?.setText(element.getNewValue().toString())
        convertView?.improvPointsTextView?.setText(element.getImprovePoints().toString())
        colourView(convertView!!,element)
*//*
        convertView?.plusButton?.setOnClickListener(View.OnClickListener {
            val oldValue = GeneratePlayer2.imporveAreasMap.getValue(element)
            if (GeneratePlayer2.leftImprovPoints > 0 && oldValue < 6){
                GeneratePlayer2.imporveAreasMap.put(element,oldValue+1)
                GeneratePlayer2.leftImprovPoints -= 1
            }
        })

        convertView?.minusButton?.setOnClickListener(View.OnClickListener {
            val oldValue = GeneratePlayer2.imporveAreasMap.getValue(element)
            if (GeneratePlayer2.leftImprovPoints < 12 && oldValue > 0){
                GeneratePlayer2.imporveAreasMap.put(element,oldValue-1)
                GeneratePlayer2.leftImprovPoints += 1
            }
        })

        convertView?.strengthenNameTextView?.setText(element)
        convertView?.strengthenValueTextView?.setText(GeneratePlayer2.imporveAreasMap.getValue(element).toString())
*/
        return convertView!!
    }

    /*
    fun colourView(convView: View, attribute: Attribute){
        colourTextView(convView.startValueTextView,attribute.getStartValue())
        colourTextView(convView.newValueTextView,attribute.getNewValue())
    }

    fun colourTextView( textView : TextView, value : Int){
        val values = listOf<Int>(75, 80, 90, 95)
        val colours = listOf<String>("#000000","#00FF00","#DFE52E","#FF7F00","#FF0000")
        var index = 0
        while (index < 4 && value >= values[index])index+=1
        textView.setTextColor(Color.parseColor(colours[index]))
    }*/

}