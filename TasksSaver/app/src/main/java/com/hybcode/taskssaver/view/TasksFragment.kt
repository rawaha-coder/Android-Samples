package com.hybcode.taskssaver.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hybcode.taskssaver.data.TaskDatabase
import com.hybcode.taskssaver.databinding.FragmentTasksBinding
import com.hybcode.taskssaver.logic.TasksViewModel
import com.hybcode.taskssaver.logic.TasksViewModelFactory


class TasksFragment : Fragment() {

    var _binding : FragmentTasksBinding? = null
    val binding get() = _binding!!
    lateinit var viewModel: TasksViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentTasksBinding.inflate(inflater, container, false)
        val view = binding.root

        val application = requireNotNull(this.activity).application
        val dao = TaskDatabase.getInstance(application).taskDao
        val viewModelFactory = TasksViewModelFactory(dao)

        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(TasksViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val adapter = TaskItemAdapter()
        binding.listsTask.adapter = adapter

        viewModel.tasks.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.data = it
            }
        })

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}