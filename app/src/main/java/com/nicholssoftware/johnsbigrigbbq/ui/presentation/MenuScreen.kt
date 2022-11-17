package com.nicholssoftware.johnsbigrigbbq.ui.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true)
@Composable
fun MenuScreen(){
    Column() {
        Text(
            text = "Menu View",
            fontWeight = FontWeight.Bold,
        )
    }
}