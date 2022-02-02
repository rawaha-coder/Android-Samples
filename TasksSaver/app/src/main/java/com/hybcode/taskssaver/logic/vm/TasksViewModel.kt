package com.hybcode.taskssaver.logic.vm

import androidx.lifecycle.*
import com.hybcode.taskssaver.data.Task
import com.hybcode.taskssaver.data.TaskDao
import kotlinx.coroutines.launch

class TasksViewModel(val Dao: TaskDao): ViewModel() {

    var newTaskName = MutableLiveData("")

    val tasks = Dao.getAll()

    fun addTask(){
        viewModelScope.launch{
            newTaskName.value?.let  {
                Dao.insert(Task(taskName = it))
            }
        }
        newTaskName.value = ""
    }
}