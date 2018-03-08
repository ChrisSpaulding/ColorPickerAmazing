package c.ebookfrenzy.colorpicker

import java.io.Serializable

data class customColor(var red:Int, var green:Int, var blue:Int, var name:String) : Serializable
{
    override fun toString(): String = "$name $red $green $blue"
}