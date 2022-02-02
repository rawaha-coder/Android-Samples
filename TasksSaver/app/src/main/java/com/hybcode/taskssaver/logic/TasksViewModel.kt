package com.hybcode.taskssaver.logic

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hybcode.taskssaver.data.Task
import com.hybcode.taskssaver.data.TaskDao
import kotlinx.coroutines.launch

class TasksViewModel(val Dao: TaskDao): ViewModel() {
    var newTaskName = ""

    val tasks = Dao.getAll()

    val taskString = Transformations.map(tasks){
        tasks -> formatTasks(tasks)
    }

    private fun formatTasks(tasks: List<Task>): String {
        return tasks.fold(""){
                acc , item -> acc + "\n" + formatTask(item)
        }
    }

    private fun formatTask(item: Task): String {
        var str = "ID; ${item.taskId}"
        str += "\n Name: ${item.taskName}"
        str += "\n Complete: ${item.taskDone} \n"

        return str
    }

    fun addTask(){
        viewModelScope.launch{
            Dao.insert(Task(taskName = newTaskName))
        }
    }
}