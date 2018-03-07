package com.example.android.number2

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.transition.Slide
import android.transition.TransitionManager
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import c.ebookfrenzy.colorpicker.R
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.popup_layout.*


class MainActivity : AppCompatActivity() {
    val alpha = 255
    var red = 0
    var blue = 0
    var green = 0
    var savedColors : ArrayList<customColor> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.my_toolbar))
        header_image.alpha = 1f

        colorArea.setBackgroundColor(Color.argb(alpha,red,green,blue))

        val redSeekBar = findViewById<SeekBar>(R.id.redBar)
        redSeekBar.max = alpha
        redNumber.text = red.toString()

        val blueSeekBar = findViewById<SeekBar>(R.id.blueBar)
        blueSeekBar.max = alpha
        blueNumber.text = blue.toString()

        val greenSeekBar = findViewById<SeekBar>(R.id.greenBar)
        greenSeekBar.max = alpha
        greenNumber.text = green.toString()

        setSeekBarListeners(redSeekBar)
        setSeekBarListeners(greenSeekBar)
        setSeekBarListeners(blueSeekBar)
    }

    override fun onStop(){
        super.onStop()
    }

    private fun setSeekBarListeners(seekBar: SeekBar) {
        seekBar?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {}
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                when(seekBar!!.id) {
                    R.id.redBar -> {
                        red = seekBar!!.progress
                        redNumber.text = red.toString()
                    }
                    R.id.greenBar ->{
                        green = seekBar!!.progress
                        greenNumber.text = green.toString()
                    }
                    R.id.blueBar -> {
                        blue = seekBar.progress
                        blueNumber.text = blue.toString()
                    }
                }
                colorArea.setBackgroundColor(Color.argb(alpha, red, green, blue))
            }
        })
    }

    fun saveOnClick(view: View){
        Log.i("saveOnClick","Save button Clicked")
        val colorName:String = colorNameField.text.toString()
        if (colorName == "Name")
            Toast.makeText(view.context, "Please Select a Name", Toast.LENGTH_SHORT).show()
        else {
            Toast.makeText(view.context, "Saving The Color $red $green $blue $colorName", Toast.LENGTH_LONG).show()
            savedColors.add(customColor(red, green, blue, colorName))
            Log.i("toStringColor",savedColors.get(savedColors.size-1).toString())
        }
    }

    fun restoreOnClick(view:View){
        Log.i("restoreOnClick", "Restore Button Clicked")
        for (color in savedColors)
            Log.i("listColors", "$color")

        // Initialize a new layout inflater instance
        val inflater: LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        // Inflate a custom view using layout inflater
        val view = inflater.inflate(R.layout.popup_layout,null)

        // Initialize a new instance of popup window
        val popupWindow = PopupWindow(
                view, // Custom view to show in popup window
                LinearLayout.LayoutParams.WRAP_CONTENT, // Width of popup window
                LinearLayout.LayoutParams.WRAP_CONTENT // Window height
        )

        // Set an elevation for the popup window
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            popupWindow.elevation = 10.0F
        }

        // If API level 23 or higher then execute the code
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            // Create a new slide animation for popup window enter transition
            val slideIn = Slide()
            slideIn.slideEdge = Gravity.TOP
            popupWindow.enterTransition = slideIn

            // Slide animation for popup window exit transition
            val slideOut = Slide()
            slideOut.slideEdge = Gravity.RIGHT
            popupWindow.exitTransition = slideOut
        }

        // Get the widgets reference from custom view
        val buttonPopup = view.findViewById<Button>(R.id.button_popup)

        var stringSavedColors = ArrayList<String>()
        for (color in savedColors)
            stringSavedColors.add(color.toString())

        //hello world

        if(!savedColors.isEmpty()){
            val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stringSavedColors)
            list_view.adapter = adapter
        }

        // Set a click listener for popup's button widget
        buttonPopup.setOnClickListener{
            // Dismiss the popup window
            popupWindow.dismiss()
        }

        // Set a dismiss listener for popup window
        popupWindow.setOnDismissListener {
            Toast.makeText(applicationContext,"Popup closed",Toast.LENGTH_SHORT).show()
        }

        // Finally, show the popup window on app
        TransitionManager.beginDelayedTransition(root_layout)
        popupWindow.showAtLocation(
                root_layout, // Location to display popup window
                Gravity.CENTER, // Exact position of layout to display popup
                0, // X offset
                0 // Y offset
        )
    }
}
