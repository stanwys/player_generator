package com.example.playergenerator

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity;
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SpinnerAdapter
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_generate_player.*
import kotlinx.android.synthetic.main.content_generate_player.*
import kotlinx.android.synthetic.main.content_main.*

class GeneratePlayer : AppCompatActivity() {

    companion object {
        val positionWithSide = listOf<String>(
            "Wing Forward",
            "Side Midfielder",
            "Wing Back",
            "Side Back"
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generate_player)
        setSupportActionBar(toolbar)
        //val listAdapter = AttributesAdapter(this, R.layout.adapter_attribute_item,)
        setSideSpinner(calculateSideOptions(positionSpinner.selectedItem.toString()))

        positionSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Log.e("item_selected ","itemselected $position ${parent?.id} $id ")
                if(parent?.getId() == R.id.positionSpinner) {
                    Log.e("item_selected ", "${positionSpinner.selectedItem.toString()}")
                    setSideSpinner(calculateSideOptions(positionSpinner.selectedItem.toString()))
                }
            }
        }

        confirmButton.setOnClickListener { view ->
            //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show()
            // updateActiveProjects(theList)
            val inT = Intent(this,GeneratePlayer3::class.java)
            val player = getPlayerFromFields()
            if(isDataValid(player, view)){
                inT.putExtra("player", player)
                startActivity(inT)
            }
        }

       /* if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                1)
        }*/

    }

    fun getPlayerFromFields():Player{
        val name = nameEditText.text.toString()
        val shirtName = shirtNameEditText.text.toString().toUpperCase()
        val height = heightEditText.text.toString().toIntOrNull(10) ?: -1
        val weight = weightEditText.text.toString().toIntOrNull(10) ?: -1
        val foot = footSpinner.selectedItem.toString()
        val nationality = nationalitySpinner.selectedItem.toString()
        val position = positionSpinner.selectedItem.toString()
        val side = sideSpinner.selectedItem.toString()
        val injury = injurySpinner.selectedItem.toString()
        return Player(name,shirtName,17,weight,height,foot,nationality,position,side,injury)
    }

    fun isDataValid(player : Player, view : View):Boolean{
        if (player.height == -1 || player.height < 148 || player.height > 205){
            Toast.makeText(view.context, "Height must be a number between 148 and 205", Snackbar.LENGTH_LONG).show()
            return false
        }
        if (player.weight == -1 || player.weight < 35 || player.weight > 125){
            Toast.makeText(view.context, "Weight must be a number between 35 and 125", Snackbar.LENGTH_LONG).show()
            return false
        }
        if (player.name.length == 0 || player.shirtName.length == 0){
            Toast.makeText(view.context, "Name can't be empty", Snackbar.LENGTH_LONG).show()
            return false
        }
        return true
    }


    fun setSideSpinner(items : List<String>){
        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        //aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sideSpinner!!.adapter = aa as SpinnerAdapter?
    }

    fun calculateSideOptions(value : String): List<String>{
        return if (/*positionSpinner.selectedItem*/value in positionWithSide)
            listOf<String>("Both","Right","Left")
        else
            listOf<String>("N/A")
    }
}
