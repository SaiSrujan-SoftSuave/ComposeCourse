package com.example.composecourse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.android.codelab.animation.ui.home.Home
import com.example.composecourse.ui.theme.ComposeCourseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            val windowSize = calculateWindowSizeClass(activity = this)
            ComposeCourseTheme {
                MyScreen(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun MyScreen(modifier: Modifier = Modifier) {
    Surface(modifier) {
      Home()
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun MyScreenPreview(modifier: Modifier = Modifier) {
    ComposeCourseTheme {
        MyScreen()
    }
}
