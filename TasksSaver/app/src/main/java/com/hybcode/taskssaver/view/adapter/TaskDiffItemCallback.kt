package com.hybcode.taskssaver.view.adapter

import androidx.recyclerview.widget.DiffUtil
import com.hybcode.taskssaver.data.Task

class TaskDiffItemCallback : DiffUtil.ItemCallback<Task>() {
    override fun areItemsTheSame(oldItem: Task, newItem: Task)
            : Boolean = (oldItem.taskId == newItem.taskId)

    override fun areContentsTheSame(oldItem: Task, newItem: Task)
            : Boolean = (oldItem == newItem)
}