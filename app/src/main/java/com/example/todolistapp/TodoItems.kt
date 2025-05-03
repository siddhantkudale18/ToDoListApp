package com.example.todolistapp

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class TodoItem(
    val id: Int,
    var task: String,
    var isDone: MutableState<Boolean> = mutableStateOf(false)
)