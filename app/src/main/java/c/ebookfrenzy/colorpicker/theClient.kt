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
    var blueOne = 0
    var greenOne = 0
    var redTwo = 255
    var blueTwo = 255
    var greenTwo = 255
    var buttonChoice = "button"

    private val PICK_CONTACT_REQUEST = 1;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.client_layout)
        colorMixBar.max = hundred

        redOne=intent.getIntExtra("redOne",0)
        greenOne=intent.getIntExtra("greenOne",0)
        blueOne=intent.getIntExtra("blueOne",0)
        redTwo=intent.getIntExtra("redTwo",0)
        greenTwo=intent.getIntExtra("greenTwo",0)
        blueTwo=intent.getIntExtra("blueTwo",0)

        colorOne.setOnClickListener{
            val intent= Intent (this, MainActivity :: class.java)
            intent.putExtra("redOne",redOne)
            intent.putExtra("greenOne",greenOne)
            intent.putExtra("blueOne",blueOne)
            intent.putExtra("redTwo",redTwo)
            intent.putExtra("greenTwo",greenTwo)
            intent.putExtra("blueTwo",blueTwo)
            intent.putExtra("button", 1)
            startActivity(intent)
        }
        colorTwo.setOnClickListener{
            val intent= Intent (this, MainActivity :: class.java)
            intent.putExtra("redOne",redOne)
            intent.putExtra("greenOne",greenOne)
            intent.putExtra("blueOne",blueOne)
            intent.putExtra("redTwo",redTwo)
            intent.putExtra("greenTwo",greenTwo)
            intent.putExtra("blueTwo",blueTwo)
            intent.putExtra("button", 2)
            startActivity(intent)
        }

        colorView.setBackgroundColor(Color.argb(alpha,redOne,blueOne,greenOne))

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