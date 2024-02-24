package com.example.goaltracking.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.goaltracking.R
import com.example.goaltracking.databinding.ActivityAddGoalBinding
import com.example.goaltracking.model.Goal

class AddGoalActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddGoalBinding
    private lateinit var goal: Goal
    private lateinit var oldGoal : Goal
    var isUpdate = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
        setContentView(R.layout.activity_add_goal)
    }
}