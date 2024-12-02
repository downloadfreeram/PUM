package com.example.lista3.ui.E1

import TaskListAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lista3.R
import com.example.lista3.databinding.FragmentE1Binding
import generateTaskLists

class E1Fragment : Fragment() {

    private var _binding: FragmentE1Binding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_e1, container, false)

        val taskList = generateTaskLists()

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewE1)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = TaskListAdapter(taskList)

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}