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
import com.awesome.tooltips.mask.MaskType
import com.awesome.tooltips.scene.SceneFlowProvider
import com.awesome.tooltips.scene.TooltipScene
import kotlinx.coroutines.flow.Flow
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
                    modifier = Modifier,
                    text = "Just some text without tooltip",
                    style = TextStyle(
                        textAlign = TextAlign.Center,
                        color = Color.Black,
                        fontSize = 22.sp
                    )
                )
                Text(
                    modifier = Modifier
                        .padding(top = 50.dp)
                        .addTooltip(
                            InitialTooltipScene,
                            maskRef = TextMaskRef
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
                            ButtonMaskRef,
                            maskType = MaskType.RoundRect(20.dp)
                        ),
                    onClick = {}
                ) {
                    Text(
                        text = "Some Button"
                    )
                }
            }
            TooltipScreen(
                sceneFlowProvider = object : SceneFlowProvider {
                    override val tooltipsSceneFlow: Flow<TooltipScene>
                        get() = flow {
                            emit(InitialTooltipScene)
                        }
                },
                elementContentProvider = ExampleTooltipElementContentProvider
            )
        }
    }
}
