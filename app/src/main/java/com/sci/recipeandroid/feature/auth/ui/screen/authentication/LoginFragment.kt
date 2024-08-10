package com.sci.recipeandroid.feature.auth.ui.screen.authentication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.sci.recipeandroid.R
import com.sci.recipeandroid.databinding.FragmentLoginBinding
import com.sci.recipeandroid.feature.auth.ui.viewmodel.LoginFormEvent
import com.sci.recipeandroid.feature.auth.ui.viewmodel.LoginScreenEvent
import com.sci.recipeandroid.feature.auth.ui.viewmodel.LoginViewModel
import com.sci.recipeandroid.feature.auth.ui.viewmodel.RegistrationFormEvent
import com.sci.recipeandroid.feature.auth.ui.viewmodel.SignUpScreenEvent
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val loginViewModel: LoginViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            loadingIndicator.visibility = View.GONE
            btnLogin.isEnabled = false
            btnLogin.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.color_button_inactive
                )
            )

            textChangeEventListener()

            //region observe login form state
            loginViewModel.loginFormState.observe(viewLifecycleOwner) {
                textInputLayoutEmail.error = it.emailError
                if (it.email.isNotEmpty() && it.password.isNotEmpty()) {
                    binding.btnLogin.isEnabled = true
                    binding.btnLogin.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.color_border_active
                        )
                    )
                } else {
                    btnLogin.isEnabled = false
                    btnLogin.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.color_button_inactive
                        )
                    )
                }
            }
            //endregion

            //region observe login screen event
            loginViewModel.loginScreenEvent.observe(viewLifecycleOwner){
                when(it){
                    is LoginScreenEvent.Error -> {
                        loadingIndicator.visibility = View.GONE
                        Snackbar.make(
                            view,
                            "Something went wrong", Snackbar.LENGTH_SHORT
                        ).show()
                    }
                    is LoginScreenEvent.Loading -> {
                        loadingIndicator.visibility = View.VISIBLE
                        btnLogin.isEnabled = false
                    }
                    is LoginScreenEvent.Success -> {
                        loadingIndicator.visibility = View.GONE
                        Snackbar.make(
                            view,
                            "Login Successful", Snackbar.LENGTH_SHORT
                        ).show()
                        requireActivity().supportFragmentManager
                            .beginTransaction()
                            .addToBackStack("login")
                            .replace(R.id.host_fragment, AuthOptionFragment())
                            .commit()
                    }
                }
            }
            //endregion

            btnLogin.setOnClickListener {
                loginViewModel.onEvent(LoginFormEvent.Submit)
            }

        }


    }

    private fun textChangeEventListener() {
        binding.apply {
            edtEmail.addTextChangedListener {
                loginViewModel.onEvent(LoginFormEvent.EmailChanged(it.toString()))
                loginViewModel.clearEmailError()
            }
            edtPassword.addTextChangedListener {
                loginViewModel.onEvent(LoginFormEvent.PasswordChange(it.toString()))
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}