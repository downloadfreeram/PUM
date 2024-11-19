package com.example.lista2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.lista2.databinding.FragmentCBinding
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

class FragmentC : Fragment() {

    private lateinit var binding: FragmentCBinding
    private lateinit var userModel: UserCredentials

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        userModel = ViewModelProvider(requireActivity()).get(UserCredentials::class.java)
        //val view = inflater.inflate(R.layout.fragment_c,container,false)
        //val loginText = view.findViewById<TextView>(R.id.login).text.toString()
        binding = FragmentCBinding.inflate(inflater)
        binding.login.setOnClickListener {
            val username = binding.username.text.toString()
            val password = binding.password.text.toString()
            if(userModel.validate(username,password,password)) {
                val action = FragmentCDirections.actionFragmentCToFragmentD(username)
                Navigation.findNavController(requireView()).navigate(action)
            }
            else {
                binding.error.text = "Wrong credentials"
            }
        }
        binding.register.setOnClickListener {
            val action = FragmentCDirections.actionFragmentCToFragmentB()
            Navigation.findNavController(requireView()).navigate(action)
        }
        return binding.root
    }
}