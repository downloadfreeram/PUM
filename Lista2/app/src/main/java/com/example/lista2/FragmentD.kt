package com.example.lista2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import com.example.lista2.databinding.FragmentDBinding
import androidx.navigation.fragment.navArgs

class FragmentD : Fragment() {

    private lateinit var binding: FragmentDBinding
    private val args: FragmentDArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDBinding.inflate(inflater)

        val username = args.loginText
        binding.textBar.text = "Welcome "+username

        binding.logout.setOnClickListener {
            val action = FragmentDDirections.actionFragmentDToFragmentA()
            Navigation.findNavController(requireView()).navigate(action)
        }
        return binding.root
    }
}