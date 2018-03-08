package c.ebookfrenzy.colorpicker

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.*
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

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
            savedColors.add(customColor(red, green, blue, colorName))
        }
    }
}