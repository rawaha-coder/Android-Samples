package com.hybcode.taskssaver.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hybcode.taskssaver.R
import com.hybcode.taskssaver.data.Task
import com.hybcode.taskssaver.databinding.TaskItemBinding

class TaskItemAdapter : RecyclerView.Adapter<TaskItemAdapter.TaskItemViewHolder>() {

    var data = listOf<Task>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : TaskItemViewHolder = TaskItemViewHolder.inflateFrom(parent)

    override fun onBindViewHolder(holder: TaskItemViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun getItemCount() = data.size

    class TaskItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding: TaskItemBinding = TaskItemBinding.bind(view)

        companion object {
            fun inflateFrom(parent: ViewGroup): TaskItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater
                    .inflate(R.layout.task_item, parent, false)
                return TaskItemViewHolder(view)
            }
        }

        fun bind(item: Task) {
            binding.taskName.text = item.taskName
            binding.taskDone.isChecked = item.taskDone
        }
    }

}