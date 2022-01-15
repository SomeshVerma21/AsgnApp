package com.vermaji.asgnapp.ui.mainScreen.task

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.vermaji.asgnapp.R
import com.vermaji.asgnapp.databinding.FragmentTaskListBinding
import com.vermaji.asgnapp.ui.mainScreen.databaseUtils.TaskDatabase

class TaskList : Fragment() {
    private lateinit var binding:FragmentTaskListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTaskListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = binding.idTaskRecyclerView
        val tasks = TaskDatabase.getInstance(requireContext()).taskDatabaseDao.getAll()
        if (tasks.isNotEmpty()){
            Log.i("main",tasks.toString())
            val adapter = TaskAdapter(requireContext(),tasks)
            recyclerView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
            recyclerView.adapter = adapter
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            TaskList()
    }
}