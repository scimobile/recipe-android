package com.sci.recipeandroid.feature.auth.ui.screen

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.UnderlineSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sci.recipeandroid.R
import com.sci.recipeandroid.databinding.FragmentSignupOptionsBinding

class SignupOptionsFragment : Fragment() {

    private var binding: FragmentSignupOptionsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignupOptionsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


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
        spannablePrivacyString.setSpan(UnderlineSpan(),
            privacyStart, privacyEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannablePrivacyString.setSpan(UnderlineSpan(),
            termsOfUseStart, termsOfUseEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding?.tvPrivacyNotice?.text = spannablePrivacyString

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
        binding?.tvLogIn?.text = spannableStringForAlreadyLogIn

    }
}