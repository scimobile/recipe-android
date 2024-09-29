package com.sci.recipeandroid.feature.auth.ui.screen.authentication

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.updateLayoutParams
import androidx.core.widget.addTextChangedListener
import com.google.android.material.snackbar.Snackbar
import com.sci.recipeandroid.R
import com.sci.recipeandroid.databinding.FragmentSignUpBinding
import com.sci.recipeandroid.feature.auth.ui.viewmodel.RegistrationFormEvent
import com.sci.recipeandroid.feature.auth.ui.viewmodel.SignUpViewModel
import com.sci.recipeandroid.feature.auth.ui.viewmodel.SignUpScreenEvent
import com.sci.recipeandroid.util.setOneTimeClickListener
import org.koin.androidx.viewmodel.ext.android.viewModel


class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    private val signUpViewModel: SignUpViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val window = requireActivity().window
        window.statusBarColor = Color.WHITE
        window.navigationBarColor = Color.TRANSPARENT
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = true

        binding.apply {
            //button initial state
            btnSignUp.isEnabled = false
            btnSignUp.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.color_button_inactive
                )
            )
            loadingIndicator.visibility = View.GONE

            setUpTextChangeListenerEvent()

            btnSignUp.setOneTimeClickListener {
                signUpViewModel.onEvent(RegistrationFormEvent.Submit)
            }
        }

        // Observe the form state
        signUpViewModel.formState.observe(viewLifecycleOwner) { state ->
            binding.apply {
                signUpBtnVisibility(
                    state.displayName.isNotEmpty() and
                            state.email.isNotEmpty()
                )

                emailInputLayout.error = state.emailError
                passwordInputLayout.error = state.passwordError
                confirmPassInputLayout.error = state.repeatedPasswordError
            }
        }

        // Observe validation events
        signUpViewModel.signUpScreenEvent.observe(viewLifecycleOwner) { event ->
            when (event) {
                is SignUpScreenEvent.Success -> {
                    Snackbar.make(
                        view,
                        getString(R.string.registration_successful), Snackbar.LENGTH_SHORT
                    ).show()
                    // Navigate to the next screen
                    binding.loadingIndicator.visibility = View.GONE
                }

                is SignUpScreenEvent.Error -> {
                    Snackbar.make(
                        view,
                        "Something went wrong", Snackbar.LENGTH_SHORT
                    ).show()
                }

                is SignUpScreenEvent.Loading -> {
                    binding.loadingIndicator.visibility = View.VISIBLE
                    binding.btnSignUp.isEnabled = false
                }
            }
        }

    }

    private fun setUpTextChangeListenerEvent() {
        binding.apply {
            edtName.addTextChangedListener {
                signUpViewModel.onEvent(RegistrationFormEvent.NameChanged(it.toString()))
            }
            edtEmail.addTextChangedListener {
                signUpViewModel.onEvent(RegistrationFormEvent.EmailChanged(it.toString()))
                signUpViewModel.clearEmailError()
                emailInputLayout.isErrorEnabled = false
            }
            edtPassword.addTextChangedListener {
                signUpViewModel.onEvent(RegistrationFormEvent.PasswordChanged(it.toString()))
                signUpViewModel.clearPasswordError()
                passwordInputLayout.isErrorEnabled = false
            }
            edtConfirmPassword.addTextChangedListener {
                signUpViewModel.onEvent(RegistrationFormEvent.RepeatedPasswordChanged(it.toString()))
                signUpViewModel.clearRepeatedPasswordError()
                confirmPassInputLayout.isErrorEnabled = false
            }
        }
    }

    private fun signUpBtnVisibility(shouldShow: Boolean) {
        if (shouldShow) {
            binding.btnSignUp.isEnabled = true
            binding.btnSignUp.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.color_border_active
                )
            )
        } else {
            binding.btnSignUp.isEnabled = false
            binding.btnSignUp.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.color_button_inactive
                )
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}