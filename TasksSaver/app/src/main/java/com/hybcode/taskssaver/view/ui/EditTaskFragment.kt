package com.hybcode.taskssaver.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.hybcode.taskssaver.data.TaskDatabase
import com.hybcode.taskssaver.databinding.FragmentEditTaskBinding
import com.hybcode.taskssaver.logic.vm.EditTaskViewModel
import com.hybcode.taskssaver.logic.vm.EditTaskViewModelFactory

class EditTaskFragment : Fragment() {

    private var _binding: FragmentEditTaskBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentEditTaskBinding.inflate(inflater, container, false)
        val view = binding.root

        val taskId = EditTaskFragmentArgs.fromBundle(requireArguments()).taskid

        val application = requireNotNull(this.activity).application
        val dao = TaskDatabase.getInstance(application).taskDao
        val viewModelFactory = EditTaskViewModelFactory(taskId, dao)
        val viewModel = ViewModelProvider(this, viewModelFactory)
            .get(EditTaskViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.navigateToList.observe(viewLifecycleOwner, Observer {navigate ->
            if (navigate){
                val action = EditTaskFragmentDirections.actionEditTaskFragmentToTasksFragment()
                this.findNavController().navigate(action)
                viewModel.onNavigatedToList()
            }
        })
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}