package com.example.playergenerator

import android.arch.lifecycle.ViewModelProviders
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.os.Bundle
import android.os.Environment
import android.os.Environment.getExternalStorageDirectory
import android.os.Environment.getExternalStorageState
import android.os.storage.StorageVolume
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_generete_player3.*
import org.w3c.dom.Document
import org.w3c.dom.Element
import java.io.File
import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.OutputKeys
import javax.xml.transform.Transformer
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult

class GeneratePlayer3 : AppCompatActivity() {

    /**
     * The [android.support.v4.view.PagerAdapter] that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * [android.support.v4.app.FragmentStatePagerAdapter].
     */
    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null
    private lateinit var viewModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generete_player3)

        setSupportActionBar(toolbar)
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

        // Set up the ViewPager with the sections adapter.
        container.adapter = mSectionsPagerAdapter

        container.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(container))

        viewModel = ViewModelProviders.of(this).get(SharedViewModel::class.java!!)
        val player=intent.getParcelableExtra<Player>("player")
        viewModel.setPlayer(player)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_generate_player3, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        if (id == R.id.export_player_action) {
            viewModel.calculatePlayerAge()
            exportPlayerXMLFile()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    /**
     * A [FragmentPagerAdapter] that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
           // return PlaceholderFragment.newInstance(position + 1)
            when(position){
                0 -> return Attributes()
                1 -> return Strengthen()
                else -> return Attributes()
            }
        }

        override fun getCount(): Int {
            return 2
        }
    }

    fun exportPlayerXMLFile(){

        val docBuilder: DocumentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder()
        val doc: Document = docBuilder.newDocument()

        val rootElement: Element = doc.createElement("PES_Player")
        val player = viewModel.getPlayer().value!!

        val listOfElements = mutableListOf<Element>()
        var element = doc.createElement("PlayerName")
        element.appendChild(doc.createTextNode(player.name))
        listOfElements.add(element)

        element = doc.createElement("ShirtName")
        element.appendChild(doc.createTextNode(player.shirtName))
        listOfElements.add(element)

        element = doc.createElement("RegisteredPosition")
        element.appendChild(doc.createTextNode(Constants.positionToAbbrevation.getValue(player.position)))
        listOfElements.add(element)

        element = doc.createElement("OptionalPositions")
        element.appendChild(doc.createTextNode(Constants.positionToAbbrevation.getValue(player.position)))
        listOfElements.add(element)

        element = doc.createElement("Nationality")
        element.appendChild(doc.createTextNode(player.nationality))
        listOfElements.add(element)

        element = doc.createElement("Age")
        element.appendChild(doc.createTextNode(player.age.toString()))
        listOfElements.add(element)

        element = doc.createElement("Foot")
        element.appendChild(doc.createTextNode(player.betterFoot))
        listOfElements.add(element)

        element = doc.createElement("FavoriteSide")
        element.appendChild(doc.createTextNode(player.side))
        listOfElements.add(element)

        element = doc.createElement("Height")
        element.appendChild(doc.createTextNode(player.height.toString()))
        listOfElements.add(element)

        element = doc.createElement("Weight")
        element.appendChild(doc.createTextNode(player.weight.toString()))
        listOfElements.add(element)

        element = doc.createElement("InjuryTolerance")
        element.appendChild(doc.createTextNode(player.injuryTolerance))
        listOfElements.add(element)

        element = doc.createElement("Condition")
        element.appendChild(doc.createTextNode("4"))
        listOfElements.add(element)

        element = doc.createElement("WeakFootAccuracy")
        element.appendChild(doc.createTextNode("4"))
        listOfElements.add(element)

        element = doc.createElement("WeakFootFrequency")
        element.appendChild(doc.createTextNode("4"))
        listOfElements.add(element)

        listOfElements.map{
            rootElement.appendChild(it)
        }

        val abilitiesNode = doc.createElement("Abilities")

        player.attributeMap.map{
            val attribute = doc.createElement(it.key)
            attribute.appendChild(doc.createTextNode(it.value.getNewValue().toString()))
            abilitiesNode.appendChild(attribute)
        }

        rootElement.appendChild(abilitiesNode)

        val specialAbilitiesNode = doc.createElement("SpecialAbilities")
        Constants.specialAbilitiesList.map{
            val attribute =  doc.createElement(it)
            attribute.appendChild(doc.createTextNode("False"))
            specialAbilitiesNode.appendChild(attribute)
        }

        rootElement.appendChild(specialAbilitiesNode)

        val motionNode = doc.createElement("Motion")
        Constants.motionSettingsList.map{
            val motion = doc.createElement(it)
            motion.appendChild(doc.createTextNode("1"))
            motionNode.appendChild(motion)
        }

        rootElement.appendChild(motionNode)
        doc.appendChild(rootElement)

        val transformer: Transformer = TransformerFactory.newInstance().newTransformer()

        transformer.setOutputProperty(OutputKeys.INDENT,"yes")
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount","2")

        val path = "storage/self/primary"
        val outDir= File(path,"PlayerGenerator")
        if(!outDir.exists()){
            val isSuccess = outDir.mkdirs()
            Log.e("tag","${isSuccess}")
        }
        val file=File(outDir,"player_${player.name}.xml")
        transformer.transform(DOMSource(doc), StreamResult(file))
        Toast.makeText(this, "player_${player.name}.xml has been created!", Toast.LENGTH_LONG).show()
    }
}
