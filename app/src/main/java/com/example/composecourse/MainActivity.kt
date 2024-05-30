package com.example.composecourse

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composecourse.ui.theme.ComposeCourseTheme
import java.math.BigDecimal
import java.time.LocalDate

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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF0F0260)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Canvas(
                modifier = Modifier
                    .padding(8.dp)
                    .aspectRatio(3 / 2f)
                    .fillMaxSize()
            ) {
                val barWidthPx = 1.dp.toPx()
                drawRect(
                    Color.Gray, style = Stroke(barWidthPx)
                )
                val verticalLines = 4
                val verticalLinePx = size.width / (verticalLines + 1)
                repeat(verticalLines) { i ->
                    val startX = verticalLinePx * (i + 1)
                    drawLine(
                        Color.White,
                        Offset(startX, 0f),
                        Offset(startX, size.height),
                        strokeWidth = barWidthPx
                    )
                }
                val horizontalLines = 3
                val sectionSize = size.height / (horizontalLines + 1)
                repeat(horizontalLines) { i ->
                    val startY = sectionSize * (i + 1)
                    drawLine(
                        Color.White,
                        Offset(0f, startY),
                        end = Offset(size.width,startY),
                        strokeWidth = barWidthPx
                    )
                }
                val path =Constant.generatePath(graphData,size)
                val filledPath =Path()
                filledPath.addPath(path)
                filledPath.lineTo(size.width,size.height)
                filledPath.lineTo(0f,size.height)
                filledPath.close()

                val brush = Brush.verticalGradient(listOf(
                    Color.Green.copy(alpha = 0.6f),
                    Color.Transparent
                ))
                drawPath(
                    path = filledPath,
                    brush =brush
                )
                drawPath(path, color = Color.Green, style = Stroke(2.dp.toPx()))

            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun MyScreenPreview(modifier: Modifier = Modifier) {
    ComposeCourseTheme {
        MyScreen()
    }
}

object Constant {
    val PurpleBackGroundColor = 0xFF7600BC

    fun generatePath(data: List<Balance>, size: Size): Path {
        val path = Path()
        val numberEntries = data.size - 1
        val weekWidth = size.width / numberEntries

        val max = data.maxBy { it.amount }
        val min = data.minBy { it.amount } // will map to x= 0, y = height
        val range = max.amount - min.amount
        val heightPxPerAmount = size.height / range.toFloat()

        data.forEachIndexed { i, balance ->
            if (i == 0) {
                path.moveTo(
                    0f,
                    size.height - (balance.amount - min.amount).toFloat() *
                            heightPxPerAmount
                )
            }
            val balanceX = i * weekWidth
            val balanceY = size.height - (balance.amount - min.amount).toFloat() *
                    heightPxPerAmount
            path.lineTo(balanceX, balanceY)
        }
        return path
    }

}


@RequiresApi(Build.VERSION_CODES.O)
val graphData = listOf(
    Balance(LocalDate.now(), BigDecimal(65631)),
    Balance(LocalDate.now().plusWeeks(1), BigDecimal(65931)),
    Balance(LocalDate.now().plusWeeks(2), BigDecimal(65851)),
    Balance(LocalDate.now().plusWeeks(3), BigDecimal(65931)),
    Balance(LocalDate.now().plusWeeks(4), BigDecimal(66484)),
    Balance(LocalDate.now().plusWeeks(5), BigDecimal(67684)),
    Balance(LocalDate.now().plusWeeks(6), BigDecimal(66684)),
    Balance(LocalDate.now().plusWeeks(7), BigDecimal(66984)),
    Balance(LocalDate.now().plusWeeks(8), BigDecimal(70600)),
    Balance(LocalDate.now().plusWeeks(9), BigDecimal(71600)),
    Balance(LocalDate.now().plusWeeks(10), BigDecimal(72600)),
    Balance(LocalDate.now().plusWeeks(11), BigDecimal(72526)),
    Balance(LocalDate.now().plusWeeks(12), BigDecimal(72976)),
    Balance(LocalDate.now().plusWeeks(13), BigDecimal(73589)),
)

data class Balance(val date: LocalDate, val amount: BigDecimal)
