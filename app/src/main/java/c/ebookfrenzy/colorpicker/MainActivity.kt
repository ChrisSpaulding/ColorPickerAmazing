package c.ebookfrenzy.colorpicker

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.*
import android.widget.*
import io.reactivex.Single
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

/*
*   TO DO: Have both able to open independently
*   Act differently based on who opened it
*   Create 2 apps
*   Save Data across the universe
*   Just give up inside
*/

class MainActivity : AppCompatActivity() {
    val alpha = 255
    var red = 0
    var blue = 0
    var green = 0
    private var savedColors: ArrayList<customColor> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.my_toolbar))
        header_image.alpha = 1f

        colorArea.setBackgroundColor(Color.argb(alpha, red, green, blue))

        val redSeekBar = findViewById<SeekBar>(R.id.redBar)
        redSeekBar.max = alpha
        redNumber.text = red.toString()

        val blueSeekBar = findViewById<SeekBar>(R.id.blueBar)
        blueSeekBar.max = alpha
        blueNumber.text = blue.toString()

        val greenSeekBar = findViewById<SeekBar>(R.id.greenBar)
        greenSeekBar.max = alpha
        greenNumber.text = green.toString()

        var redOne= intent.getIntExtra("redOne",0)
        var greenOne= intent.getIntExtra("greenOne", 0)
        var blueOne= intent.getIntExtra("blueOne", 0)
        var redTwo= intent.getIntExtra("redTwo",0)
        var greenTwo= intent.getIntExtra("greenTwo", 0)
        var blueTwo= intent.getIntExtra("blueTwo", 0)

        chooseColor.setOnClickListener {
            if (1 == intent.getIntExtra("button",0)) {
                redOne = red
                greenOne = green
                blueOne = blue
            } else {
                redTwo = red
                greenTwo = green
                blueTwo = blue
            }
            val intent = Intent(this, theClient::class.java)
            intent.putExtra("redOne", redOne)
            intent.putExtra("greenOne", greenOne)
            intent.putExtra("blueOne", blueOne)
            intent.putExtra("redTwo", redTwo)
            intent.putExtra("greenTwo", greenTwo)
            intent.putExtra("blueTwo", blueTwo)
            startActivity(intent)
        }
        setSeekBarListeners(redSeekBar)
        setSeekBarListeners(greenSeekBar)
        setSeekBarListeners(blueSeekBar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        var id = item!!.title
        Log.i("Title", "The title is ;$id")
        for (color in savedColors) {
            if(color.name == id){
                red = color.red
                blue = color.blue
                green = color.green
                setColor(id)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        //add the color to the menu
        (1..8)
                .filter { it <= savedColors.size }
                .forEach { menu.getItem(it -1).title = savedColors[it -1].name }
        return super.onPrepareOptionsMenu(menu)
    }

    private fun setColor(name :String){
        redBar.progress = red
        redNumber.text = red.toString()

        blueBar.progress = blue
        blueNumber.text = blue.toString()

        greenBar.progress = green
        greenNumber.text = green.toString()

        colorNameField.setText(name)

        colorArea.setBackgroundColor(Color.argb(alpha,red,green,blue))
    }

    private fun setSeekBarListeners(seekBar: SeekBar) {
        seekBar?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {}
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                when (seekBar!!.id) {
                    R.id.redBar -> {
                        red = seekBar!!.progress
                        redNumber.text = red.toString()
                    }
                    R.id.greenBar -> {
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

    fun saveOnClick(view: View) {
        Log.i("saveOnClick", "Save button Clicked")
        val colorName: String = colorNameField.text.toString()
        if (colorName == "Name")
            Toast.makeText(view.context, "Please Select a Name", Toast.LENGTH_SHORT).show()
        else {
            Toast.makeText(view.context, "Saving The Color $red $green $blue $colorName", Toast.LENGTH_LONG).show()
            var colorToSave = customColor(red,green,blue, colorName)
            //add color to array
            savedColors.add(colorToSave)
            //add color to database
        }
    }

    fun saveColor(appDatabase: AppDatabase,insertColor:customColor){
        val insertColorEntity = ColorDataEntitiy(insertColor.name, insertColor.red,insertColor.green, insertColor.blue)
        appDatabase.colorDao().insert(colorDataEntity = insertColorEntity)
    }
}