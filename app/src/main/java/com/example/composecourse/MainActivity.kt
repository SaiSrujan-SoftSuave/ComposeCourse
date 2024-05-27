package com.example.composecourse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composecourse.ui.theme.ComposeCourseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            val windowSize = calculateWindowSizeClass(activity = this)
            ComposeCourseTheme {
                WellnessScreen()
            }
        }
    }
}
@Composable
fun WaterCounter(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        var count :MutableState<Int> = remember {
            mutableIntStateOf(0)
        }
        Text("You've had ${count.value} glasses.")
        Button(onClick = { count.value ++ }, Modifier.padding(top = 8.dp)) {
            Text("Add one")
        }
    }
}

@Composable
fun WellnessScreen(modifier: Modifier = Modifier) {
    WaterCounter(modifier)
}
        @Preview(widthDp = 360, heightDp = 640, showSystemUi = true)
        @Composable
        fun WellnessScreenPreview() {
            ComposeCourseTheme {
                WellnessScreen()
            }
        }


