package com.example.composecourse

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.CardColors
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecourse.ui.theme.ComposeCourseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeCourseTheme {
                MyApp(Modifier)
            }
        }
    }
}

@Composable
fun MyApp(modifier: Modifier = Modifier) {
    var shouldShowOnboardingScreen by remember {
        mutableStateOf(true)
    }
    if (shouldShowOnboardingScreen) {
        OnBoardingScreen(onContinueCliked = { shouldShowOnboardingScreen = false })
    } else {
        Greetings(modifier = Modifier)
    }
}

@Composable
fun Greetings(modifier: Modifier, names: List<String> = List<String>(100) { "$it" }) {
    Surface(color = MaterialTheme.colorScheme.background, modifier = modifier) {
        LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
            for (name in names) {
                item {
                    Greeting(name = name, modifier = modifier)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Greeting(modifier: Modifier = Modifier, name: String = "ad") {
    var showBoolean by remember {
        mutableStateOf(false)
    }
    val icon: ImageVector =
        if (showBoolean) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown
    val extraPadding by animateDpAsState(
        targetValue = if (showBoolean) 50.dp else 0.dp, animationSpec =
        spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )
    Surface(
        color = MaterialTheme.colorScheme.primaryContainer,
        modifier = modifier.padding(horizontal = 8.dp, vertical = 4.dp),
        shape = MaterialTheme.shapes.small
    ) {
        Column(modifier = Modifier.padding(12.dp).animateContentSize(
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            )
        )) {
        Row(
            modifier = Modifier
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(12.dp)
            ) {
                Text(
                    text = "Hello,"
                )
                Text(
                    text = name, fontWeight = FontWeight.Bold, fontSize = 20.sp
                )
            }
            IconButton(onClick = { showBoolean =!showBoolean }) {
                Icon(imageVector = icon, contentDescription = "Show more")
            }
        }
            if(showBoolean)Text(text = ("Composem ipsum color sit lazy, " +
                    "padding theme elit, sed do bouncy. ").repeat(4))
        }
    }
}

@Composable
fun OnBoardingScreen(modifier: Modifier = Modifier, onContinueCliked: () -> Unit = {}) {

    Surface {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Welcome to the codelab!")
            Button(
                onClick = { onContinueCliked() },
                modifier = Modifier.padding(vertical = 24.dp)
            ) {
                Text(text = "Continue")
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 320)
@Preview(showBackground = true, widthDp = 320, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun GreetingPreview() {
    ComposeCourseTheme {
        Greetings(modifier = Modifier)
        OnBoardingScreen()
    }
}

@Composable
fun CardColors(modifier: Modifier = Modifier) {
//    Row(
//        modifier = Modifier
//            .padding(12.dp)
//            .animateContentSize(
//                animationSpec = spring(
//                    dampingRatio = Spring.DampingRatioMediumBouncy,
//                    stiffness = Spring.StiffnessLow
//                )
//            )
//    ) {
//        Column(
//            modifier = Modifier
//                .weight(1f)
//                .padding(12.dp)
//        ) {
//            Text(text = "Hello, ")
//            Text(
//                text = name, style = MaterialTheme.typography.headlineMedium.copy(
//                    fontWeight = FontWeight.ExtraBold
//                )
//            )
//            if (expanded) {
//                Text(
//                    text = ("Composem ipsum color sit lazy, " +
//                            "padding theme elit, sed do bouncy. ").repeat(4),
//                )
//            }
//        }
//        IconButton(onClick = { expanded = !expanded }) {
//            Icon(
//                imageVector = if (expanded) Filled.ExpandLess else Filled.ExpandMore,
//                contentDescription = if (expanded) {
//                    stringResource(R.string.show_less)
//                } else {
//                    stringResource(R.string.show_more)
//                }
//            )
//        }
//    }
}