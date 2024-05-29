 package com.example.composecourse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.unit.dp
import com.example.composecourse.ui.theme.ComposeCourseTheme

 class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            val windowSize = calculateWindowSizeClass(activity = this)
            ComposeCourseTheme {
                WellnessScreen(viewModel = WellNessViewModel())
            }
        }
    }
}

fun getTaskItems() = List(30) { it -> WellnessTask(id = it, "Task # $it") }

@Composable
fun WellnessTaskIte(modifier: Modifier = Modifier, taskName: String, onClose: () -> Unit) {
    var checkedState by rememberSaveable { mutableStateOf(false) }

    WellnessTaskItem(
        taskName = taskName,
        checked = checkedState,
        onCheckedChange = { newValue -> checkedState = newValue },
        onClose = { onClose() }, // we will implement this later!
        modifier = modifier,
    )
}

@Composable
fun WellnessTaskList(
    modifier: Modifier = Modifier, list: List<WellnessTask> = remember {
        getTaskItems()
    },
    onCloseTask: (task: WellnessTask) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        state = rememberLazyListState()
    ) {
        items(list, key = { task -> task.id }) { task ->
            WellnessTaskIte(taskName = task.label, onClose = {
                onCloseTask(task)
            })
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
fun WellnessTaskItem(
    modifier: Modifier = Modifier,
    taskName: String,
    onClose: () -> Unit,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = taskName, modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp)
        )
        Checkbox(checked = checked, onCheckedChange = onCheckedChange)
        IconButton(onClick = { onClose() }) {
            Icon(imageVector = Icons.Filled.Close, contentDescription = null)
        }
    }
}

@LightDarkThemePreview
@Composable
fun WellnessTaskItemPreview(modifier: Modifier = Modifier) {
    WellnessTaskItem(taskName = "This is the task", onClose = {}, onCheckedChange = { it ->

    }, checked = true)
}

@Composable
fun WellnessScreen(modifier: Modifier = Modifier,viewModel: WellNessViewModel) {
    val list by viewModel.tasks.collectAsState()
    Column {
        WaterCounter(modifier)
        WellnessTaskList(list = list, onCloseTask = { task ->
            viewModel.remove(task)
        })
    }
}
@LightDarkThemePreview
@Composable
fun WellnessScreenPreview() {
    ComposeCourseTheme {
        WellnessScreen(viewModel = WellNessViewModel())
    }
}


