package com.vermaji.asgnapp.ui.authScreen

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.vermaji.asgnapp.R
import com.vermaji.asgnapp.databinding.FragmentLoginBinding
import com.vermaji.asgnapp.ui.mainScreen.MainActivity

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater)
        requireActivity().title = "Login"
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.idFlipPage.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.idUserLoginFragmentContainer,RegisterFragment.newInstance())
                .commit()
        }
        binding.idLoginBtn.setOnClickListener{
            val email = binding.idLoginEmail.text.toString()
            val password = binding.idLoginPassword.text.toString()
            if (email.isNotEmpty()&&password.isNotEmpty())
            {
                loginUser(LoginUserData(email,password))
            }else{
                Toast.makeText(requireContext(),"Field can be empty",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loginUser(loginUserData: LoginUserData)
    {
        firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.signInWithEmailAndPassword(loginUserData.email,loginUserData.password)
            .addOnCompleteListener {
                task ->
                if (task.isSuccessful){
                    val intent = Intent(requireActivity(),MainActivity::class.java)
                    startActivity(intent)
                    requireActivity().finish()
                }else{
                    Toast.makeText(requireContext(),task.exception.toString(),Toast.LENGTH_SHORT).show()
                }
        }
    }
    companion object {
        @JvmStatic
        fun newInstance() = LoginFragment()
    }
}