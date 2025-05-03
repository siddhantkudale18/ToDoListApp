package com.example.todolistapp

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.Color

@Composable
fun TodoItemCard(item: TodoItem, viewModel: TodoViewModel, onEdit: (TodoItem) -> Unit){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF009688)
        )
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ){
            Checkbox(
                checked = item.isDone.value,
                onCheckedChange = {viewModel.toggleDone(item)},
                colors = CheckboxDefaults.colors(
                    checkedColor = Color.Green,
                    checkmarkColor = Color.White,
                    uncheckedColor = Color.Black
                )
            )
            Text(
                text = item.task,
                modifier = Modifier.weight(1f)
            )

            // Edit Button
            IconButton(onClick = {
                onEdit(item)
            }) {
                Icon(Icons.Default.Edit, contentDescription = "Edit Task")
            }
            IconButton(onClick = {viewModel.removeTodo(item)}){
                Icon(Icons.Default.Delete, contentDescription = "Delete Task")
            }
        }
    }
}