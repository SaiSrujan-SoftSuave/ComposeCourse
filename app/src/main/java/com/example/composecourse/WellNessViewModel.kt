package com.example.composecourse

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class WellNessViewModel:ViewModel() {
  private val _tasks =  MutableStateFlow<MutableList<WellnessTask>>(getTaskItems().toMutableStateList())
  val tasks: StateFlow<MutableList<WellnessTask>> = _tasks

  fun remove(item:WellnessTask) {
    _tasks.value.remove(item)
  }

}