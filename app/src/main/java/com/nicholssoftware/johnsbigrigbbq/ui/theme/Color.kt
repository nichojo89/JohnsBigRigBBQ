package com.nicholssoftware.johnsbigrigbbq.ui.theme

import androidx.compose.ui.graphics.Color

val Red = "#eb3423".color
val LightRed = "#ff6c4e".color
val DarkRed = "#b00000".color
val Orange = "#ff7817".color
val OrangeLight = "#ffa94c".color
val OrangeDark = "#c54800".color

val String.color
    get() = Color(android.graphics.Color.parseColor(this))