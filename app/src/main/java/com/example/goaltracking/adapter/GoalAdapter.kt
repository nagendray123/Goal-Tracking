package com.example.goaltracking.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.goaltracking.R
import com.example.goaltracking.model.Goal
import kotlin.random.Random

class GoalAdapter(private val context: Context, val listener: GoalClickListener) : RecyclerView.Adapter<GoalAdapter.GoalViewHolder>() {

    private val goalList = ArrayList<Goal>()
    private val fullList = ArrayList<Goal>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoalAdapter.GoalViewHolder {
       return GoalViewHolder(
           LayoutInflater.from(context).inflate(R.layout.item_list, parent, false)
       )
    }

    override fun onBindViewHolder(holder: GoalAdapter.GoalViewHolder, position: Int) {
        val currentGoal = goalList[position]
        holder.title.text = currentGoal.title
        holder.title.isSelected = true

        holder.deadline.text = currentGoal.deadline
        holder.deadline.isSelected = true

        holder.categories.text = currentGoal.categories
        holder.description.text = currentGoal.description

        // this method is use to random background color
        holder.goals_layout.setCardBackgroundColor(holder.itemView.resources.getColor(randomColor(), null))

        // this method is use to goal_layout
        holder.goals_layout.setOnClickListener {
            listener.onItemClicked(goalList[holder.adapterPosition])
        }
        holder.goals_layout.setOnLongClickListener {
            listener.onLongItemClicked(goalList[holder.adapterPosition], holder.goals_layout)
            true
        }
    }

    override fun getItemCount(): Int {
        return goalList.size
    }

    // this function is use to update Goal

    fun updateList(newList: List<Goal>){
        fullList.clear()
        fullList.addAll(newList)

        goalList.clear()
        goalList.addAll(fullList)
        notifyDataSetChanged()
    }

    // this function is use to  random color
    fun randomColor(): Int {
        val list = ArrayList<Int>()
        list.add(R.color.color_1)
        list.add(R.color.color_2)
        list.add(R.color.color_3)
        list.add(R.color.color_4)
        list.add(R.color.color_5)
        list.add(R.color.color_6)

        val seed = System.currentTimeMillis().toInt()
        val randomIndex = Random(seed).nextInt(list.size)
        return list[randomIndex]

    }

    inner class GoalViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val goals_layout = itemView.findViewById<CardView>(R.id.card_layout)
        val title = itemView.findViewById<TextView>(R.id.tv_title)
        val deadline = itemView.findViewById<TextView>(R.id.tv_deadline)
        val categories = itemView.findViewById<TextView>(R.id.tv_categories)
        val description = itemView.findViewById<TextView>(R.id.tv_description)
    }

    interface GoalClickListener{
        fun onItemClicked(goal: Goal)
        fun onLongItemClicked(goal: Goal, cardView: CardView)
    }
}