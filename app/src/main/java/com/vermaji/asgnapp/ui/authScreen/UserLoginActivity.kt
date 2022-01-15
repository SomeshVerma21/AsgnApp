package com.vermaji.asgnapp.ui.authScreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vermaji.asgnapp.R

class UserLoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_login)
        setupUI()
    }

    private fun setupUI()
    {
        supportFragmentManager.beginTransaction()
            .add(R.id.idUserLoginFragmentContainer,RegisterFragment.newInstance())
            .commitNow()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}