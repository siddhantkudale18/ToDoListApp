package com.example.todolistapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoListScreen(viewModel: TodoViewModel = viewModel()){
    var newTask by remember { mutableStateOf(TextFieldValue(""))}
    var editingItem by remember { mutableStateOf<TodoItem?>(null)}
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ){
        // Input field for new tasks
        Spacer(modifier = Modifier.height(46.dp))
        OutlinedTextField(
            value = newTask,
            onValueChange = {newTask = it},
            label = {Text("Add a new task")},
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF2196F3),
                unfocusedBorderColor = Color(0xFF90CAF9),
                cursorColor = Color(0xFF2196F3),
                focusedLabelColor = Color(0xFF2196F3)
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Add Button
        Button(
            onClick = {
                if(newTask.text.isNotBlank()){
                    if(editingItem != null){
                        viewModel.editTodo(editingItem!!, newTask.text)
                        editingItem = null
                    } else {
                        viewModel.addTodo(newTask.text)
                    }
                    newTask = TextFieldValue("")
                }
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(4.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2196F3)
            )
        )
        {
            Text("Add Task")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // LazyColumn List
        LazyColumn {
            items(viewModel.todoList) { item ->
                TodoItemCard(item, viewModel) { selectedItem ->
                    editingItem = selectedItem
                    newTask = TextFieldValue(
                        text = selectedItem.task,
                        selection = TextRange(selectedItem.task.length)
                    )
                }
            }
        }
    }
}


























