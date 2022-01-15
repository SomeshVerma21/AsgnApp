package com.vermaji.asgnapp.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.vermaji.asgnapp.R
import com.vermaji.asgnapp.ui.authScreen.UserLoginActivity
import com.vermaji.asgnapp.ui.mainScreen.MainActivity

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        firebaseAuth = FirebaseAuth.getInstance()
        if (firebaseAuth.currentUser!=null)
        {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }else
        {
            val intent = Intent(this,UserLoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}