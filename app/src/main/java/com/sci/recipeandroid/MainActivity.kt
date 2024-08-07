package com.sci.recipeandroid

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.sci.recipeandroid.feature.auth.ui.screen.LoginFragment
import com.sci.recipeandroid.feature.auth.ui.screen.SignUpFragment
import com.sci.recipeandroid.feature.auth.ui.screen.SignupOptionsFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, 0, systemBars.right, systemBars.bottom)
//            insets
//        }
        // Check if the fragment is already added
        if (savedInstanceState == null) {
            // Dynamically add the fragment
            supportFragmentManager.beginTransaction()
                .replace(R.id.host_fragment, SignUpFragment())
                .commit()
        }

    }
}