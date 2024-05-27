package com.example.composecourse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
        var count by rememberSaveable {
            mutableIntStateOf(0)
        }
        if (count > 0) {
            var showTask by rememberSaveable {
                mutableStateOf(true)
            }
            if (showTask) {
                WellnessTaskItem(taskName = "Have you taken a 15 mins walk") {
                    showTask = false
                }
            }
            Text("You've had ${count} glasses.")
        }
        Row(
            modifier = Modifier.padding(top = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = { count++ }, Modifier.padding(), enabled = count < 10) {
                Text("Add one")
            }
            Button(onClick = { count = 0 }, Modifier.padding(start = 10.dp)) {
                Text("Clear the water")
            }
        }
    }
}

@Composable
fun WellnessTaskItem(modifier: Modifier = Modifier, taskName: String, onClose: () -> Unit) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = taskName, modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp)
        )
        IconButton(onClick = { onClose() }) {
            Icon(imageVector = Icons.Filled.Close, contentDescription = null)
        }
    }
}

@Preview(widthDp = 360, heightDp = 640, showSystemUi = true)
@Composable
fun WellnessTaskItemPreview(modifier: Modifier = Modifier) {
    WellnessTaskItem(taskName = "This is the task", onClose = {})
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


