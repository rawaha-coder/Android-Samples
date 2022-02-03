package com.hybcode.taskssaver.logic.vm

import androidx.lifecycle.*
import com.hybcode.taskssaver.data.Task
import com.hybcode.taskssaver.data.TaskDao
import kotlinx.coroutines.launch

class TasksViewModel(val Dao: TaskDao) : ViewModel() {

    var newTaskName = MutableLiveData("")

    private val _navigateToTask = MutableLiveData<Long?>()
    val navigateToTask: LiveData<Long?>
        get() = _navigateToTask

    val tasks = Dao.getAll()

    fun onTaskClicked(taskId: Long?){
        _navigateToTask.value = taskId
    }

    fun onTaskNavigated(){
        _navigateToTask.value = null
    }

    fun addTask() {
        viewModelScope.launch {
            newTaskName.value?.let {
                Dao.insert(Task(taskName = it))
            }
        }
        newTaskName.value = ""
    }
}