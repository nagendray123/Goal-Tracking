package com.example.goaltracking.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.goaltracking.R
import com.example.goaltracking.adapter.GoalAdapter
import com.example.goaltracking.database.GoalDatabase
import com.example.goaltracking.databinding.ActivityMainBinding
import com.example.goaltracking.model.Goal
import com.example.goaltracking.model.GoalViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var database: GoalDatabase
    lateinit var viewModel: GoalViewModel
    lateinit var adapter: GoalAdapter
    lateinit var selectedGoal : Goal
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Initialize the UI
        initUI()

        // this method use to update goals
        viewModel = ViewModelProvider(this,
         ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(GoalViewModel::class.java)

        viewModel.allgoals.observe(this){ list ->
            list?.let {
                adapter.updateList(list)
            }
        }


    }

    private fun initUI() {
        TODO("Not yet implemented")
    }
}