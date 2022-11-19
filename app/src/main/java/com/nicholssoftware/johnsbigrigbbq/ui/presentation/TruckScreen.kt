package com.nicholssoftware.johnsbigrigbbq.ui.presentation

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.*
import com.nicholssoftware.johnsbigrigbbq.R
import com.nicholssoftware.johnsbigrigbbq.ui.theme.Red

@Preview(showBackground = true)
@Composable
fun TruckScreen(){
    val context = LocalContext.current
    Column(modifier = Modifier.padding(50.dp,5.dp,50.dp,5.dp)) {
        Text(
            text = "We've located the nearest food truck!",
            fontWeight = FontWeight.Bold,
        )
        Text(
            modifier = Modifier.padding(0.dp,10.dp,0.dp,10.dp),
            text = "555 Sunshine lane \n" +
                    "Chesterfield MI, 48047"
        )

        MapScreen()

        Button(onClick = {
                         //Navigate to email
            val intent = Intent(Intent.ACTION_SEND)
            intent.putExtra(Intent.EXTRA_EMAIL, "bigjon@bigjonsBBQTruck.com")
            intent.putExtra(Intent.EXTRA_SUBJECT, "Food Order")
            intent.putExtra(Intent.EXTRA_TEXT, "1 brisket: \$16.00\n" +
                    "1 steak bite: \$12.00\n" +
                    "1 smoked rib: \$18.00\n" +
                    "1 smoked chicken: \$13.00\n" +
                    "Tip: 12.00\n" +
                    "Tax: \$10.00\n" +
                    "Total: \$81.00")
            intent.type = "message/rfc822"

            context.startActivity(Intent.createChooser(intent, "Select email"))
        },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
            , colors = ButtonDefaults.buttonColors(
                Red, Color.White
            )
            , shape = RoundedCornerShape(23.dp)
        ) {
            Text(text = "Send Order")
        }
    }
}


@Composable
fun MapScreen(){
    val context = LocalContext.current
    GoogleMap(modifier = Modifier.fillMaxWidth().height(250.dp),

        uiSettings = MapUiSettings(zoomControlsEnabled = true),

        cameraPositionState =  CameraPositionState(
            CameraPosition(
                LatLng(22.5726,88.3639),
                12f,
                0f,
                0f
            )
        )
    ){
        Marker(
            state = MarkerState(position = LatLng(22.5726,88.3638)),
            title = "Marker in Sydney",
            icon = BitmapDescriptorFactory.fromResource(R.drawable.ic_truck_red)
        )
    }

}