package com.example.goaltracking.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.PopupMenu
import androidx.activity.result.contract.ActivityResultContracts
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.goaltracking.R
import com.example.goaltracking.adapter.GoalAdapter
import com.example.goaltracking.database.GoalDatabase
import com.example.goaltracking.databinding.ActivityMainBinding
import com.example.goaltracking.model.Goal
import com.example.goaltracking.model.GoalViewModel

class MainActivity : AppCompatActivity(), GoalAdapter.GoalClickListener, PopupMenu.OnMenuItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var database: GoalDatabase
    private lateinit var viewModel: GoalViewModel
    private lateinit var adapter: GoalAdapter
    private lateinit var selectedGoal : Goal

    private val updateGoal = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
        if(result.resultCode == Activity.RESULT_OK){

            val goal = result.data?.getSerializableExtra("goal") as? Goal
            if (goal != null){

                viewModel.updateGoal(goal)
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Initialize the UI
        initUI()

        // this method use to update goals
        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application))[GoalViewModel::class.java]

        viewModel.allgoals.observe(this){ list ->
            list?.let {
                adapter.updateList(list)
            }
        }

         database = GoalDatabase.getDatabase(this)
    }

    private fun initUI() {
      binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = StaggeredGridLayoutManager(2, LinearLayout.VERTICAL)
        adapter = GoalAdapter(this, this)
        binding.recyclerView.adapter = adapter

        val getContent = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){result ->
            if (result.resultCode == Activity.RESULT_OK){
               val goal = result.data?.getSerializableExtra("goal") as? Goal
                if (goal != null){
                    viewModel.insertGoal(goal)
                }
            }
        }

        // this method is use to FloatingActionButton

        binding.fbAddGoal.setOnClickListener {
            val intent = Intent(this, AddGoalActivity::class.java)
            getContent.launch(intent)
        }
    }

    override fun onItemClicked(goal: Goal) {
        val intent = Intent(this@MainActivity, AddGoalActivity::class.java)
        intent.putExtra("current_goal", goal)
        updateGoal.launch(intent)
    }

    override fun onLongItemClicked(goal: Goal, cardView: CardView) {
        selectedGoal = goal
        popUpDisplay(cardView)
    }
    private fun popUpDisplay(cardView: CardView) {
       val popup = PopupMenu(this, cardView)
        popup.setOnMenuItemClickListener(this@MainActivity)
        popup.inflate(R.menu.pop_up_menu)
        popup.show()
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.delete_goal){
            viewModel.deleteGoal(selectedGoal)
            return true
        }
        return false
    }
}