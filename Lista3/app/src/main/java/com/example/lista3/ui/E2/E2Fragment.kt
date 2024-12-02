package com.example.lista3.ui.E2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lista3.R
import com.example.lista3.databinding.FragmentE2Binding
import GradeAdapter
import androidx.lifecycle.ViewModelProvider
import com.example.lista3.ui.E1.E1ViewModel

class E2Fragment : Fragment() {

    private var _binding: FragmentE2Binding? = null

    private val binding get() = _binding!!

    private lateinit var viewModel: E1ViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: GradeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_e2, container, false)

        recyclerView = view.findViewById(R.id.recyclerViewE2)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = GradeAdapter(emptyList())
        recyclerView.adapter = adapter

        viewModel = ViewModelProvider(requireActivity())[E1ViewModel::class.java]

        // observe
        viewModel.getSubjectSummaries().observe(viewLifecycleOwner) { grades ->
            // set data in adapter
            adapter.updateData(grades)
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}