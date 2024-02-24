package com.example.goaltracking.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.goaltracking.databinding.ActivityAddGoalBinding
import com.example.goaltracking.model.Goal
import java.text.SimpleDateFormat
import java.util.Date

class AddGoalActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddGoalBinding
    private lateinit var goal: Goal
    private lateinit var oldGoal : Goal
    var isUpdate = false

    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddGoalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        try {
            oldGoal = intent.getSerializableExtra("current_goal") as Goal
            binding.etTitle.setText(oldGoal.title)
            binding.etDeadline.setText(oldGoal.deadline)
            binding.etCategories.setText(oldGoal.categories)
            binding.etDescription.setText(oldGoal.description)
            isUpdate = true
        } catch (e : Exception){
            e.printStackTrace()
        }
        binding.imgCheck.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val deadline = binding.etDeadline.text.toString()
            val categories = binding.etCategories.text.toString()
            val description = binding.etDescription.text.toString()


            if (title.isNotEmpty() || deadline.isNotEmpty() || categories.isNotEmpty() || description.isNotEmpty()){
                val formatter = SimpleDateFormat("EEE, d MMM, yyyy HH:mm a")
                if (isUpdate){
                    goal = Goal(
                        oldGoal.id,title, deadline, categories, description, formatter.format(Date())
                    )
                } else {
                    goal = Goal(
                        null, title, deadline, categories, description, formatter.format(Date())
                    )
                }
                val intent = Intent()
                intent.putExtra("goal", goal)
                setResult(Activity.RESULT_OK, intent)
                    finish()
            } else {
                Toast.makeText(this@AddGoalActivity, "Please enter some data", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
        }
        binding.imgBack.setOnClickListener {
            onBackPressed()
        }
    }
}