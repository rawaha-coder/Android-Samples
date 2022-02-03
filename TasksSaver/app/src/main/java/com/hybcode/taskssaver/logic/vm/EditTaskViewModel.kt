package com.hybcode.taskssaver.logic.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hybcode.taskssaver.data.TaskDao
import kotlinx.coroutines.launch

class EditTaskViewModel(taskId: Long, private val Dao: TaskDao) : ViewModel() {

    val task = Dao.get(taskId)

    private val _navigateToList = MutableLiveData<Boolean>()
    val navigateToList: LiveData<Boolean>
        get() = _navigateToList


    fun updateTask() {
        viewModelScope.launch {
            Dao.update(task.value!!)
            _navigateToList.value = true
        }
    }

    fun deleteTask() {
        viewModelScope.launch {
            Dao.delete(task.value!!)
            _navigateToList.value = true
        }
    }

    fun onNavigatedToList() {
        _navigateToList.value = false
    }

}