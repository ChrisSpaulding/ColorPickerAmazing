package c.ebookfrenzy.colorpicker

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle

import android.support.v7.app.AppCompatActivity
import android.widget.SeekBar
import kotlinx.android.synthetic.main.client_layout.*

class theClient : AppCompatActivity() {

    val alpha = 255
    private val hundred = 100
    var redOne = 0
    var blueOne = 255
    var greenOne = 0
    var redTwo = 0
    var blueTwo = 0
    var greenTwo = 255
    var buttonChoice = "button"

    private val PICK_CONTACT_REQUEST = 1;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.client_layout)
        colorMixBar.max = hundred

        if("button1" == intent.getStringExtra(buttonChoice)){
            redOne= intent.getStringExtra(red.twoString()) //need to implement code
        }
        else{
            setColor2 //need to implement code
        }
        colorOne.setOnClickListener{

                val intent= Intent (this, MainActivity :: class.java)
                intent.putExtra(buttonChoice, "button1")
                startActivity(intent)

        }
        colorTwo.setOnClickListener{
            val intent= Intent (this, MainActivity :: class.java)
            intent.putExtra(buttonChoice, "button2")
            startActivity(intent)

        }

        colorMixBar?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {}
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                when (colorMixBar.progress) {
                    100 -> colorView.setBackgroundColor(Color.argb(alpha, redTwo, greenTwo, blueTwo))
                    0 -> colorView.setBackgroundColor(Color.argb(alpha, redOne, greenOne, blueOne))
                    else -> colorView.setBackgroundColor(Color.argb(alpha,
                            ((redTwo*colorMixBar.progress/100)+(redOne*(100-colorMixBar.progress)/100)),
                            ((greenTwo*colorMixBar.progress/100)+(greenOne*(100-colorMixBar.progress)/100)),
                            ((blueTwo*colorMixBar.progress/100)+(blueOne*(100-colorMixBar.progress)/100))))
                }
            }
        })
    }

    private fun chooseColor(){
        val pickColorIntent = Intent(Intent.ACTION_PICK, Uri.parse("content;//color"))
    }
}