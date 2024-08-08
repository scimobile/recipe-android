package com.sci.recipeandroid.feature.auth.ui.screen.authentication

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.Fragment
import com.sci.recipeandroid.R
import com.sci.recipeandroid.databinding.FragmentAuthenticationOptionsBinding
import com.sci.recipeandroid.feature.auth.ui.viewmodel.AuthOptionViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AuthOptionFragment : Fragment() {

    private var _binding: FragmentAuthenticationOptionsBinding? = null
    private val binding get() = _binding!!

    private val authViewModel: AuthOptionViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        WindowCompat.setDecorFitsSystemWindows(requireActivity().window, true)
//        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { _view, windowInsets ->
//            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
//            _view.updateLayoutParams<ViewGroup.MarginLayoutParams> {
//                // Push all content below the top system status bar
//                topMargin = 0
//                // Push all content to the right of the right system status bar
//                bottomMargin = 0
//            }
//            WindowInsetsCompat.CONSUMED
//        }
        _binding = FragmentAuthenticationOptionsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        authViewModel.uiEvent.observe(viewLifecycleOwner) {
            Toast.makeText(this.context, it, Toast.LENGTH_LONG).show()
        }
        binding.googleBtn.setOnClickListener {
            authViewModel.googleAuthentication(requireActivity())
        }
        underLineTextDecorate()

    }

    private fun underLineTextDecorate() {
        //Underline only privacy notice & Terms of Use
        val privacyToUString = getString(R.string.privacy_notice_terms_of_use_string)
        val spannablePrivacyString = SpannableString(privacyToUString)
        val privacyStart = privacyToUString.indexOf("Privacy Notice")
        val privacyEnd = privacyStart + "Privacy Notice".length
        val termsOfUseStart = privacyToUString.indexOf("Terms of Use")
        val termsOfUseEnd = termsOfUseStart + "Terms of Use".length
        spannablePrivacyString.setSpan(
            UnderlineSpan(),
            privacyStart, privacyEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannablePrivacyString.setSpan(
            UnderlineSpan(),
            termsOfUseStart, termsOfUseEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        binding.tvPrivacyNotice.text = spannablePrivacyString

        // Underline only "Log in"
        val alreadyLogInString = getString(R.string.already_have_an_account)
        val spannableStringForAlreadyLogIn = SpannableString(alreadyLogInString)
        val logInStart = alreadyLogInString.indexOf("Log in")
        val logInEnd = logInStart + "Log in".length
        spannableStringForAlreadyLogIn.setSpan(
            UnderlineSpan(),
            logInStart,
            logInEnd,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        binding.tvLogIn.text = spannableStringForAlreadyLogIn
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}