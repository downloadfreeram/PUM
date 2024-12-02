package com.example.lista3.ui.E1

import TaskListAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lista3.R
import com.example.lista3.databinding.FragmentE1Binding
import Tasks
import androidx.navigation.fragment.findNavController

class E1Fragment : Fragment() {

    private var _binding: FragmentE1Binding? = null

    private val binding get() = _binding!!

    private lateinit var viewModel: E1ViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TaskListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_e1, container, false)

        // RecyclerView setup
        recyclerView = view.findViewById(R.id.recyclerViewE1)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = TaskListAdapter(emptyList(), object : TaskListAdapter.OnItemClickListener {
            override fun onItemClick(taskList: Tasks) {
                viewModel.selectTask(taskList)
                val action = E1FragmentDirections
                    .actionE1ToE3(taskList.taskName,
                        taskList.subject)
                findNavController().navigate(action)
            }
        })

        recyclerView.adapter = adapter

        viewModel = ViewModelProvider(requireActivity())[E1ViewModel::class.java]
        viewModel.getTaskLists().observe(viewLifecycleOwner) { taskLists ->
            adapter.updateData(taskLists)
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}