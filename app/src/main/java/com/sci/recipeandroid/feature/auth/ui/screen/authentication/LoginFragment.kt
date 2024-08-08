package com.sci.recipeandroid.feature.auth.ui.screen.authentication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.sci.recipeandroid.R
import com.sci.recipeandroid.databinding.FragmentLoginBinding
import com.sci.recipeandroid.feature.auth.ui.viewmodel.RegistrationFormEvent
import com.sci.recipeandroid.feature.auth.ui.viewmodel.ValidationEvent
import com.sci.recipeandroid.feature.auth.ui.viewmodel.ValidationViewModel


class LoginFragment : Fragment() {

    private var binding: FragmentLoginBinding? = null
    private val validationViewModel: ValidationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {

            btnLogin.isEnabled = false
            btnLogin.setBackgroundColor(ContextCompat.getColor(requireContext(),
                R.color.color_button_inactive))

            textChangeListenerEvent()

            btnLogin.setOnClickListener {
                validationViewModel.onEvent(RegistrationFormEvent.Submit)
            }

        }

        // Observe the form state
        validationViewModel.state.observe(viewLifecycleOwner){ state ->
            binding?.apply {
                textInputLayoutEmail.error = state.emailError
            }
        }
        // Observe validation events
        validationViewModel.validationEvent.observe(viewLifecycleOwner) { event ->
            when (event) {
                is ValidationEvent.Success -> {
                    Snackbar.make(view, "Registration Successful!", Snackbar.LENGTH_SHORT).show()
                    // Navigate to the next screen

                }
            }
        }

    }

    private fun textChangeListenerEvent() {
        binding?.apply {
            edtEmail.addTextChangedListener {
                validationViewModel.onEvent(RegistrationFormEvent.EmailChanged(it.toString()))
                textInputLayoutEmail.isErrorEnabled = false
                validateEmptyField()
            }
            edtPassword.addTextChangedListener {
                validateEmptyField()
            }
        }
    }

    //validate empty field
    private fun validateEmptyField() {
        val isAnyFilledField = binding?.edtEmail?.text.toString().isNotBlank() &&
                binding?.edtPassword?.text.toString().isNotBlank()

        if (isAnyFilledField) {
            binding?.btnLogin?.isEnabled = true
            binding?.btnLogin?.setBackgroundColor(ContextCompat.getColor(requireContext(),
                R.color.color_border_active))
        } else {
            binding?.btnLogin?.isEnabled = false
            binding?.btnLogin?.setBackgroundColor(ContextCompat.getColor(requireContext(),
                R.color.color_button_inactive))
        }

    }

}