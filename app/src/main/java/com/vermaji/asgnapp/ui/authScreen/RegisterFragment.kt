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
import com.vermaji.asgnapp.databinding.FragmentRegisterBinding
import com.vermaji.asgnapp.ui.mainScreen.MainActivity

class RegisterFragment : Fragment() {
    private lateinit var binding:FragmentRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firebaseDatabase: FirebaseDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater)
        requireActivity().title = "Register"
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.idFlipPage.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.idUserLoginFragmentContainer,LoginFragment.newInstance())
                .commit()
        }
        binding.idRegisterBtn.setOnClickListener{
            val fullName = binding.idRegisterFullName.text.toString()
            val email = binding.idRegisterEmail.text.toString()
            val password = binding.idRegisterPassword.text.toString()
            val age = binding.idRegisterAge.text.toString()
            if (fullName.isNotEmpty()&&email.isNotEmpty()&&password.isNotEmpty()&&age.isNotEmpty()){
                registerUser(RegUserData(fullName, email, password,age.toInt()))
            }else{
                Toast.makeText(requireContext(),"Field can not empty",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun registerUser(userData:RegUserData)
    {
        firebaseAuth = FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance()
        firebaseAuth.createUserWithEmailAndPassword(userData.email,userData.password).addOnCompleteListener {
            task ->
                if (task.isSuccessful){
                    Toast.makeText(requireContext(),"Success",Toast.LENGTH_SHORT).show()
                    task.result.user?.let { firebaseDatabase.reference.child("users").child(it.uid).setValue(userData)
                        .addOnCompleteListener {
                            if (it.isSuccessful){
                                firebaseAuth.signOut()
                                Toast.makeText(requireContext(),"Success",Toast.LENGTH_SHORT).show()
                                val intent = Intent(requireActivity(),MainActivity::class.java)
                                startActivity(intent)
                                requireActivity().finish()
                            }else
                            {
                                Toast.makeText(requireContext(),"Something went wrong",Toast.LENGTH_SHORT).show()
                            }
                    } }
                }else{
                    Toast.makeText(requireContext(),"Failed",Toast.LENGTH_SHORT).show()
                }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = RegisterFragment()
    }
}