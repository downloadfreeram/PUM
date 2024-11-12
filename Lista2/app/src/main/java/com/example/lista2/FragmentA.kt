package com.example.lista2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.lista2.databinding.FragmentABinding

class FragmentA : Fragment() {
    private lateinit var binding: FragmentABinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentABinding.inflate(inflater)
        binding.login.setOnClickListener {
            val action = FragmentADirections.actionFragmentAToFragmentB()
            Navigation.findNavController(requireView()).navigate(action)
        }
        binding.register.setOnClickListener {
            val action = FragmentADirections.actionFragmentAToFragmentC()
            Navigation.findNavController(requireView()).navigate(action))
        }
        return binding.root
    }
}
