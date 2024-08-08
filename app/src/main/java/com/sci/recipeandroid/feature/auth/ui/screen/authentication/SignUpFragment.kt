package com.sci.recipeandroid.feature.auth.ui.screen.authentication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.sci.recipeandroid.R
import com.sci.recipeandroid.databinding.FragmentSignUpBinding
import com.sci.recipeandroid.feature.auth.ui.viewmodel.RegistrationFormEvent
import com.sci.recipeandroid.feature.auth.ui.viewmodel.ValidationEvent
import com.sci.recipeandroid.feature.auth.ui.viewmodel.ValidationViewModel


class SignUpFragment : Fragment() {

    private var binding: FragmentSignUpBinding? = null

    private val validationViewModel: ValidationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        getActivity()?.getWindow()?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {

            //button initial state
            btnSignUp.isEnabled = false
            btnSignUp.setBackgroundColor(ContextCompat.getColor(requireContext(),
                R.color.color_button_inactive))

            textChangeListenerEvent()

            btnSignUp.setOnClickListener {
                validationViewModel.onEvent(RegistrationFormEvent.Submit)
            }
        }

        // Observe the form state
        validationViewModel.state.observe(viewLifecycleOwner){ state ->
            binding?.apply {
                emailInputLayout.error = state.emailError
                passwordInputLayout.error = state.passwordError
                confirmPassInputLayout.error = state.repeatedPasswordError
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
            edtName.addTextChangedListener {
                validateEmptyField()
            }
            edtEmail.addTextChangedListener {
                validationViewModel.onEvent(RegistrationFormEvent.EmailChanged(it.toString()))
                validationViewModel.clearEmailError()
                emailInputLayout.isErrorEnabled = false
                validateEmptyField()
            }
            edtPassword.addTextChangedListener {
                validationViewModel.onEvent(RegistrationFormEvent.PasswordChanged(it.toString()))
                validationViewModel.clearPasswordError()
                passwordInputLayout.isErrorEnabled = false
                validateEmptyField()
            }
            edtConfirmPassword.addTextChangedListener {
                validationViewModel.onEvent(RegistrationFormEvent.RepeatedPasswordChanged(it.toString()))
                validationViewModel.clearRepeatedPasswordError()
                confirmPassInputLayout.isErrorEnabled = false
                validateEmptyField()
            }
        }
    }

    //validate empty field
    private fun validateEmptyField() {
        val isAnyFilledField = binding?.edtName?.text.toString().isNotBlank() &&
                binding?.edtEmail?.text.toString().isNotBlank()

        if (isAnyFilledField) {
            binding?.btnSignUp?.isEnabled = true
            binding?.btnSignUp?.setBackgroundColor(ContextCompat.getColor(requireContext(),
                R.color.color_border_active))
        } else {
            binding?.btnSignUp?.isEnabled = false
            binding?.btnSignUp?.setBackgroundColor(ContextCompat.getColor(requireContext(),
                R.color.color_button_inactive))
        }
    }

}