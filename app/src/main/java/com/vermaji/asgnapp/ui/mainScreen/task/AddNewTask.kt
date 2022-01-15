package com.vermaji.asgnapp.ui.mainScreen.task

import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.vermaji.asgnapp.R
import com.vermaji.asgnapp.databinding.FragmentAddNewTaskBinding
import com.vermaji.asgnapp.ui.mainScreen.databaseUtils.TaskDatabase
import com.vermaji.asgnapp.ui.mainScreen.databaseUtils.TaskModel

class AddNewTask : Fragment() {
    private lateinit var binding:FragmentAddNewTaskBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddNewTaskBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.idAddTaskBtn.setOnClickListener{
            val title = binding.idAddTaskTitle.text.toString()
            val description = binding.idAddTaskDescription.text.toString()
            val date = binding.idDateOfComplete.text.toString()

            if (title.isNotEmpty()&&description.isNotEmpty()&&date.isNotEmpty()){
                val dataSource = TaskDatabase.getInstance(requireContext())
                dataSource.taskDatabaseDao.insertTask(TaskModel(id = null, title = title, description = description, date = date))
                Toast.makeText(requireContext(),"Task saved",Toast.LENGTH_SHORT).show()
                
            }else{
                Toast.makeText(requireContext(),"Fields can be empty",Toast.LENGTH_SHORT).show()
            }

        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            AddNewTask()
    }
}