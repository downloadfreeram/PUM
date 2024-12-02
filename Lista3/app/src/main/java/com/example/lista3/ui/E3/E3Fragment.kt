package com.example.lista3.ui.E3

import TaskDetailAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lista3.R
import com.example.lista3.ui.E1.E1ViewModel

class E3Fragment : Fragment() {

    private lateinit var taskDetailAdapter: TaskDetailAdapter
    private lateinit var viewModel: E1ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_e3, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[E1ViewModel::class.java]

        val subject = arguments?.getString("subject")

        requireActivity().title = subject ?: "Task Details"

        // RecyclerView setup
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewE3)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        taskDetailAdapter = TaskDetailAdapter(viewModel.getSelectedTaskDetails().value ?: emptyList())
        recyclerView.adapter = taskDetailAdapter

        viewModel.getSelectedTaskDetails().observe(viewLifecycleOwner) { taskDetails ->
            taskDetailAdapter.updateData(taskDetails)
        }
    }
}