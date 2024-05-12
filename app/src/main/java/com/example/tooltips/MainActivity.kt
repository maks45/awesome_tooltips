package com.example.tooltips

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.awesome.tooltips.TooltipScreen
import com.awesome.tooltips.addTooltip
import kotlinx.coroutines.flow.flow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .addTooltip(
                            InitialTooltipScene,
                            TextMaskRef
                        ),
                    text = "Some Text",
                    style = TextStyle(
                        textAlign = TextAlign.Center,
                        color = Color.Black,
                        fontSize = 22.sp
                    )
                )
                Button(
                    modifier = Modifier
                        .padding(top = 100.dp)
                        .addTooltip(
                            InitialTooltipScene,
                            ButtonMaskRef
                        ),
                    onClick = {}
                ) {
                    Text(
                        text = "Some Button"
                    )
                }
            }
            TooltipScreen(
                tooltipSceneFlow = flow {
                    emit(InitialTooltipScene)
                },
                elementContentProvider = ExampleTooltipElementContentProvider
            )
        }
    }
}
