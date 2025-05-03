package com.example.todolistapp

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class TodoViewModel: ViewModel(){
    var todoList = mutableStateListOf<TodoItem>()
        private set

    fun addTodo(task: String){
        todoList.add(TodoItem(id = todoList.size+1, task = task))
    }

    fun removeTodo(item: TodoItem){
        todoList.remove(item)
    }

    fun toggleDone(item: TodoItem){
        item.isDone.value = !item.isDone.value
    }

    fun editTodo(item: TodoItem, newTask: String){
        val index = todoList.indexOf(item)
        if(index != -1){
            todoList[index] = item.copy(task = newTask)
        }
    }
}
