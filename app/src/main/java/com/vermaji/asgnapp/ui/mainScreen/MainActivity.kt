package com.vermaji.asgnapp.ui.mainScreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.vermaji.asgnapp.R
import com.vermaji.asgnapp.databinding.ActivityMainBinding
import com.vermaji.asgnapp.ui.authScreen.UserLoginActivity
import com.vermaji.asgnapp.ui.mainScreen.home.HomeFragment
import com.vermaji.asgnapp.ui.mainScreen.task.AddNewTask
import com.vermaji.asgnapp.ui.mainScreen.task.TaskList

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        setupUI()
    }
    private fun setupUI(){
        supportFragmentManager.beginTransaction()
            .add(R.id.idMainScreenFragmentContainer,HomeFragment.newInstance())
            .commitNow()
        binding.topAppBar.title = "Home"
        binding.idBottomNavigation.setOnItemSelectedListener{
            when(it.itemId){
                R.id.home_nav ->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.idMainScreenFragmentContainer,HomeFragment.newInstance())
                        .commitNow()
                    binding.topAppBar.title = "Home"
                }
                R.id.add_nav ->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.idMainScreenFragmentContainer,AddNewTask.newInstance())
                        .commitNow()
                    binding.topAppBar.title = "New Task"
                }
                R.id.task_nav ->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.idMainScreenFragmentContainer,TaskList.newInstance())
                        .commitNow()
                    binding.topAppBar.title = "Tasks"
                }
            }
            true
        }

        binding.topAppBar.setOnMenuItemClickListener{
            menuItem->
            when(menuItem.itemId){
                R.id.logout_nav->{
                    FirebaseAuth.getInstance().signOut()
                    val intent = Intent(this,UserLoginActivity::class.java)
                    startActivity(intent)
                    finish()
                    true
                }
                R.id.settings_nav->{
                    true
                }
                else -> false
            }
        }
    }

}