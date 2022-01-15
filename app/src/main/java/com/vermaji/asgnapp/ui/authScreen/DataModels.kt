package com.vermaji.asgnapp.ui.authScreen

data class RegUserData(
    val name:String,
    val email:String,
    val password:String,
    val age:Int
)

data class LoginUserData(
    val email: String,
    val password: String
)
