package com.vermaji.asgnapp.ui.mainScreen.home

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.vermaji.asgnapp.R

class UsersAdapter(val context:Context, private val list:MutableList<UserModel>) : RecyclerView.Adapter<MViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.user_card_view,parent,false)
        return MViewHolder(view)
    }

    override fun onBindViewHolder(holder: MViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class MViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
    fun bind(userModel:UserModel){
        itemView.findViewById<TextView>(R.id.idUserFullName).text = userModel.name
        itemView.findViewById<TextView>(R.id.idAge).text = userModel.age
        itemView.findViewById<TextView>(R.id.idEmail).text = userModel.email
        if (userModel.id.equals(FirebaseAuth.getInstance().currentUser?.uid))
        {
            itemView.findViewById<ConstraintLayout>(R.id.idCardBackgroundLayout).setBackgroundColor(Color.MAGENTA)
            itemView.findViewById<TextView>(R.id.idUserFullName).text = userModel.name+"(You)"

        }
    }
}
