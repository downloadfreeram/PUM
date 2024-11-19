package com.example.lista2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.lista2.databinding.FragmentBBinding

class FragmentB : Fragment() {
    private lateinit var binding: FragmentBBinding
    private lateinit var userModel: UserCredentials

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBBinding.inflate(inflater)

        userModel = ViewModelProvider(requireActivity()).get(UserCredentials::class.java)

        binding.register.setOnClickListener {
            val username = binding.username.text.toString()
            val password = binding.password.text.toString()
            val passwordRepeat = binding.passwordRepeat.text.toString()

            val action = FragmentBDirections.actionFragmentBToFragmentC()
            if(username.isEmpty() || password.isEmpty() || passwordRepeat.isEmpty()) {
                binding.error.text="Some fields are empty"
            }
            else if(userModel.registerUser(username,password,passwordRepeat)) {
                Navigation.findNavController(requireView()).navigate(action)
            }
            else {
                binding.error.text="Username already exists"
            }
        }
        return binding.root
    }
}
