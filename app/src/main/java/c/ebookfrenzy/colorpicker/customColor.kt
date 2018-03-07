package c.ebookfrenzy.colorpicker

data class customColor(var red:Int, var green:Int, var blue:Int, var name:String)
{
    override fun toString(): String = "$name $red $green $blue"
}