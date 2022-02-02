package com.hybcode.taskssaver.logic.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hybcode.taskssaver.data.TaskDao
import java.lang.IllegalArgumentException

class TasksViewModelFactory(private val dao: TaskDao): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TasksViewModel::class.java)){
            return TasksViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}