package com.sci.recipeandroid.feature.auth.ui.screen.authentication

import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StyleSpan
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
        textDecoration()

    }

    private fun textDecoration() {
        //app title bold
        val fullText = getString(R.string.app_name_title)
        val spannableString = SpannableString(fullText)
        val chefStart = fullText.indexOf("C H E F")
        val chefEnd = chefStart + "C H E F".length
        spannableString.setSpan(
            StyleSpan(Typeface.BOLD), chefStart, chefEnd,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding?.tvAppNameTitle?.text = spannableString

        //Underline only privacy notice & Terms of Use
        val privacyToUString = getString(R.string.privacy_notice_terms_of_use_string)
        val spannablePrivacyString = SpannableString(privacyToUString)
        val privacyStart = privacyToUString.indexOf("Privacy Notice")
        val privacyEnd = privacyStart + "Privacy Notice".length
        val termsOfUseStart = privacyToUString.indexOf("Terms of Use")
        val termsOfUseEnd = termsOfUseStart + "Terms of Use".length
        spannablePrivacyString.setSpan(UnderlineSpan(),
            privacyStart, privacyEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannablePrivacyString.setSpan(UnderlineSpan(),
            termsOfUseStart, termsOfUseEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
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
        binding.btnTvLogIn.text = spannableStringForAlreadyLogIn

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}