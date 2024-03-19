package com.example.goaltracking.ui

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.Toast
import com.example.goaltracking.R
import com.example.goaltracking.databinding.ActivityAddGoalBinding
import com.example.goaltracking.model.Goal
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

class AddGoalActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddGoalBinding
    private lateinit var goal: Goal
    private lateinit var oldGoal : Goal
    private var isUpdate = false

    // Calender
    private lateinit var dateView: EditText

    @SuppressLint("SimpleDateFormat")
    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddGoalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Dropdown List
        val dropdown = resources.getStringArray(R.array.categories_item)
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_list, dropdown)
        val autocompleteTV = findViewById<AutoCompleteTextView>(R.id.et_categories)
        autocompleteTV.setAdapter(arrayAdapter)

        // This method is use to calender
        dateView = findViewById(R.id.et_deadline)
        dateView.setOnClickListener{
            val calender = Calendar.getInstance()

            val year = calender.get(Calendar.YEAR)
            val month = calender.get(Calendar.MONTH)
            val day = calender.get(Calendar.DAY_OF_MONTH)

            @Suppress("NAME_SHADOWING")
            val datePickerDialog = DatePickerDialog(this, { _, year, monthOfYear, dayOfMonth ->
                    val date = (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-"  + year)
                    dateView.setText(date)
                },
                year, month, day
            )
            datePickerDialog.show()
        }

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
                goal = if (isUpdate){
                    Goal(
                        oldGoal.id,title, deadline, categories, description, formatter.format(Date())
                    )
                } else {
                    Goal(
                        null, title, deadline, categories, description, formatter.format(Date())
                    )
                }
                val intent = Intent()
                intent.putExtra("goal", goal)
                setResult(RESULT_OK, intent)
                    finish()
            } else {
                Toast.makeText(this@AddGoalActivity, "Please Enter Your Goal", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
        }
        binding.imgBack.setOnClickListener {
            super.onBackPressed()
        }
    }
}